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
import org.openqa.selenium.NoSuchElementException;

/**
 *
 * @author Sjoerd Boerhout
 */
public class SelectDropDown extends TestAction
{
  
  private final static Logger LOGGER = LogManager.getLogger(
          SelectDropDown.class.getName());
  
      // Action attribute names
  public final static String ATTR_XPATH  = "XPATH";
  public final static String ATTR_SELECT = "SELECT";
  
  private Selenium selenium;
  private Xpath xPath;
  private Text select;


  /**
   * Constructor
   */
  public SelectDropDown(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
    xPath = new Xpath();
    select = new Text();
  }


/**
   * Execute this action
   */  
  @Override
  public TestStatus execute()
  {
    LOGGER.debug("> Select '{}' on {}",select , xPath);
    int count = 0;
    
    if(!isValid())
    {
      LOGGER.error("Action is not validated!");
      return TestStatus.FAILED;
    }
    
    while(true)
    {
      try
      {
        Select dropdown = new Select(selenium.findElement(xPath.getValue()));
        
        LOGGER.trace("Element '" + xPath + "' found.");
      
        try
        {
	        LOGGER.trace("Trying to select '{}' ByVisibleText (doSelectDropdown)", select.getValue());
        	// Set dropdown by visible text
	        dropdown.selectByVisibleText(select.getValue());
        }
        catch(NoSuchElementException ex)
        {
        	LOGGER.trace("'{}' not found ByVisibleText (doSelectDropdown)", select.getValue());
        	LOGGER.trace("Trying to select '{}' ByValue (doSelectDropdown)", select.getValue());
        	// Set dropdown by value
	        try
	        {
	        	dropdown.selectByValue(select.getValue());
	        }
	        catch(NoSuchElementException nseex)
	        {
	        	LOGGER.trace("'{}' not found ByValue (doSelectDropdown)", select.getValue());
	        	throw new Exception("'" + select.getValue() + "' can not be selected as 'text' or as 'value' in element: '" + xPath.getValue() + "'");
	        }
        }
        
        return TestStatus.PASSED;
      }
      catch(Exception eX)
      {
        if(++count > selenium.getMaxRetries())
        {
          LOGGER.debug("Exception while selecting '{}': {} (retry count: {})", xPath, eX.getMessage(), count);
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
    return("'" + this.getClass().getSimpleName().toUpperCase() + "': " + select.getValue() + " '" + xPath.getValue() + "'");
  }
  
  
  /**
  * Check if all requirements are met to execute this action
  */
  @Override
  public boolean isValid()
  {
    if(selenium == null)    return false;
    if(!xPath.isValid())    return false;
    if(!select.isValid())   return false;
    
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
        case ATTR_SELECT:
          select.setValue(value); 
        break;
      }
    }
    catch (Exception ex)
    {
      LOGGER.error("Exception while setting attribute to TestAction for key " + key);
      ex.printStackTrace();
    }
  }

}
