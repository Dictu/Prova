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
package nl.dictu.prova.plugins.input;

import nl.dictu.prova.TestRunner;
import nl.dictu.prova.framework.TestCase;
import nl.dictu.prova.framework.TestSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sjoerd Boerhout
 */
public class InputPluginTest
{

  public InputPluginTest()
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
   * Test of init method, of class InputPlugin.
   */
  @Test
  public void testInit() throws Exception
  {
  }


  /**
   * Test of setTestRoot method, of class InputPlugin.
   */
  @Test
  public void testSetTestRoot()
  {
  }


  /**
   * Test of setTestCaseFilter method, of class InputPlugin.
   */
  @Test
  public void testSetTestCaseFilter()
  {
  }


  /**
   * Test of setUp method, of class InputPlugin.
   */
  @Test
  public void testSetUp()
  {
  }


  /**
   * Test of loadTestCase method, of class InputPlugin.
   */
  @Test
  public void testLoadTestCase()
  {
  }


  /**
   * Test of shutDown method, of class InputPlugin.
   */
  @Test
  public void testShutDown()
  {
  }


  /**
   * Test of getName method, of class InputPlugin.
   */
  @Test
  public void testGetName()
  {
  }

  public class InputPluginImpl implements InputPlugin
  {

    public void init(TestRunner testRunner) throws Exception
    {
    }


    public String setTestRoot(String newTestRoot, String projectName) throws
                                                                             IllegalArgumentException
    {
      return "";
    }


    public String setTestCaseFilter(String[] labels) throws NullPointerException
    {
      return "";
    }


    public TestSuite setUp(TestSuite testSuite) throws NullPointerException
    {
      return null;
    }


    public TestCase loadTestCase(TestCase testCase) throws NullPointerException
    {
      return null;
    }


    public void shutDown()
    {
    }


    public String getName()
    {
      return "";
    }
  }

}
