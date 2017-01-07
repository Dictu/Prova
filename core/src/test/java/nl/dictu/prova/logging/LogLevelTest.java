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
 * Date: 27-08-2016
 * Author(s): Sjoerd Boerhout
 * <p>
 */

package nl.dictu.prova.logging;

import nl.dictu.prova.GlobalSetup;
import nl.dictu.prova.framework.TestAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * PROVA-8: Option to log program execution with different log levels
 * <p>
 * FATAL: 0
 * ERROR: 1
 * WARN: 2
 * INFO: 3
 * DEBUG: 4
 * TRACE: 5
 *
 * @author Sjoerd Boerhout
 */
public class LogLevelTest
{

  private final static Logger LOGGER = LogManager.getLogger(TestAction.class.getName());

  private final int NUMBER_OF_STATES = 6;


  /**
   * One-time initialization code
   */
  @BeforeClass
  public static void setUpClass()
  {
    GlobalSetup.configure();
  }


  /**
   * Validate the total number of log levels
   */
  @Test
  public void testNumberOfLogLevelEnums()
  {
    LOGGER.debug("TC: testNumberOfLogLevelEnums");

    assertTrue(LogLevel.values().length == NUMBER_OF_STATES);
  }


  /**
   * Validate the priority of the log level enums
   */
  @Test
  public void testLogLevelsOrder()
  {
    LOGGER.debug("TC: testLogLevelsOrder");

    assertTrue(LogLevel.FATAL.getValue() == 0);
    assertTrue(LogLevel.ERROR.getValue() == 1);
    assertTrue(LogLevel.WARN.getValue() == 2);
    assertTrue(LogLevel.INFO.getValue() == 3);
    assertTrue(LogLevel.DEBUG.getValue() == 4);
    assertTrue(LogLevel.TRACE.getValue() == 5);
  }


  /**
   * Validate the Fatal log level
   */
  @Test
  public void testLogLevelFatal()
  {
    LOGGER.debug("TC: testLogLevelFatal");

    assertTrue(LogLevel.lookup("fatal").name().equals("FATAL"));
    assertTrue(LogLevel.lookup("FaTaL").name().equals("FATAL"));
    assertTrue(LogLevel.lookup("FATAL").name().equals("FATAL"));
    assertTrue(LogLevel.lookup("FATAL").toString().equals("FATAL"));
    assertTrue(LogLevel.lookup("FATAL").getName().equals("FATAL"));
  }


  /**
   * Validate the Error log level
   */
  @Test
  public void testLogLevelError()
  {
    LOGGER.debug("TC: testLogLevelError");

    assertTrue(LogLevel.lookup("error").name().equals("ERROR"));
    assertTrue(LogLevel.lookup("ErRoR").name().equals("ERROR"));
    assertTrue(LogLevel.lookup("ERROR").name().equals("ERROR"));
    assertTrue(LogLevel.lookup("ERROR").toString().equals("ERROR"));
    assertTrue(LogLevel.lookup("ERROR").getName().equals("ERROR"));
  }


  /**
   * Validate the Warning log level
   */
  @Test
  public void testLogLevelWarning()
  {
    LOGGER.debug("TC: testLogLevelWarning");

    assertTrue(LogLevel.lookup("warn").name().equals("WARN"));
    assertTrue(LogLevel.lookup("WaRn").name().equals("WARN"));
    assertTrue(LogLevel.lookup("WARN").name().equals("WARN"));
    assertTrue(LogLevel.lookup("WARN").toString().equals("WARN"));
    assertTrue(LogLevel.lookup("WARN").getName().equals("WARN"));
  }


  /**
   * Validate the Info log level
   */
  @Test
  public void testLogLevelInfo()
  {
    LOGGER.debug("TC: testLogLevelInfo");

    assertTrue(LogLevel.lookup("info").name().equals("INFO"));
    assertTrue(LogLevel.lookup("InFo").name().equals("INFO"));
    assertTrue(LogLevel.lookup("INFO").name().equals("INFO"));
    assertTrue(LogLevel.lookup("INFO").toString().equals("INFO"));
    assertTrue(LogLevel.lookup("INFO").getName().equals("INFO"));
  }


  /**
   * Validate the Debug log level
   */
  @Test
  public void testLogLevelDebug()
  {
    LOGGER.debug("TC: testLogLevelDebug");

    assertTrue(LogLevel.lookup("debug").name().equals("DEBUG"));
    assertTrue(LogLevel.lookup("DeBuG").name().equals("DEBUG"));
    assertTrue(LogLevel.lookup("DEBUG").name().equals("DEBUG"));
    assertTrue(LogLevel.lookup("DEBUG").toString().equals("DEBUG"));
    assertTrue(LogLevel.lookup("DEBUG").getName().equals("DEBUG"));
  }


  /**
   * Validate the Trace log level
   */
  @Test
  public void testLogLevelTrace()
  {
    LOGGER.debug("TC: testLogLevelTrace");

    assertTrue(LogLevel.lookup("trace").name().equals("TRACE"));
    assertTrue(LogLevel.lookup("TrAcE").name().equals("TRACE"));
    assertTrue(LogLevel.lookup("TRACE").name().equals("TRACE"));
    assertTrue(LogLevel.lookup("TRACE").getName().equals("TRACE"));
  }

}
