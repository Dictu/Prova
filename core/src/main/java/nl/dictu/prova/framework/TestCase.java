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

/**
 *
 * @author Sjoerd Boerhout
 */
public class TestCase
{

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
   * @param newId
   *
   * @throws InvalidParameterException
   */
  public TestCase(String newId) throws InvalidParameterException
  {

  }


  /**
   * Return the ID of this test case
   *
   * @return
   */
  public String getId()
  {
    return null;
  }


  /**
   * Return the status of this test case
   *
   * @return
   */
  public TestStatus getTestStatus()
  {
    return null;
  }


  /**
   * Update the test status because of an external cause.
   * Accepted new states:
   * - Blocked
   *
   * @param newTestStatus
   * @param reason
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  public TestStatus updateTestStatus(TestStatus newTestStatus, String reason)
          throws
          InvalidParameterException
  {

    // Log update to reporting plugin(s)
    return null;
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
    return false;
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
    return null;
  }


  /**
   * Return a list of the setup actions in this test case
   *
   * @return
   */
  public LinkedList<TestAction> getSetUpActions()
  {
    return null;
  }


  /**
   * Return a list of the test actions in this test case
   *
   * @return
   */
  public LinkedList<TestAction> getTestActions()
  {
    return null;
  }


  /**
   * Return a list of the teardown actions in this test case
   *
   * @return
   */
  public LinkedList<TestAction> getTearDownActions()
  {
    return null;
  }

}
