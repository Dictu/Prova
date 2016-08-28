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
package nl.dictu.prova.plugins.reporting;

import nl.dictu.prova.TestRunner;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestCase;
import nl.dictu.prova.framework.TestSuite;

/**
 *
 * @author Sjoerd Boerhout
 */
public interface ReportingPlugin
{

  /**
   * Initialize the plug-in and provide the instance of the test runner
   *
   * @param testRunner
   *
   * @throws Exception
   */
  public void init(TestRunner testRunner) throws Exception;


  /**
   * Set the location where the report messages will be saved.
   * Depending on the plug-in this could be a directory name, a database name or
   * whatever
   *
   * @param newOutputLocation
   *
   * @return
   *
   * @throws IllegalArgumentException
   */
  public String setOutputLocation(String newOutputLocation) throws
          IllegalArgumentException;


  /**
   * Set the project name. The reporting plug-in will use this name in the
   * reports for easier identifying the test run.
   *
   * @param projectName
   *
   * @return
   *
   * @throws IllegalArgumentException
   */
  public String setProjectName(String projectName) throws
          IllegalArgumentException;


  /**
   * Prepare the plug-in to start saving reports.
   * Actions will depend on the type of the plug-in.
   * For example:
   * - Open a new log file
   * - Create some records in a database
   * - Open a (network) connection to a (remote) host
   *
   * @throws Exception
   */
  public void setUp() throws Exception;


  /**
   * Execute shutdown actions for the plug-in.
   * Actions will depend on the output test type.
   * For example:
   * - Close a file
   * - Disconnect from a database
   * - Close a (network) connection
   *
   * @throws Exception
   */
  public void shutDown() throws Exception;


  /**
   * Returns the human readable name of the input plug-in
   *
   * @return
   */
  public String getName();


  /**
   * Triggered by the test runner when the test run of the given test suite is
   * about to begin.
   *
   * @param testSuite
   */
  public void logStartTestSuite(TestSuite testSuite);


  /**
   * Triggered by the test runner when the test run of the given test case is
   * about to begin.
   *
   * @param testCase
   */
  public void logStartTestCase(TestCase testCase);


  /**
   * Triggered by the test runner after the given setup action is executed
   *
   * @param setUpAction
   */
  public void logSetupAction(TestAction setUpAction);


  /**
   * Triggered by the test runner after the given test action is executed
   *
   * @param testAction
   */
  public void logTestAction(TestAction testAction);


  /**
   * Triggered by the test runner after the given teardown action is executed
   *
   * @param tearDownAction
   */
  public void logTearDownAction(TestAction tearDownAction);


  /**
   * Triggered by the test runner after finishing the execution of the given
   * test case
   *
   * @param testCase
   */
  public void logEndTestCase(TestCase testCase);


  /**
   * Triggered by the test runner after finishing the execution of the given
   * test suite
   *
   * @param testSuite
   */
  public void logEndTestSuite(TestSuite testSuite);


  /**
   * Triggered by the test runner after finishing the execution of all test
   * suites
   *
   * @param testSuite
   */
  public void logTestRunSummary(TestSuite testSuite);


  /**
   * Gives the opportunity to log an (extra) message
   *
   * @param message
   * @param testSuite
   */
  public void logMessage(String message, TestSuite testSuite);


  /**
   * Gives the opportunity to log an (extra) message
   *
   * @param message
   * @param testCase
   */
  public void logMessage(String message, TestCase testCase);


  /**
   * Gives the opportunity to log an (extra) message
   *
   * @param message
   * @param testAction
   */
  public void logMessage(String message, TestAction testAction);

}
