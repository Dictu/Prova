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
package nl.dictu.prova.plugins.reporting;

import nl.dictu.prova.TestRunner;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestCase;
import nl.dictu.prova.framework.TestSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Sjoerd Boerhout
 */
public class ReportingPluginTest
{

  public ReportingPluginTest()
  {
  }


  @BeforeClass
  public static void setUpClass()
  {
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
   * Test of init method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testInit() throws Exception
  {
  }


  /**
   * Test of setOutputLocation method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testSetOutputLocation()
  {
  }


  /**
   * Test of setProjectName method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testSetProjectName()
  {
  }


  /**
   * Test of setUp method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testSetUp() throws Exception
  {
  }


  /**
   * Test of shutDown method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testShutDown() throws Exception
  {
  }


  /**
   * Test of getName method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testGetName()
  {
  }


  /**
   * Test of logStartTestSuite method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogStartTestSuite()
  {
  }


  /**
   * Test of logStartTestCase method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogStartTestCase()
  {
  }


  /**
   * Test of logSetupAction method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogSetupAction()
  {
  }


  /**
   * Test of logTestAction method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogTestAction()
  {
  }


  /**
   * Test of logTearDownAction method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogTearDownAction()
  {
  }


  /**
   * Test of logEndTestCase method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogEndTestCase()
  {
  }


  /**
   * Test of logEndTestSuite method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogEndTestSuite()
  {
  }


  /**
   * Test of logTestRunSummary method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogTestRunSummary()
  {
  }


  /**
   * Test of logMessage method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogMessage_String_TestSuite()
  {
  }


  /**
   * Test of logMessage method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogMessage_String_TestCase()
  {
  }


  /**
   * Test of logMessage method, of class ReportingPlugin.
   */
  @Test
  @Ignore
  public void testLogMessage_String_TestAction()
  {
  }

  public class ReportingPluginImpl implements ReportingPlugin
  {

    public void init(TestRunner testRunner) throws Exception
    {
    }


    public String setOutputLocation(String newOutputLocation) throws
                                                                     IllegalArgumentException
    {
      return "";
    }


    public String setProjectName(String projectName) throws
                                                            IllegalArgumentException
    {
      return "";
    }


    public void setUp() throws Exception
    {
    }


    public void shutDown() throws Exception
    {
    }


    public String getName()
    {
      return "";
    }


    public void logStartTestSuite(TestSuite testSuite)
    {
    }


    public void logStartTestCase(TestCase testCase)
    {
    }


    public void logSetupAction(TestAction setUpAction)
    {
    }


    public void logTestAction(TestAction testAction)
    {
    }


    public void logTearDownAction(TestAction tearDownAction)
    {
    }


    public void logEndTestCase(TestCase testCase)
    {
    }


    public void logEndTestSuite(TestSuite testSuite)
    {
    }


    public void logTestRunSummary(TestSuite testSuite)
    {
    }


    public void logMessage(String message, TestSuite testSuite)
    {
    }


    public void logMessage(String message, TestCase testCase)
    {
    }


    public void logMessage(String message, TestAction testAction)
    {
    }
  }

}
