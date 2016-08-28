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
package nl.dictu.prova.plugins.input;

import nl.dictu.prova.TestRunner;
import nl.dictu.prova.framework.TestCase;
import nl.dictu.prova.framework.TestSuite;

/**
 * Defines the interface for input plug-ins
 *
 * @author Sjoerd Boerhout
 */
public interface InputPlugin
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
   * Set the test root where the test scripts are located.
   * Depending on the plug-in this could be a directory name, a database name or
   * whatever
   *
   * @param newTestRoot
   * @param projectName
   *
   * @return
   *
   * @throws IllegalArgumentException
   */
  public String setTestRoot(String newTestRoot, String projectName) throws
          IllegalArgumentException;


  /**
   * When a test case filter is provided the input plug-in will only process
   * test scripts with one (or more) of the provided labels.
   *
   * @param labels
   *
   * @return
   *
   * @throws NullPointerException
   */
  public String setTestCaseFilter(String[] labels) throws NullPointerException;


  /**
   * Create a structure of (sub-)test suites and test cases for the given test
   * suite with respect to the (optional) given filters. Loads the headers of
   * the test cases, but not the test actions.
   *
   * @param testSuite
   *
   * @return
   *
   * @throws NullPointerException
   */
  public TestSuite setUp(TestSuite testSuite) throws NullPointerException;


  /**
   * Load all actions for the given test case.
   *
   * @param testCase
   *
   * @return
   *
   * @throws NullPointerException
   */
  public TestCase loadTestCase(TestCase testCase) throws NullPointerException;


  /**
   * Request from test runner to shutdown. Close all open connections, files etc
   */
  public void shutDown();


  /**
   * Returns the human readable name of the input plug-in
   *
   * @return
   */
  public String getName();

}
