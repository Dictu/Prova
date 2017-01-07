/**
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * <p>
 * http://ec.europa.eu/idabc/eupl
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * <p>
 * Date:      23-08-2016
 * Author(s): Sjoerd Boerhout
 * <p>
 */
package nl.dictu.prova.plugins.output.selenium.actions;

import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestStatus;
import nl.dictu.prova.plugins.output.selenium.Selenium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Sjoerd Boerhout
 */
public class Click extends TestAction
{

  private final static Logger LOGGER = LogManager.getLogger(Click.class.
          getName());
  
    // Action attribute names
  public final static String ATTR_XPATH               = "XPATH";
  public final static String ATTR_RIGHTCLICK          = "RIGHTCLICK";
  public final static String ATTR_NUMBEROFCLICKS      = "NUMBEROFCLICKS";
  public final static String ATTR_WAITUNTILPAGELOADED = "WAITUNTILPAGELOADED";
  
  Selenium selenium = null;
  private Bool rightClick;
  private Bool waitUntilPageLoaded;
  private Xpath xPath;
  private Number numberOfClicks;
  

  /**
   * Constructor
   */
  public Click(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
    
    // Create parameters with (optional) defaults and limits
    try
    {
      xPath = new Xpath();
      rightClick = new Bool(false);
      numberOfClicks = new Number(1);
      numberOfClicks.setMinValue(1);
      numberOfClicks.setMaxValue(3);
      waitUntilPageLoaded = new Bool(true);
    } 
    catch (Exception ex)
    {
      LOGGER.error("Exception while creating new Click TestAction! " + ex.getMessage());
    }
  }


  /**
   * Execute this action
   */  
  @Override
  public TestStatus execute()
  {
    LOGGER.debug(">> Click with {} on '{}', Wait for page = {}", (rightClick.getValue() ? "right" : "left"), xPath.getValue(), waitUntilPageLoaded.getValue());
    
    int count = 0;
    
    while(true)
    {
      try
      {
        if(!xPath.isValid())
          xPath.setValue(this.getAttribute("xPath"));
        if(!rightClick.isValid())
          rightClick.setValue(this.getAttribute("rightClick").trim().equalsIgnoreCase("true"));
        if(!waitUntilPageLoaded.isValid())
          waitUntilPageLoaded.setValue(this.getAttribute("waitUntilPageLoaded").trim().equalsIgnoreCase("true"));
        
        if(!isValid())
        {
          LOGGER.error("Action is not validated!");
          return TestStatus.FAILED;
        }
        
        WebElement element = selenium.findElement(xPath.getValue());
        
        if(element == null)
          throw new Exception("Element '" + xPath.getValue() + "' not found.");
      
        // TODO support right click
        if(rightClick.getValue()) throw new Exception("Right click is not supported yet.");
        
        LOGGER.trace("Clicking on element '{}' (doClick)", xPath.getValue());
        assert element.isDisplayed();
        assert element.isEnabled();
        
        //if(waitUntilPageLoaded)
        //  element.submit();
        //else
          element.click();
        //element.sendKeys(Keys.RETURN);
        
        // TODO Add support for waitUntilPageLoaded
        if(waitUntilPageLoaded.getValue())
        {
          //new WebDriverWait(webdriver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
          //new WebDriverWait(webdriver, 30).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
        }
        
        return TestStatus.PASSED;
      }
      catch(Exception eX)
      {
        LOGGER.debug("Exception while clicking on element '{}' retry count: '{}', Type: '{}' : '{}'", 
        this.getAttribute("xPath"),  
        count, 
        eX.getClass().getSimpleName(),
        eX.getMessage());
        
        if(++count > selenium.getMaxRetries())
        { 
          CaptureScreen capture = new CaptureScreen(selenium);
          capture.setAttribute("filename", "Click");
          capture.execute();
          eX.printStackTrace();
          return TestStatus.FAILED;
        }
      }
    }
  }

  
  /**
   * Return a string representation of the objects content
   * 
   * @return 
   */
  @Override
  public String toString()
  {
    return( "'" + this.getClass().getSimpleName().toUpperCase() + "': Click on element '" + xPath.getValue() + 
            "' with " + numberOfClicks.getValue() + " clicks.") +
            (rightClick.getValue() ? " right" : " left") + " clicks. " +
            "Wait for page loaded: " + waitUntilPageLoaded.getValue();
  }

  
 /**
  * Check if all requirements are met to execute this action
  */
  @Override
  public boolean isValid()
  {
    if(selenium == null) return false;
    if(rightClick == null) return false;
    if(waitUntilPageLoaded == null) return false;
    if(xPath == null) return false;
    if(numberOfClicks == null) return false;
    
    return true;
  }
  
  
  /**
   * Set attribute <key> with <value>
   * - Unknown attributes are ignored
   * - Invalid values result in an exception
   * 
   * @param key
   * @param value
   * @throws Exception
   */
  @Override
  public void setAttribute(String key, String value)
  {
    try
    {
      LOGGER.trace("Request to set '{}' to '{}'", () -> key, () -> value);

      switch(key.toUpperCase())
      {
        case ATTR_XPATH:  
          xPath.setValue(value); 
        break;

        case ATTR_RIGHTCLICK: 
          rightClick.setValue(value); 
        break;

        case ATTR_NUMBEROFCLICKS:
          numberOfClicks.setValue(value);
        break;

        case ATTR_WAITUNTILPAGELOADED:  
          waitUntilPageLoaded.setValue(value); 
        break;
      }

      xPath.setAttribute(key, value);
    }
    catch(Exception ex)
    {
      LOGGER.error("Exception while setting attribute to TestAction");
    }
  }

}