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
package nl.dictu.prova.framework;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sjoerd Boerhout
 */
public class TestCase
{
  private final static Logger LOGGER = LogManager.getLogger(TestCase.class.getName());

  private String id;
  private TestStatus testStatus;

  private Properties headers;
  private Properties variables;

  private LinkedList<TestAction> setUpActions;
  private LinkedList<TestAction> testActions;
  private LinkedList<TestAction> tearDownActions;


  /**
   * Constructor. Provided ID must be unique and is an identifier for the input
   * plug-in to locate the test case
   *
   * @param id
   *
   * @throws InvalidParameterException
   */
  public TestCase(String id) throws InvalidParameterException
  {
    LOGGER.debug("Creation of new testcase with test id '{}'", () -> id);

    setId(id);
  }


  /**
   * Set the ID of this test case
   *
   * @param id
   *
   * @throws InvalidParameterException
   */
  private void setId(String id) throws InvalidParameterException
  {
    LOGGER.trace("Set id of test case to '{}'", () -> id);

    if(id == null)
    {
      LOGGER.debug("Id can not be null ({})", () -> id);
      throw new InvalidParameterException("Id can not be null");
    }

    if(id.trim().length() < 1)
    {
      LOGGER.debug("Invalid testcase Id ({})", () -> id);
      throw new InvalidParameterException("Invalid testcase Id (" + id + ")");
    }

    this.id = id.trim();
  }


  /**
   * Return the ID of this test case
   *
   * @return
   */
  public String getId()
  {
    LOGGER.trace("Request for test id '{}'", () -> this.id);
    
    return id;
  }


  /**
   * Return the status of this test case
   *
   * @return
   */
  public TestStatus getTestStatus()
  {
    LOGGER.debug("Request for test status '{}'", () -> testStatus);
    
    return testStatus;
  }


  /**
   * Update the test status because of an external cause.
   * Accepted new states:
   * - Blocked
   *
   * @param testStatus
   * @param reason
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  public TestStatus updateTestStatus(TestStatus testStatus, String reason)
          throws
          InvalidParameterException
  {
    LOGGER.debug("Updating testStatus to '{}' with reason '{}'",
                 () -> testStatus, () -> reason);

    if(testStatus == null)
    {
      throw new InvalidParameterException("TestStatus null not allowed!");
    }

    this.testStatus = testStatus;
    
    return this.testStatus;
  }


  /**
   * Add the given {@link setUpAction} to this test case
   *
   * @param setUpAction
   *
   * @throws InvalidParameterException
   */
  public void addSetUpAction(TestAction setUpAction) throws
          InvalidParameterException
  {
    LOGGER.debug("Add setup action '{}'",
                 () -> setUpAction == null ? "null" : setUpAction.toString());

    if(setUpAction == null)
    {
      throw new InvalidParameterException("Setup action can not be 'null'");
    }

    setUpActions.add(setUpAction);
  }


  /**
   * Add the given {@link testAction} to this test case
   *
   * @param testAction
   *
   * @throws InvalidParameterException
   */
  public void addTestAction(TestAction testAction) throws
          InvalidParameterException
  {
    LOGGER.debug("Add test action '{}'",
                 () -> testAction == null ? "null" : testAction.toString());

    if(testAction == null)
    {
      throw new InvalidParameterException("Setup action can not be 'null'");
    }
    
    testActions.add(testAction);
  }


  /**
   * Add the given {@link tearDownAction} to this test case
   *
   * @param tearDownAction
   *
   * @throws InvalidParameterException
   */
  public void addTearDownAction(TestAction tearDownAction) throws
          InvalidParameterException
  {
    LOGGER.debug("Add teardown action '{}'",
                 () -> tearDownAction == null ? "null" : tearDownAction.
                         toString());

    if(tearDownAction == null)
    {
      throw new InvalidParameterException("Setup action can not be 'null'");
    }
    
    tearDownActions.add(tearDownAction);
  }


  /**
   * Set or update the given header {@link key} with {@link value}
   *
   * @param key
   * @param value
   *
   * @throws InvalidParameterException
   */
  public void setHeader(String key, String value) throws
          InvalidParameterException
  {
    LOGGER.trace("Set value of header with key '{}' to '{}'", () -> key, () -> value);

    if(key == null || value == null)
    {
      throw new InvalidParameterException(
              "Invalid key or value for header.(" + key + ":" + value + ")");
    }

    headers.put(key, value);
  }


  /**
   * Check if the given header {@link key} is set in the test case
   *
   * @param key
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  public boolean hasHeader(String key) throws
          InvalidParameterException
  {
    LOGGER.trace("Has header: '{}': ({})", 
                  () -> key, 
                  () -> headers.containsKey(key) ? headers.getProperty(key) : "No");
    
    return headers.containsKey(key);
  }


  /**
   * Return the {@link value} of the given header {@link key}
   *
   * @param key
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  public String getHeader(String key) throws
          InvalidParameterException
  {
    LOGGER.trace("Get value of header: '{}' ({})", 
                  () -> key, 
                  () -> headers.containsKey(key) ? headers.getProperty(key) : "Not found");
    
    if(!headers.containsKey(key))
    {
      throw new InvalidParameterException("No header with value '" + key + "' found!");
    }

    return headers.getProperty(key);
  }


  /**
   * Return a list of the setup actions in this test case
   *
   * @return
   */
  public LinkedList<TestAction> getSetUpActions()
  {
    LOGGER.trace("Request for all setup actions (size: {})",
                 () -> setUpActions.size());
    
    return setUpActions;
  }


  /**
   * Return a list of the test actions in this test case
   *
   * @return
   */
  public LinkedList<TestAction> getTestActions()
  {
    LOGGER.trace("Request for all test actions (size: {})",
                 () -> testActions.size());
    
    return testActions;
  }


  /**
   * Return a list of the teardown actions in this test case
   *
   * @return
   */
  public LinkedList<TestAction> getTearDownActions()
  {
    LOGGER.trace("Request for all teardown actions (size: {})",
                 () -> tearDownActions.size());
    
    return tearDownActions;
  }
  
  
 /**
   * Set or update the given variable {@link key} with {@link value}
   *
   * @param key
   * @param value
   *
   * @throws InvalidParameterException
   */
  public void setVariable(String key, String value) throws InvalidParameterException
  {
    LOGGER.trace("Set value of variable with key '{}' to '{}'", () -> key, () -> value);

    if(key == null || value == null)
    {
      throw new InvalidParameterException(
              "Invalid key or value for variable.(" + key + ":" + value + ")");
    }

    variables.put(key, value);
  }

  
  /**
   * Check if the given variable {@link key} is set in the test case
   *
   * @param key
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  public boolean hasVariable(String key) throws InvalidParameterException
  {
    LOGGER.trace("Get value of variable: '{}' ({})", 
                  () -> key, 
                  () -> variables.containsKey(key) ? variables.getProperty(key) : "Not found");

    if(key == null)
    {
      throw new InvalidParameterException("Key 'null' not allowed");
    }

    return variables.containsKey(key);
  }

  
   /**
   * Return the {@link value} of the given variable {@link key}
   *
   * @param key
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  public String getVariable(String key) throws InvalidParameterException
  {
     LOGGER.trace("Get value of variable: '{}' ({})", 
                  () -> key, 
                  () -> variables.containsKey(key) ? variables.getProperty(key) : "Not found");
    
    if(!variables.containsKey(key))
      throw new InvalidParameterException("No variable with value '" + key + "' found!");
    
    return variables.getProperty(key);
  }

}
