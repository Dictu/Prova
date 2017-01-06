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
   * Properties can be added and updated individually or as a set.
   */
  @Test
  public void testThatPropertyCanBeAddedToTheTestRunner()
  {
    try
    {
      LOGGER.debug("TC: testThatPropertyCanBeAddedToTheTestRunner");

      TestRunner prova = new Prova();

      prova.setProperty("testKey1", "testValue1");
      prova.setProperty("testKey2", "testValue2");
      prova.setProperty("testKey3", "testValue3");

      assertTrue(prova.getProperty("testKey1").equals("testValue1"));
      assertTrue(prova.getProperty("testKey2").equals("testValue2"));
      assertTrue(prova.getProperty("testKey3").equals("testValue3"));
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

      TestRunner prova = new Prova();
      Properties properties = new Properties();

      properties.setProperty("testKey1", "testValue1");
      properties.setProperty("testKey2", "testValue2");
      properties.setProperty("testKey3", "testValue3");
      prova.setProperties(properties);

      assertTrue(prova.getProperty("testKey1").equals("testValue1"));
      assertTrue(prova.getProperty("testKey2").equals("testValue2"));
      assertTrue(prova.getProperty("testKey3").equals("testValue3"));

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

      TestRunner prova = new Prova();

      prova.setProperty("testKey1", "testValue1");
      prova.setProperty("testKey2", "testValue2");
      prova.setProperty("testKey3", "testValue3");

      assertTrue(prova.getProperty("testKey1").equals("testValue1"));
      assertTrue(prova.getProperty("testKey2").equals("testValue2"));
      assertTrue(prova.getProperty("testKey3").equals("testValue3"));
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

      TestRunner prova = new Prova();

      prova.setProperty("testKey1", "testValue1");
      prova.setProperty("testKey2", "testValue2");
      prova.setProperty("testKey3", "testValue3");

      assertTrue(prova.hasProperty("testKey1"));
      assertTrue(prova.hasProperty("testKey2"));
      assertTrue(prova.hasProperty("testKey3"));

      assertFalse(prova.hasProperty("testValue1"));
      assertFalse(prova.hasProperty("testValue2"));
      assertFalse(prova.hasProperty("testValue3"));
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

      TestRunner prova = new Prova();

      assertTrue(prova.hasProperty("path.separator"));
      assertTrue(prova.hasProperty("file.separator"));
      assertTrue(prova.hasProperty("os.version"));
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

}
