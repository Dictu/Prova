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

import java.io.File;
import java.nio.file.InvalidPathException;
import java.security.InvalidParameterException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sjoerd Boerhout
 */
public abstract class TestAction
{

  private static Logger LOGGER = LogManager.getLogger(TestAction.class.
          getName());

  private Integer actionId;
  private String actionName;
  private TestCase parent;

  private long startTime;
  private long endTime;
  private TestStatus status;
  private Exception lastException;

  private Properties attributes;
  private Properties returnVariables;
  private File resultFile;


  /**
   * Constructor.
   * Set the logger to the logger of the implementing class
   *
   * @param newLogger
   *
   * @throws NullPointerException
   */
  protected TestAction(Logger newLogger) throws NullPointerException
  {

    if(newLogger == null)
    {
      throw new NullPointerException("Logger instance is null");
    }

    LOGGER = newLogger;
  }


  /**
   * Set the test case this test action belongs to
   *
   * @param parent
   *
   * @throws InvalidParameterException
   */
  public void setParent(TestCase parent) throws InvalidParameterException
  {

  }


  /**
   * Update the status of this test action
   *
   * @param newStatus
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  protected TestStatus setStatus(TestStatus newStatus) throws
          InvalidParameterException
  {
    return null;
  }


  /**
   * Get the status of this test status
   *
   * @return
   */
  public TestStatus getStatus()
  {
    return null;
  }


  /**
   * Set attribute {@link key} of this action to {@link value}
   *
   * @param key
   * @param value
   *
   * @throws InvalidParameterException
   */
  public void setAttribute(String key, String value) throws
          InvalidParameterException
  {

  }


  /**
   * Check if the attribute {@link key} is set
   *
   * @param key
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  protected boolean hasAttribute(String key) throws
          InvalidParameterException
  {
    return false;
  }


  /**
   * Get the value of attribute {@link key}
   *
   * @param key
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  protected String getAttribute(String key) throws
          InvalidParameterException
  {
    return null;
  }


  /**
   * Set return value {@link key} of this action to {@link value}
   *
   * @param key
   * @param value
   *
   * @throws InvalidParameterException
   */
  public void setReturnValue(String key, String value) throws
          InvalidParameterException
  {

  }


  /**
   * Get the return values of this action
   *
   * @return
   */
  protected Properties getReturnValues()
  {
    return null;
  }


  /**
   * Set result file of this action to {@link retFile}
   *
   * @param retFile
   *
   * @throws InvalidParameterException
   */
  public void setReturnValue(File retFile) throws
          InvalidPathException
  {

  }


  /**
   * Get the result file of this action
   *
   * @return
   */
  protected File getResultFile()
  {
    return null;
  }


  /**
   * Get the last exception
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  protected Exception getLastException()
  {
    return null;
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
   * the actions.
   *
   * @return
   */
  @Override
  public abstract String toString();

}
