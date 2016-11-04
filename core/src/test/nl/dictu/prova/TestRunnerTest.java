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
package nl.dictu.prova;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import nl.dictu.prova.framework.TestSuite;
import nl.dictu.prova.plugins.input.InputPlugin;
import nl.dictu.prova.plugins.output.OutputPlugin;
import nl.dictu.prova.plugins.reporting.ReportingPlugin;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sjoerd Boerhout
 */
public class TestRunnerTest
{

  public TestRunnerTest()
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
   * Test of getInputPlugins method, of class TestRunner.
   */
  @Test
  public void testGetInputPlugins()
  {
  }


  /**
   * Test of getOutputPlugins method, of class TestRunner.
   */
  @Test
  public void testGetOutputPlugins_0args()
  {
  }


  /**
   * Test of getOutputPlugins method, of class TestRunner.
   */
  @Test
  public void testGetOutputPlugins_TestType()
  {
  }


  /**
   * Test of getReportingPlugins method, of class TestRunner.
   */
  @Test
  public void testGetReportingPlugins()
  {
  }


  /**
   * Test of addInputPlugin method, of class TestRunner.
   */
  @Test
  public void testAddInputPlugin()
  {
  }


  /**
   * Test of addOutputPlugin method, of class TestRunner.
   */
  @Test
  public void testAddOutputPlugin()
  {
  }


  /**
   * Test of addReportingPlugin method, of class TestRunner.
   */
  @Test
  public void testAddReportingPlugin()
  {
  }


  /**
   * Test of addTestSuite method, of class TestRunner.
   */
  @Test
  public void testAddTestSuite()
  {
  }


  /**
   * Test of setProperty method, of class TestRunner.
   */
  @Test
  public void testSetProperty()
  {
  }


  /**
   * Test of hasProperty method, of class TestRunner.
   */
  @Test
  public void testHasProperty()
  {
  }


  /**
   * Test of getProperty method, of class TestRunner.
   */
  @Test
  public void testGetProperty()
  {
  }


  /**
   * Test of start method, of class TestRunner.
   */
  @Test
  public void testStart()
  {
  }


  /**
   * Test of join method, of class TestRunner.
   */
  @Test
  public void testJoin()
  {
  }

  public class TestRunnerImpl implements TestRunner
  {

    public LinkedList<InputPlugin> getInputPlugins()
    {
      return null;
    }


    public LinkedList<OutputPlugin> getOutputPlugins()
    {
      return null;
    }


    public LinkedList<OutputPlugin> getOutputPlugins(TestType testType)
    {
      return null;
    }


    public LinkedList<ReportingPlugin> getReportingPlugins()
    {
      return null;
    }


    public void addInputPlugin(InputPlugin inputPlugin)
    {
    }


    public void addOutputPlugin(OutputPlugin outputPlugin, TestType testType)
    {
    }


    public void addReportingPlugin(ReportingPlugin reportingPlugin)
    {
    }


    public void addTestSuite(TestSuite testSuite, InputPlugin inputPlugin)
    {
    }


    public void setProperty(String key, String value) throws
                                                             NullPointerException
    {
    }


    public boolean hasProperty(String key)
    {
      return false;
    }


    public String getProperty(String key) throws InvalidParameterException
    {
      return "";
    }


    public void start()
    {
    }


    public void join()
    {
    }
  }

}
