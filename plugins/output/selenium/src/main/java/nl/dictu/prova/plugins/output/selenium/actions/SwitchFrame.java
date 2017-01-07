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
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Sjoerd Boerhout
 */
public class SwitchFrame extends TestAction
{

  private final static Logger LOGGER = LogManager.getLogger(SwitchFrame.class.
          getName());

  // Action attribute names
  public final static String ATTR_XPATH = "XPATH";
  public final static String ATTR_ALERT = "ALERT";
  public final static String ATTR_ACCEPT = "ACCEPT";
  
  Selenium selenium;
  Xpath xPath;
  Bool alert;
  Bool accept;

  /**
   * Constructor
   */
  public SwitchFrame(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
    
    try
    {
      // Create parameters with (optional) defaults and limits
      xPath = new Xpath();
      xPath.setValue("DEFAULT");
      alert = new Bool(false);
      accept = new Bool(false);
    }
    catch(Exception ex)
    {
      LOGGER.error("Exception while creating new SwitchFrame TestAction! " + ex.getMessage());
    }
  }


  /**
   * Execute this action in the active output plug-in
   */
  @Override
  public TestStatus execute()
  {
    if(!isValid())
    {
      LOGGER.error("Action is not validated!");
      return TestStatus.FAILED;
    }
    
    LOGGER.debug(">> Switch to frame");
    
    int count = 0;
    
    while(true)
    {
      try
      {
        if (alert.getValue())
        {
        	//if 'alert' is true, we're expecting a non web message
        	LOGGER.trace("Switching to alert (doSwitchFrame)");
        	Alert popupalert = selenium.getWebdriver().switchTo().alert();
        	if (accept.getValue())
        	{
        		//accepting the message by clicking 'yes' or whatever
        		LOGGER.trace("Accepting alert (doSwitchFrame)");
        		popupalert.accept();
        	}
        	else if (!accept.getValue())
        	{
        		//dismissing the message by clicking 'no' or whatever
        		LOGGER.trace("Dismissing alert (doSwitchFrame)");
        		popupalert.dismiss();
        	}
        	else
        	{
        		//if check on boolean works properly, the else is never reached
        		throw new Exception("Value of Boolean 'alert' not valid");
        	}

        }
        else
        {
	    	if (xPath.getValue()=="DEFAULT")
	        {
	        	//switching to the default frame
	        	LOGGER.trace("Switching to frame '{}' (doSwitchFrame)", xPath);
		        selenium.getWebdriver().switchTo().defaultContent();
	        }
	        else
	        {
		        WebElement element = selenium.findElement(xPath.getValue());
		        
		        if(element == null || !element.isEnabled())
		        {
		          throw new Exception("Element '" + xPath + "' not found.");
		        }
		        
		        // switching to frame by element, selected by xpath
		        LOGGER.trace("Switching to frame '{}' (doSwitchFrame)", xPath);
		        selenium.getWebdriver().switchTo().frame(element);
	        } 
        }
        // Action succeeded. Return.
        return TestStatus.PASSED;
      }
      catch(Exception eX)
      {
        if(++count > selenium.getMaxRetries())
        {
          LOGGER.debug("Exception while switching to frame '{}' : {} (retry count: {})", 
                        xPath, eX.getMessage(), count);
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
    return("'" + this.getClass().getSimpleName().toUpperCase() + "': Switch to frame '" + xPath.getValue() + "'");
  }

  
  /**
   * Check if all requirements are met to execute this action
   */
  @Override
  public boolean isValid()
  {
    if(selenium == null)  return false;
    if(!xPath.isValid())  return false;
    if(!alert.isValid())  return false;
    if(!accept.isValid()) return false;
    
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
          if(value!=null) xPath.setValue(value); 
        break;

        case ATTR_ALERT:  
          if(value!=null) alert.setValue(value); 
        break;

        case ATTR_ACCEPT:  
          if(value!=null) accept.setValue(value); 
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
