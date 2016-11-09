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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sjoerd Boerhout
 */
public class TestCaseTest
{

  /*
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


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test case which is not empty.
   * Create a test case with a valid identifier.
   */
  @Test
  public void createTestCaseWithValidIdentifier()
  {
    try
    {
      TestCase testCase = new TestCase("qwerty");
      assertNotNull(testCase);
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test case which is not empty.
   * Validate that ' ' is not a valid identifier
   */
  @Test
  public void createTestCaseWithEmptyIdentifier()
  {
    try
    {
      @SuppressWarnings("unused")
      TestCase testCase = new TestCase(" ");

      fail("Empty identifier is not allowed!");
    }
    catch(InvalidParameterException eX)
    {
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test case which is not empty.
   * Validate that 'null' is not a valid identifier
   */
  @Test
  public void createTestCaseWithNullAsIdentifier()
  {
    try
    {
      @SuppressWarnings("unused")
      TestCase testCase = new TestCase(null);

      fail("Null as identifier is not allowed!");
    }
    catch(InvalidParameterException eX)
    {
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test case which is not empty.
   * Test the getId-function.
   */
  @Test
  public void getId()
  {
    try
    {
      TestCase testCase = new TestCase("qwerty");
      assertTrue(testCase.getId().contentEquals("qwerty"));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test case has a test status.
   * Validate that a new test case has initial state NotRun.
   */
  @Test
  public void getTestCaseStatus()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      assertTrue(testCase.getTestCaseStatus() == TestStatus.NOTRUN);
    }
    catch(InvalidParameterException eX)
    {
      fail("Unexpected failure.");
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test case has a test status.
   * Validate that a test case can be updated.
   */
  @Test
  public void updateTestCaseStatusToValidStatus()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      assertTrue(testCase.getTestCaseStatus() == TestStatus.NOTRUN);
      
      testCase.updateTestCaseStatus(TestStatus.BLOCKED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.BLOCKED);
      
      testCase.updateTestCaseStatus(TestStatus.FAILED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.FAILED);
      
      testCase.updateTestCaseStatus(TestStatus.PASSED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.PASSED);
    }
    catch(InvalidParameterException eX)
    {
      fail("Unexpected failure.");
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  } 
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test case has a test status.
   * Validate that a test case can not be updated to 'null'.
   */
  @Test
  public void updateTestCaseStatusToNull()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      assertTrue(testCase.getTestCaseStatus() == TestStatus.NOTRUN);
      
      testCase.updateTestCaseStatus(null);

      fail("Test case status can't be 'null'");
    }
    catch(InvalidParameterException eX)
    {
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }
  
  
  /*
  public void addSetUpAction(TestAction setUpAction) throws InvalidParameterException
  public void addTestAction(TestAction testAction) throws InvalidParameterException
  public void addTearDownAction(TestAction tearDownAction) throws InvalidParameterException

  public LinkedList<TestAction> getSetUpActions()
  public LinkedList<TestAction> getTestActions()
  public LinkedList<TestAction> getTearDownActions()

  public void setHeader(String key, String value) throws InvalidParameterException
  public boolean hasHeader(String key) throws InvalidParameterException
  public String getHeader(String key) throws InvalidParameterException

  public void setVariable(String key, String value) throws InvalidParameterException
  public boolean hasVariable(String key) throws InvalidParameterException
  public String getVariable(String key) throws InvalidParameterException
   */

}
