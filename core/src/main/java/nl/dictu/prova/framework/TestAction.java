/**
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
 * Date: 23-08-2016
 * Author(s): Sjoerd Boerhout
 * <p>
 */

package nl.dictu.prova.framework;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.security.InvalidParameterException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * A test action contains one single action. This abstract class offers both an
 * interface to the testrunner as well as an implementation of shared functions.
 * Issues:
 * - PROVA-12: Structure to handle test suites, cases and actions
 *
 * @author Sjoerd Boerhout
 */
public abstract class TestAction
{

  private static Logger LOGGER = LogManager.getLogger(TestAction.class.getName());

  private Integer id;
  private TestCase parent;
  private TestStatus status;

  private long startTime = 0;
  private long endTime = 0;
  private Exception lastException;

  private Properties attributes = new Properties();;
  private Properties returnVariables = new Properties();;
  private File resultFile;


  /**
   * Constructor.
   * Set the local logger to the logger of the implementing class
   *
   * @param newLogger
   * @throws NullPointerException
   */
  protected TestAction(Logger newLogger, Integer id) throws NullPointerException,
                                                     InvalidParameterException
  {

    if(newLogger == null)
    {
      throw new NullPointerException("Logger instance is null");
    }

    if(id == null)
    {
      throw new InvalidParameterException("Id 'null' is not allowed");
    }

    LOGGER = newLogger;
    setId(id);
    status = TestStatus.NOTRUN;
  }


  /**
   * Set the ID of this test action
   *
   * @param id
   * @throws InvalidParameterException
   */
  private void setId(Integer id) throws InvalidParameterException
  {
    LOGGER.trace("Set id of test action to '{}'", () -> id);

    if(id == null)
    {
      LOGGER.debug("Id can not be null ({})", () -> id);
      throw new InvalidParameterException("Id can not be null");
    }

    if(id < 0)
    {
      LOGGER.debug("Invalid test action Id. ({})", () -> id);
      throw new InvalidParameterException("Invalid test action Id (" + id + ")");
    }

    this.id = id;
  }


  /**
   * Return the ID of this test action
   *
   * @return
   */
  public Integer getId()
  {
    LOGGER.trace("Request for action id '{}'", () -> this.id);

    return id;
  }


  /**
   * Set the test case this test action belongs to
   *
   * @param parent
   * @throws InvalidParameterException
   */
  public void setParent(TestCase parent) throws InvalidParameterException
  {
    LOGGER.trace("Set the parent of this test action to ({})",
                 (parent == null ? "null" : parent.getId()));

    if(parent == null)
    {
      LOGGER.debug("Parent can not be null ({})", () -> id);
      throw new InvalidParameterException("Parent can not be null");
    }

    this.parent = parent;
  }


  /**
   * Get the test case this test action belongs to
   *
   * @return
   */
  public TestCase getParent()
  {
    LOGGER.trace("Get the parent of this test action to ({})",
                 (parent == null ? "null" : parent.getId()));

    return this.parent;
  }


  /**
   * Record the current time in milliseconds as the start time of this test
   * action
   */
  protected long startExecution()
  {
    startTime = System.currentTimeMillis();

    LOGGER.trace("Starting test action execution at '{}'", startTime);

    return startTime;
  }


  /**
   * Record the current time in milliseconds as the end time of this test action
   */
  protected long endExecution()
  {
    endTime = System.currentTimeMillis();

    LOGGER.trace("Stopped test action execution at '{}'", endTime);

    return endTime;
  }


  /**
   * Record the current time in milliseconds as the end time of this test action
   */
  protected long getExecutionTime() throws InvalidParameterException
  {
    LOGGER.trace("Request for execution time calculation. Start: {}ms, End: {}ms", () -> startTime,
                 () -> endTime);

    if(startTime < 1)
    {
      throw new InvalidParameterException("Start time not set");
    }

    if(endTime < 1)
    {
      throw new InvalidParameterException("End time not set");
    }

    long executionTime = (endTime - startTime);

    LOGGER.trace("Calculated test action execution time: {}ms", executionTime);

    return executionTime;
  }


  /**
   * Update the status of this test action
   *
   * @param newStatus
   * @return
   * @throws InvalidParameterException
   */
  protected TestStatus updateStatus(TestStatus testStatus) throws InvalidParameterException
  {
    LOGGER.debug("Updating testaction Status to '{}'", () -> testStatus);

    if(testStatus == null)
    {
      throw new InvalidParameterException("Testaction status 'null' not allowed!");
    }

    status = testStatus;

    return status;
  }


  /**
   * Get the status of this test status
   *
   * @return
   */
  public TestStatus getStatus()
  {
    return status;
  }


  /**
   * Set attribute {@link key} of this action to {@link value}
   *
   * @param key
   * @param value
   * @throws InvalidParameterException
   */
  public void setAttribute(String key, String value) throws InvalidParameterException
  {
    LOGGER.trace("Set value of attribute with key '{}' to '{}'", () -> key, () -> value);

    if(key == null || value == null || key.trim().length() < 1)
    {
      throw new InvalidParameterException("Invalid key or value for attribute.(" + key + ":" + value
                                          + ")");
    }

    attributes.put(key.trim(), value);
  }


  /**
   * Check if the attribute {@link key} is set
   *
   * @param key
   * @return
   * @throws InvalidParameterException
   */
  protected boolean hasAttribute(String key) throws InvalidParameterException
  {
    LOGGER.trace("Has attribute: '{}': ({})", () -> key,
                 () -> (key != null && attributes.containsKey(key)) ? attributes.getProperty(key)
                                                                    : "No");

    return attributes.containsKey(key);
  }


  /**
   * Get the value of attribute {@link key}
   *
   * @param key
   * @return
   * @throws InvalidParameterException
   */
  public String getAttribute(String key) throws InvalidParameterException
  {
    LOGGER.trace("Get value of attribute: '{}' ({})", () -> key,
                 () -> (key != null && attributes.containsKey(key)) ? attributes.getProperty(key)
                                                                    : "Not found");

    if( !attributes.containsKey(key))
    {
      throw new InvalidParameterException("No attribute with value '" + key + "' found!");
    }

    return attributes.getProperty(key);
  }


  /**
   * Get the attributes of this action
   *
   * @return
   */
  protected Properties getAttributes()
  {
    return this.attributes;
  }


  /**
   * Set return value {@link key} of this action to {@link value}
   *
   * @param key
   * @param value
   * @throws InvalidParameterException
   */
  protected void setReturnVariable(String key, String value) throws InvalidParameterException
  {
    LOGGER.trace("Set value of return value with key '{}' to '{}'", () -> key, () -> value);

    if(key == null || value == null || key.trim().length() < 1)
    {
      throw new InvalidParameterException("Invalid key or value for return value.(" + key + ":"
                                          + value + ")");
    }

    this.returnVariables.put(key.trim(), value);
  }


  /**
   * Get the return variables of this action
   *
   * @return
   */
  protected Properties getReturnVariables()
  {
    return this.returnVariables;
  }


  /**
   * Set result file of this action to {@link retFile}
   *
   * @param retFile
   * @throws InvalidParameterException
   */
  protected void setResultFile(File retFile) throws InvalidPathException
  {
    LOGGER.trace("Set result file to '{}'",
                 () -> retFile != null ? retFile.getAbsolutePath() : "null");

    this.resultFile = retFile;
  }


  /**
   * Get the result file of this action
   *
   * @return
   */
  protected File getResultFile()
  {
    return resultFile;
  }


  /**
   * Get the last exception
   *
   * @return
   * @throws InvalidParameterException
   */
  protected Exception getLastException()
  {
    return lastException;
  }


  /**
   * Execute this action
   *
   * @return
   */
  public abstract TestStatus execute();


  /**
   * Validate if this action is ready to execute
   *
   * @return
   */
  public abstract boolean isValid();


  /**
   * Return a full string representation of this test action with all details of
   * the actions for use in reporting and logging.
   *
   * @return
   */
  @Override
  public abstract String toString();

}
