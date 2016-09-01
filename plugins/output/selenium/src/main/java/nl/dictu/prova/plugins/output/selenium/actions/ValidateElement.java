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
public class ValidateElement extends TestAction
{

  private final static Logger LOGGER = LogManager.getLogger(
          ValidateElement.class.getName());

  // Action attribute names
  public final static String ATTR_XPATH    = "XPATH";
  public final static String ATTR_EXISTS   = "EXISTS";
  public final static String ATTR_TIMEOUT  = "TIMEOUT";
  
  Selenium selenium;
  private Xpath   xPath;
  private Bool    exists;
  private TimeOut timeOut;

  /**
   * Constructor
   */
  public ValidateElement(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
  }


  /**
   * Execute this action
   */
  @Override
  public TestStatus execute()
  {
    throw new UnsupportedOperationException("ValidateElement not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  /**
   * Return a string representation of the objects content
   * 
   * @return 
   */
  @Override
  public String toString()
  {
    return("'" + this.getClass().getSimpleName().toUpperCase() + "': Validate that element '" + xPath.getValue() + "' " +
           (exists.getValue() ? "does" : "doesn't ") + "exist. " +
           "TimeOut: " + timeOut.getValue());
  }

  
  /**
   * Check if all requirements are met to execute this action
   */
  @Override
  public boolean isValid()
  {
    if(selenium == null)  return false;
    if(!xPath.isValid())    return false;
    if(!exists.isValid())   return false;
    if(!timeOut.isValid())  return false;
    
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
    LOGGER.trace("Request to set '{}' to '{}'", () -> key, () -> value);
    try
    {
      switch(key.toUpperCase())
      {
        case ATTR_XPATH:  
          xPath.setValue(value); 
        break;

        case ATTR_PARAMETER:
        case ATTR_EXISTS:
          exists.setValue(value); 
        break;

        case ATTR_TIMEOUT:
          timeOut.setValue(value); 
        break;
      }
      xPath.setAttribute(key, value);
    }
    catch(Exception ex)
    {
      LOGGER.error("Exception while setting attribute to TestAction : " + ex.getMessage());
    }
  }

}
