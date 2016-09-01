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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Sjoerd Boerhout
 */
public class SetText extends TestAction
{

  private final static Logger LOGGER = LogManager.getLogger(SetText.class.
          getName());
  
  // Action attribute names
  public final static String ATTR_XPATH = "XPATH";
  public final static String ATTR_VALUE = "VALUE";
  public final static String ATTR_REPLACE = "REPLACE";
          
  Selenium selenium;

  // Declaration and default value
  private Xpath xPath;
  private Text text;
  private Bool replace;


  /**
   * Constructor
   */
  public SetText(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
    
    try
    {
      // Create parameters with (optional) defaults and limits
      xPath = new Xpath();

      text = new Text();
      text.setMinLength(0);

      replace = new Bool(true);
    }
    catch(Exception ex)
    {
      LOGGER.error("Exception while creating new SetText TestAction! " + ex.getMessage());
    }
  }


  /**
   * Execute this action
   */  
  @Override
  public TestStatus execute()
  {
    if(!isValid())
    {
      LOGGER.error("Action is not validated!");
      return TestStatus.FAILED;
    }
    
    LOGGER.debug(">> Set '{}' with text '{}'", xPath.getValue(), text.getValue());
    
    int count = 0;
    
    while(true)
    {
      try
      {
        WebElement element = selenium.findElement(xPath.getValue());
        
        if(element == null || !element.isEnabled())
        {
          throw new Exception("Element '" + xPath.getValue() + "' not found.");
        }
        
        LOGGER.trace("Sending keys to element '{}'. Replace={} (doSetText)", xPath.getValue(), replace.getValue());
        
        if(replace.getValue())
          element.sendKeys(Keys.chord(Keys.CONTROL, "a"),text.getValue());
        else
          element.sendKeys(text.getValue());
        
        return TestStatus.PASSED;
      }
      catch(Exception eX)
      {
        if(++count > selenium.getMaxRetries())
        {
          LOGGER.debug("Exception while setting text '{}' in '{}': {} (retry count: {})", 
                        xPath.getValue(), text.getValue(), eX.getMessage(), count);
          
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
    return("'" + this.getClass().getSimpleName().toUpperCase() + "': " + (replace.getValue() ? "Replace" : "Set") + " text of '" + xPath.getValue() + "' to '" + text.getValue() + "'");
  }

  
  /**
  * Check if all requirements are met to execute this action
  */
  @Override
  public boolean isValid()
  {
    if(selenium == null)    return false;
    if(!xPath.isValid())    return false;
    if(!text.isValid())     return false;
    if(!replace.isValid())  return false;
    
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

        case ATTR_PARAMETER:
        case ATTR_VALUE:
          text.setValue(value); 
        break;

        case ATTR_REPLACE:  
          replace.setValue(value); 
        break;
      }

      xPath.setAttribute(key, value);  
    }
    catch (Exception ex)
    {
      LOGGER.error("Exception while setting attribute to TestAction : " + ex.getMessage());
    }
  }

}
