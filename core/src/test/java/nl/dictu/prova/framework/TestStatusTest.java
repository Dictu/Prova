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
 * Date:      27-08-2016
 * Author(s): Sjoerd Boerhout
 * <p>
 */
package nl.dictu.prova.framework;

import nl.dictu.prova.GlobalSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * PROVA-12: Structure to handle test suites, cases and actions
 * <p>
 * @author Sjoerd Boerhout
 */
public class TestStatusTest
{
  /*
   * NOTRUN("NotRun")
   * BLOCKED("Blocked")
   * PASSED("Passed")
   * FAILED("Failed")
   */

  private final static Logger LOGGER = LogManager.getLogger(
          TestStatusTest.class.getName());

  private final int NUMBER_OF_STATES = 4;

  /*
   * One-time initialization code
   */
  @BeforeClass
  public static void setUpClass()
  {
    GlobalSetup.configure();
  }


  @Test
  public void checkNumberOfEnums()
  {
    LOGGER.debug("checkNumberOfEnums");

    assertTrue(TestStatus.values().length == NUMBER_OF_STATES);
  }


  @Test
  public void checkNotRun()
  {
    LOGGER.debug("checkNotRun");

    assertTrue(TestStatus.NOTRUN.getValue().equals("NotRun"));
    assertTrue(TestStatus.lookup("notrun").name().equals("NOTRUN"));
    assertTrue(TestStatus.lookup("NOTRUN").name().equals("NOTRUN"));
    assertTrue(TestStatus.lookup("NotRun").toString().equals("NotRun"));
  }


  @Test
  public void checkBlocked()
  {
    LOGGER.debug("checkBlocked");

    assertTrue(TestStatus.BLOCKED.getValue().equals("Blocked"));
    assertTrue(TestStatus.lookup("blocked").name().equals("BLOCKED"));
    assertTrue(TestStatus.lookup("BLOCKED").name().equals("BLOCKED"));
    assertTrue(TestStatus.lookup("Blocked").toString().equals("Blocked"));
  }


  @Test
  public void checkPassed()
  {
    LOGGER.debug("checkPassed");

    assertTrue(TestStatus.PASSED.getValue().equals("Passed"));
    assertTrue(TestStatus.lookup("passed").name().equals("PASSED"));
    assertTrue(TestStatus.lookup("PASSED").name().equals("PASSED"));
    assertTrue(TestStatus.lookup("Passed").toString().equals("Passed"));
  }


  @Test
  public void checkFailed()
  {
    LOGGER.debug("checkFailed");

    assertTrue(TestStatus.FAILED.getValue().equals("Failed"));
    assertTrue(TestStatus.lookup("failed").name().equals("FAILED"));
    assertTrue(TestStatus.lookup("FAILED").name().equals("FAILED"));
    assertTrue(TestStatus.lookup("Failed").toString().equals("Failed"));
  }

}
