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

package nl.dictu.prova;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * This class extends the TestRunner base class and acts as the central part
 * of Prova.
 * It load, initializes and run all plug-ins to load and run test scripts.
 * The test runner is configured and started by a test runner (cli, gui, etc).
 * 
 * @author Sjoerd Boerhout
 */
public class Prova extends TestRunner
{

  private final static Logger LOGGER = LogManager.getLogger(Prova.class.getName());


  /**
   * Constructor
   * - Create/initialize (local) variables
   */
  public Prova()
  {
    super(LOGGER);
    
    LOGGER.debug("Create new TestRunner '{}'", () -> Prova.class.getName());

  }


  /**
   * Initialize the test runner.
   * - Check if required items are available
   * - Load the plug-ins
   * - ...
   *
   * @throws Exception
   */
  public void init() throws Exception
  {

  }


  /**
   * Prepare everything for the execution fase:
   * - Load the structure of test suites and test cases
   * - Execute the configure (optional) projects setup script
   *
   * @throws Exception
   */
  public void setUp() throws Exception
  {

  }


  /**
   * Run the test suites
   *
   * @throws Exception
   */
  public void execute() throws Exception
  {

  }


  /**
   * Finish the test run.
   * - Execute the configure (optional) projects teardown script
   * - Log a summary
   * - ...
   *
   * @throws Exception
   */
  public void tearDown() throws Exception
  {

  }


  /**
   * Shutdown all plug-ins and end program execution
   *
   * @throws Exception
   */
  public void shutDown() throws Exception
  {

  }


}
