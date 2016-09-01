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
public class SendKeys extends TestAction
{

  private final static Logger LOGGER = LogManager.getLogger(SendKeys.class.
          getName());
  
    // Action attribute names
  public final static String ATTR_KEYS = "KEYS";
  public final static String ATTR_XPATH = "XPATH";
  
  Selenium selenium;
  private Text keys;
  private Xpath xPath;


  /**
   * Constructor
   */
  public SendKeys(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
    
    try
    {
      keys.setMinLength(1);
      xPath.setValue("/html/body");
    }
    catch (Exception ex)
    {
      LOGGER.error("Exception while creating new SendKeys TestAction! " + ex.getMessage());
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
    
    try
    {
      LOGGER.debug(">> Send key '{}' to element (doSendKeys)", keys);
      //replace the given keys with keyboard presses
      keys.setValue(keys.getValue().replace("<DOWN>", Keys.DOWN));
      keys.setValue(keys.getValue().replace("<END>", Keys.END));
      keys.setValue(keys.getValue().replace("<ESC>", Keys.ESCAPE));
      keys.setValue(keys.getValue().replace("<HOME>", Keys.HOME));
      keys.setValue(keys.getValue().replace("<INSERT>", Keys.INSERT));
      keys.setValue(keys.getValue().replace("<LEFT>", Keys.LEFT));
      keys.setValue(keys.getValue().replace("<RETURN>", Keys.RETURN));
      keys.setValue(keys.getValue().replace("<RIGHT>", Keys.RIGHT));
      keys.setValue(keys.getValue().replace("<TAB>", Keys.TAB));
      keys.setValue(keys.getValue().replace("<UP>", Keys.UP));
      
      //if xPath is not filled, sendKeys to the active element
      if (xPath.getValue().equalsIgnoreCase("/html/body"))
      {
	      LOGGER.trace("> Send keys '{}' to active element (doSendKeys)", keys);
	      selenium.getWebdriver().switchTo().activeElement().sendKeys(keys.getValue());
      }
      //xPath is filled. Find element and send keys
      else
      {
    	  WebElement element = selenium.findElement(xPath.getValue());
          
          if(element == null || !element.isEnabled())
          {
            throw new Exception("Element '" + xPath + "' not found.");
          }
          LOGGER.trace("Sending keys to element '{}' (doSendKeys)", xPath);
          element.sendKeys(keys.getValue());
      }
      return TestStatus.PASSED;
    }
    catch(Exception eX)
    {
      LOGGER.debug("Exception while sending keys '{}'", keys);
      eX.printStackTrace();
      return TestStatus.FAILED;
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
    return("'" + this.getClass().getSimpleName().toUpperCase() + "': Send keys '" + keys.getValue() + "' to browser.");
  }

  
  /**
  * Check if all requirements are met to execute this action
  */
  @Override
  public boolean isValid()
  {
    if(selenium == null)    return false;
    if(!keys.isValid())     return false;
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
        case ATTR_KEYS:  
          keys.setValue(value); 
        break;
        case ATTR_XPATH:  
          if(value!=null) xPath.setValue(value); 
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
