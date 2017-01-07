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

/**
 *
 * @author Sjoerd Boerhout
 */
public class Sleep extends TestAction
{

  private final static Logger LOGGER = LogManager.getLogger(Sleep.class.
          getName());
  
  // Action attribute names
  public final static String ATTR_WAITTIME = "WAITTIME";
  
  Selenium selenium;
  private TimeOut waitTime;


  /**
   * Constructor
   */
  public Sleep(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
    
    try
    {
      // Create parameters with (optional) defaults and limits
      waitTime = new TimeOut(500);
    }
    catch(Exception ex)
    {
      LOGGER.error("Exception while creating new Sleep TestAction! " + ex.getMessage());
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
    
    LOGGER.debug(">> Sleep '{}' ms", waitTime.getValue());
    
    try
    {
      Thread.sleep(waitTime.getValue());
      
      return TestStatus.PASSED;
    }
    catch(Exception eX)
    {
      LOGGER.debug("Exception while waiting '{}' ms: {}", 
                    waitTime, eX.getMessage());
          
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
    return("'" + this.getClass().getSimpleName().toUpperCase() + "': Sleep for '" + waitTime.getValue() + "' Ms");
  }

  
  /**
  * Check if all requirements are met to execute this action
  */
  @Override
  public boolean isValid()
  {
    if(selenium == null)  return false;
    if(!waitTime.isValid()) return false;
    
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
        case ATTR_WAITTIME:
          waitTime.setValue(value); 
        break;
      }
    }
    catch (Exception ex)
    {
      LOGGER.error("Exception while setting attribute to TestAction : " + ex.getMessage());
    }
  }

}
