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

package nl.dictu.prova;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 * @author Sjoerd Boerhout
 */
public class TestRunnerTest
{
  private final static Logger LOGGER = LogManager.getLogger(TestRunnerTest.class.getName());

  private TestRunner testRunner;


  /**
   * One-time initialization code
   */
  @BeforeClass
  public static void setUpClass()
  {
    GlobalSetup.configure();
  }


  @AfterClass
  public static void tearDownClass()
  {
  }


  @Before
  public void setUp()
  {
    try
    {
      LOGGER.debug("setUp - Create testRunner instance");

      testRunner = new testRunnerInstance(LOGGER);
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  @After
  public void tearDown()
  {
  }


  /**
   * Test of getInputPlugins method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testGetInputPlugins()
  {
  }


  /**
   * Test of getOutputPlugins method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testGetOutputPlugins_0args()
  {
  }


  /**
   * Test of getOutputPlugins method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testGetOutputPlugins_TestType()
  {
  }


  /**
   * Test of getReportingPlugins method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testGetReportingPlugins()
  {
  }


  /**
   * Test of addInputPlugin method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testAddInputPlugin()
  {
  }


  /**
   * Test of addOutputPlugin method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testAddOutputPlugin()
  {
  }


  /**
   * Test of addReportingPlugin method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testAddReportingPlugin()
  {
  }


  /**
   * Test of addTestSuite method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testAddTestSuite()
  {
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * This set of properties also contains a copy of all system wide properties.
   */
  @Test
  public void testThatSystemPropertiesInTestRunnerIsValidCollectionAfterCreation()
  {
    try
    {
      LOGGER.debug("TC: testThatSystemPropertiesInTestRunnerIsValidCollectionAfterCreation");

      Properties properties = testRunner.getProperties();

      assertNotNull(properties);
      assertTrue(properties instanceof Properties);
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * Properties can be added and updated individually or as a set.
   */
  @Test
  public void testThatPropertyCanBeAddedToTheTestRunner()
  {
    try
    {
      LOGGER.debug("TC: testThatPropertyCanBeAddedToTheTestRunner");

      testRunner.setProperty("testKey1", "testValue1");
      testRunner.setProperty("testKey2", "testValue2");
      testRunner.setProperty("testKey3", "testValue3");

      assertTrue(testRunner.getProperty("testKey1").equals("testValue1"));
      assertTrue(testRunner.getProperty("testKey2").equals("testValue2"));
      assertTrue(testRunner.getProperty("testKey3").equals("testValue3"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * Properties can be added and updated individually or as a set.
   */
  @Test
  public void testThatMultiplePropertiesCanBeAddedToTheTestRunnerAtOnce()
  {
    try
    {
      LOGGER.debug("TC: testThatMultiplePropertiesCanBeAddedToTheTestRunnerAtOnce");

      Properties properties = new Properties();

      properties.setProperty("testKey1", "testValue1");
      properties.setProperty("testKey2", "testValue2");
      properties.setProperty("testKey3", "testValue3");
      testRunner.setProperties(properties);

      assertTrue(testRunner.getProperty("testKey1").equals("testValue1"));
      assertTrue(testRunner.getProperty("testKey2").equals("testValue2"));
      assertTrue(testRunner.getProperty("testKey3").equals("testValue3"));

      assertTrue(true);
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * Properties can be added and updated individually or as a set.
   */
  @Test
  public void testThatPropertiesCanBeRetrievedFromTheTestRunner()
  {
    try
    {
      LOGGER.debug("TC: testThatPropertiesCanBeRetrievedFromTheTestRunner");

      testRunner.setProperty("testKey1", "testValue1");
      testRunner.setProperty("testKey2", "testValue2");
      testRunner.setProperty("testKey3", "testValue3");

      assertTrue(testRunner.getProperty("testKey1").equals("testValue1"));
      assertTrue(testRunner.getProperty("testKey2").equals("testValue2"));
      assertTrue(testRunner.getProperty("testKey3").equals("testValue3"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * Properties can be checked for existence.
   */
  @Test
  public void testThatPropertiesCanBeCheckedForSpecificPropertyExistence()
  {
    try
    {
      LOGGER.debug("TC: testThatPropertiesCanBeCheckedForSpecificPropertyExistence");

      testRunner.setProperty("testKey1", "testValue1");
      testRunner.setProperty("testKey2", "testValue2");
      testRunner.setProperty("testKey3", "testValue3");

      assertTrue(testRunner.hasProperty("testKey1"));
      assertTrue(testRunner.hasProperty("testKey2"));
      assertTrue(testRunner.hasProperty("testKey3"));

      assertFalse(testRunner.hasProperty("testValue1"));
      assertFalse(testRunner.hasProperty("testValue2"));
      assertFalse(testRunner.hasProperty("testValue3"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * This set of properties also contains a copy of all system wide properties.
   */
  @Test
  public void testThatSystemPropertiesExistsInTestRunner()
  {
    try
    {
      LOGGER.debug("TC: testThatSystemPropertiesExistsInTestRunner");
      
      assertTrue(testRunner.hasProperty("file.separator"));
      assertTrue(testRunner.hasProperty("line.separator"));
      assertTrue(testRunner.hasProperty("path.separator"));
      
      assertTrue(testRunner.hasProperty("os.name"));
      assertTrue(testRunner.hasProperty("os.arch"));
      assertTrue(testRunner.hasProperty("os.version"));
      assertTrue(testRunner.hasProperty("java.vm.vendor"));
      assertTrue(testRunner.hasProperty("java.runtime.version"));
      
      assertTrue(testRunner.hasProperty("user.name"));
      assertTrue(testRunner.hasProperty("user.dir"));
      assertTrue(testRunner.hasProperty("user.home"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * This set of properties has a default set of properties.
   */
  @Test
  public void testThatDefaultPropertiesAreLoadedFromResource()
  {
    try
    {
      LOGGER.debug("TC: testThatSystemPropertiesExistsInTestRunner");   
      
      assertTrue(testRunner.hasProperty("prova.root.dir"));

      assertTrue(testRunner.hasProperty("prova.conf.dir"));
      assertTrue(testRunner.hasProperty("prova.conf.file.pfx"));
      assertTrue(testRunner.hasProperty("prova.conf.file.default"));
      assertTrue(testRunner.hasProperty("prova.conf.file.test"));
      assertTrue(testRunner.hasProperty("prova.conf.file.user"));
      assertTrue(testRunner.hasProperty("prova.conf.file.ext"));
      
      assertTrue(testRunner.hasProperty("prova.log.level"));
      assertTrue(testRunner.hasProperty("prova.log.dir.root"));
      assertTrue(testRunner.hasProperty("prova.log.dir.history"));
      assertTrue(testRunner.hasProperty("prova.log.filename"));
      assertTrue(testRunner.hasProperty("prova.log.ext.txt"));
      assertTrue(testRunner.hasProperty("prova.log.pattern.console"));
      assertTrue(testRunner.hasProperty("prova.log.pattern.file"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * This set of properties has a default set of properties. These properties
   * have a default value.
   */
  @Test
  public void testThatSystemPropertiesHaveExpectedValues()
  {
    try
    {
      LOGGER.debug("TC: testThatDefaultPropertiesFromResourceHaveCorrectValues");

      assertTrue(testRunner.getProperty(Property.SYSTEM_FILE_SEPARATOR).equals(System.getProperty("file.separator")));
      assertTrue(testRunner.getProperty(Property.SYSTEM_LINE_SEPARATOR).equals(System.getProperty("line.separator")));
      assertTrue(testRunner.getProperty(Property.SYSTEM_PATH_SEPARATOR).equals(System.getProperty("path.separator")));
      
      assertTrue(testRunner.getProperty(Property.SYSTEM_OS_NAME).equals(System.getProperty("os.name")));
      assertTrue(testRunner.getProperty(Property.SYSTEM_OS_ARCHITECTURE).equals(System.getProperty("os.arch")));
      assertTrue(testRunner.getProperty(Property.SYSTEM_OS_VERSION).equals(System.getProperty("os.version")));
      assertTrue(testRunner.getProperty(Property.SYSTEM_JAVA_VM_VENDOR).equals(System.getProperty("java.vm.vendor")));
      assertTrue(testRunner.getProperty(Property.SYSTEM_JAVA_RUNTIME_VERSION).equals(System.getProperty("java.runtime.version")));
      
      assertTrue(testRunner.getProperty(Property.SYSTEM_USER_NAME).equals(System.getProperty("user.name")));
      assertTrue(testRunner.getProperty(Property.SYSTEM_USER_DIR).equals(System.getProperty("user.dir")));
      assertTrue(testRunner.getProperty(Property.SYSTEM_USER_HOME).equals(System.getProperty("user.home")));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-9: Support for properties
   * Requirement:
   * A test runner has a set of properties to configure its behavior.
   * This set of properties has a default set of properties. These properties
   * have a default value.
   */
  @Test
  public void testThatDefaultPropertiesFromResourceHaveCorrectValues()
  {
    try
    {
      LOGGER.debug("TC: testThatDefaultPropertiesFromResourceHaveCorrectValues");

      assertTrue(testRunner.getProperty(Property.PROVA_CORE_ROOT_DIR).equals(""));

      assertTrue(testRunner.getProperty(Property.PROVA_CONF_DIR).equals("config"));
      assertTrue(testRunner.getProperty(Property.PROVA_CONF_FILE_PFX).equals("prova_"));
      assertTrue(testRunner.getProperty(Property.PROVA_CONF_FILE_DEF).equals("default"));
      assertTrue(testRunner.getProperty(Property.PROVA_CONF_FILE_TEST).equals("-test"));
      assertTrue(testRunner.getProperty(Property.PROVA_CONF_FILE_USER).equals(""));
      assertTrue(testRunner.getProperty(Property.PROVA_CONF_FILE_EXT).equals("prop"));


      assertTrue(testRunner.getProperty(Property.PROVA_LOG_LEVEL).equals("info"));
      assertTrue(testRunner.getProperty(Property.PROVA_LOG_ROOT).equals("log"));
      assertTrue(testRunner.getProperty(Property.PROVA_LOG_HISTORY)
                           .equals("$${date:yyyy-MM-dd}-%i"));
      assertTrue(testRunner.getProperty(Property.PROVA_LOG_FILENAME).equals("Prova"));
      assertTrue(testRunner.getProperty(Property.PROVA_LOG_EXT_TXT).equals("log"));
      assertTrue(testRunner.getProperty(Property.PROVA_LOG_PATTERN_CONSOLE)
                           .equals("%d{HH:mm:ss} %-5p - %msg%n"));
      assertTrue(testRunner.getProperty(Property.PROVA_LOG_PATTERN_FILE)
                           .equals("%d{yyyy-MM-dd HH:mm:ss,SSS} [%c:%t:%L] %-5p - %msg%n"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled()) eX.printStackTrace();

      fail(eX.getMessage());
    }
  }


  /**
   * Test of start method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testStart()
  {
  }


  /**
   * Test of join method, of class TestRunner.
   */
  @Test
  @Ignore
  public void testJoin()
  {
  }

  /**
   * Implementation of abstract class TestRunner for testing purpose
   * 
   * @author Sjoerd Boerhout
   */
  private class testRunnerInstance extends TestRunner
  {

    protected testRunnerInstance(Logger newLogger) throws Exception
    {
      super(newLogger);
    }

  }
}
