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
package nl.dictu.prova.plugins.output;

import java.security.InvalidParameterException;
import nl.dictu.prova.TestRunner;
import nl.dictu.prova.TestType;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestCase;

/**
 * Defines the interface for input plug-ins
 *
 * @author Sjoerd Boerhout
 */
public interface OutputPlugin
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
   * Request from test runner to shutdown. Close all open connections, files etc
   */
  public void shutDown();


  /**
   * Returns the human readable name of the input plug-in
   *
   * @return
   */
  public String getName();


  /**
   * Return the testType(s) this plug-in supports
   *
   * @return
   */
  public TestType[] getTestType();


  /**
   * Prepare to execute the given test case.
   * Actions will depend on the output test type.
   * For example:
   * - Open a new web browser
   * - Connect with a database
   * - Create a new file
   *
   * @param testCase
   */
  public void setUp(TestCase testCase);


  /**
   * Execute teardown actions for the test case.
   * Actions will depend on the output test type.
   * For example:
   * - Close the web browser
   * - Disconnect from a database
   * - Close a file
   *
   * @param testCase
   */
  public void tearDown(TestCase testCase);


  /**
   * Get a new instance of the test action with name {@link actionName}
   * Called by the input plug-in when reading test script actions
   *
   * @param actionName
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  public TestAction getTestAction(String actionName) throws
          InvalidParameterException;

}
