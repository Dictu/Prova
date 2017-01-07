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

import junit.framework.Assert;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestStatus;
import nl.dictu.prova.plugins.output.selenium.Selenium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Sjoerd Boerhout
 */
public class ValidateText extends TestAction
{

  private final static Logger LOGGER = LogManager.getLogger(ValidateText.class.
          getName());

  // Action attribute names
  public final static String ATTR_VALUE   = "VALUE";
  public final static String ATTR_EXISTS  = "EXISTS";
  public final static String ATTR_TIMEOUT = "TIMEOUT";
  public final static String ATTR_XPATH   = "XPATH";

  private Text    text;
  private Bool    exists;
  private TimeOut timeOut;
  private Xpath   xPath;
  Selenium selenium;
  
  /**
   * Constructor
   */
  public ValidateText(Selenium selenium)
  {
    super(LOGGER);
    this.selenium = selenium;
    
    try
    {
      // Create parameters with (optional) defaults and limits
      text = new Text();
      exists = new Bool(true);
      timeOut = new TimeOut(0); // Ms
      xPath = new Xpath();
      xPath.setValue("/html/body");
    }
    catch(Exception ex)
    {
      LOGGER.error("Exception while creating new ValidateText TestAction! " + ex.getMessage());
    }
  }


  /**
   * Execute this action
   */  
  @Override
  public TestStatus execute()
  {
    LOGGER.debug("> Validate '{}' with text '{}'", xPath, text.getValue());
    int iTimeOut = 0;
    try
    {
    	if (timeOut.getValue() == 0)
    	{
    		try
    		{
    			LOGGER.trace("Timeout not set; setting timeout to default");
    			timeOut.setValue(Integer.parseInt(selenium.getTestRunner().getProperty(Config.PROVA_TIMEOUT)));
    		}
    		catch(Exception eX)
    		{
    			LOGGER.debug("Setting default timeout failed: " + eX);
    		}
    	LOGGER.trace("Converting {} from milliseconds to seconds", timeOut.getValue());
    	iTimeOut = Integer.valueOf((int) (timeOut.getValue()/1000));
    	if(iTimeOut < 1) iTimeOut = 1;
    	LOGGER.trace("Convertion to seconds complete, timeout is {} seconds", iTimeOut);
    	}
    }
    catch(Exception eX)
    {
    	LOGGER.debug("Converting to seconds failed: " + eX);
    	throw eX;
    }
    WebDriverWait wait = new WebDriverWait(selenium.getWebdriver(), iTimeOut);
    int count = 0;
    
    while(true)
    {
      try
      {
    	
    	WebElement element = selenium.getWebdriver().findElement(By.xpath(xPath.getValue()));
    	
        if(element == null || !element.isEnabled())
        {
          throw new Exception("Element '" + xPath.getValue() + "' not found.");
        }
        // Get text from element
        String elementText = element.getText()+ "\r\n";
        elementText = elementText + "Attribute @value: " + element.getAttribute("value");
        //LOGGER.trace(text);
        // If exists is false, check if text is not present in element
        if (!exists.getValue())
        {
        	//validate if value is not present in text
        	try
        	{
	        	LOGGER.trace("Check if text {} isn't present in element {}", elementText, xPath.getValue());
	        	Assert.assertTrue("The value \"" + text.getValue() + "\" is found in the text: " + elementText,
	        			            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text.getValue()))));
        	}
        	catch(TimeoutException eX)
        	{
        		//validate if value is not present in attribute @value
        		try
        		{
        			LOGGER.trace("Check if the attribute @value, in element {}, doesn't contain the text {}", xPath.getValue(), text.getValue() );
        			Assert.assertTrue("The value \"" + text.getValue() + "\" is found in the text: " + elementText,
    			            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(element, text.getValue()))));
        		}
        		catch(TimeoutException e)
        		{
              throw new TimeoutException("The value \"" + text.getValue() + "\" is found in the text: " + elementText);
        		}
        	}
        }
        // Check if element contains the given text
        else
        {
        	//validate if value is present in text
        	try
        	{
        		LOGGER.trace("Check if text {} is present in element {}", text.getValue(), xPath);
        		Assert.assertTrue("The value \"" + text.getValue() + "\" is not found in the text: " + elementText,
 			           wait.until(ExpectedConditions.textToBePresentInElement(element, text.getValue())));
        	}
        	catch(TimeoutException eX)
        	{
        		//validate if value is present in attribute @value
        		try
        		{
        			LOGGER.trace("Check if the attribute @value, in element {}, contains the text {}", xPath.getValue(), text.getValue() );
        			Assert.assertTrue("The value \"" + text.getValue() + "\" is not found in the text: " + elementText,
      			           wait.until(ExpectedConditions.textToBePresentInElementValue(element, text.getValue())));
        		}
        		catch(TimeoutException e)
        		{
        			throw new TimeoutException("The value \"" + text.getValue() + "\" is not found in the text: " + elementText);
        		}
        	}
        }
        
        return TestStatus.PASSED;
      }
      catch(Exception eX)
      {
        if(++count > selenium.getMaxRetries())
        {
          LOGGER.debug("Exception while validating text '{}' in '{}': {} (retry count: {})", 
                        text.getValue(), xPath.getValue(), eX.getMessage(), count);
        }
        return TestStatus.FAILED;
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
    return("'" + this.getClass().getSimpleName().toUpperCase() + "': Validate that text '" + text.getValue() + "' " +
          (exists.getValue() ? "" : "doesn't ") + "exists. " +
          (xPath.getValue().length() > 0 ? "Element: " + xPath.getValue() + ". " : "") +
          " TimeOut: " + timeOut.getValue() );
  }

  
  /**
  * Check if all requirements are met to execute this action
  */
  @Override
  public boolean isValid()
  {
    if(selenium == null)  return false;
    if(!text.isValid())     return false;
    if(!exists.isValid())   return false;
    if(!timeOut.isValid())  return false;
    if(!xPath.isValid())    return false;
    
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
        case ATTR_PARAMETER:
        case ATTR_VALUE:  
          text.setValue(value); 
        break;

        case ATTR_EXISTS:
          exists.setValue(value); 
        break;

        case ATTR_TIMEOUT:
          if((value!=null) && (value.length()>0)) timeOut.setValue(value); 
        break;

        case ATTR_XPATH:
          if(value!=null) xPath.setValue(value);   
        break;
      }

      xPath.setAttribute(key, value); 
    }
    catch(Exception ex)
    {
      LOGGER.error("Exception while setting attribute to TestAction! : " + ex.getMessage());
    }
  }

}
