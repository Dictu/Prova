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
package nl.dictu.prova.plugins.output;

import java.security.InvalidParameterException;
import nl.dictu.prova.TestRunner;
import nl.dictu.prova.TestType;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestCase;
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
public class OutputPluginTest
{

  public OutputPluginTest()
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
   * Test of init method, of class OutputPlugin.
   */
  @Test
  @Ignore
  public void testInit() throws Exception
  {
  }


  /**
   * Test of shutDown method, of class OutputPlugin.
   */
  @Test
  @Ignore
  public void testShutDown()
  {
  }


  /**
   * Test of getName method, of class OutputPlugin.
   */
  @Test
  @Ignore
  public void testGetName()
  {
  }


  /**
   * Test of getTestType method, of class OutputPlugin.
   */
  @Test
  @Ignore
  public void testGetTestType()
  {
  }


  /**
   * Test of setUp method, of class OutputPlugin.
   */
  @Test
  @Ignore
  public void testSetUp()
  {
  }


  /**
   * Test of tearDown method, of class OutputPlugin.
   */
  @Test
  @Ignore
  public void testTearDown()
  {
  }


  /**
   * Test of getTestAction method, of class OutputPlugin.
   */
  @Test
  @Ignore
  public void testGetTestAction()
  {
  }

  public class OutputPluginImpl implements OutputPlugin
  {

    public void init(TestRunner testRunner) throws Exception
    {
    }


    public void shutDown()
    {
    }


    public String getName()
    {
      return "";
    }


    public TestType[] getTestType()
    {
      return null;
    }


    public void setUp(TestCase testCase)
    {
    }


    public void tearDown(TestCase testCase)
    {
    }


    public TestAction getTestAction(String actionName) throws
                                                              InvalidParameterException
    {
      return null;
    }
  }

}
