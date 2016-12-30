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

package nl.dictu.prova.framework;

import nl.dictu.prova.GlobalSetup;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * PROVA-12: Structure to handle test suites, cases and actions
 * <p>
 * 
 * @author Sjoerd Boerhout
 */
public class TestCaseTest
{
  private final static Logger LOGGER = LogManager.getLogger(TestCaseTest.class.getName());


  /**
   * One-time initialization code
   */
  @BeforeClass
  public static void setUpClass()
  {
    GlobalSetup.configure();
  }


  /**
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


  /**
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


  /**
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


  /**
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


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test case has a test status.
   * Validate that a new test case has initial state NotRun.
   */
  @Test
  public void getDefaultTestCaseStatus()
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


  /**
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


  /**
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


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void addSetupActionWithValidAction()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);
      LinkedList<TestAction> testActions;

      testCase.addSetUpAction(testAction);
      assertTrue(testCase.getSetUpActions().size() == 1);

      testActions = testCase.getSetUpActions();
      assertTrue(testActions.size() == 1);

      assertTrue(testActions.get(0).equals(testAction));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case. Null is not allowed.
   */
  @Test
  public void addSetupActionWithInvalidAction()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.addSetUpAction(null);

      fail("'Null' is not allowed to add as action");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void getSetUpActions()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);
      LinkedList<TestAction> testActions;

      testCase.addSetUpAction(testAction);

      testActions = testCase.getSetUpActions();
      assertTrue(testActions.size() == 1);

      assertTrue(testActions.get(0).equals(testAction));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void addTestActionWithValidAction()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);
      LinkedList<TestAction> testActions;

      testCase.addTestAction(testAction);
      assertTrue(testCase.getTestActions().size() == 1);

      testActions = testCase.getTestActions();
      assertTrue(testActions.size() == 1);

      assertTrue(testActions.get(0).equals(testAction));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case. Null is not allowed.
   */
  @Test
  public void addTestActionWithInvalidAction()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.addTestAction(null);

      fail("'Null' is not allowed to add as action");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void getTestActions()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);
      LinkedList<TestAction> testActions;

      testCase.addTestAction(testAction);

      testActions = testCase.getTestActions();
      assertTrue(testActions.size() == 1);

      assertTrue(testActions.get(0).equals(testAction));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void addTearDownActionWithValidAction()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);
      LinkedList<TestAction> testActions;

      testCase.addTearDownAction(testAction);
      assertTrue(testCase.getTearDownActions().size() == 1);

      testActions = testCase.getTearDownActions();
      assertTrue(testActions.size() == 1);

      assertTrue(testActions.get(0).equals(testAction));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case. Null is not allowed.
   */
  @Test
  public void addTearDownActionWithInvalidAction()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.addTearDownAction(null);

      fail("'Null' is not allowed to add as action");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void getTearDownActions()
  {
    try
    {
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);
      LinkedList<TestAction> testActions;

      testCase.addTearDownAction(testAction);

      testActions = testCase.getTearDownActions();
      assertTrue(testActions.size() == 1);

      assertTrue(testActions.get(0).equals(testAction));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void setHeaderWithValidValue()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("abc", "def");

      assertTrue(testCase.getHeader("abc").equals("def"));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void setHeaderWithValidKeyIncludingPrefixAndPostfixSpacesInKey()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("  abc  ", "def");
      assertTrue(testCase.getHeader("abc").equals("def"));

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void setHeaderWithValidKeyIncludingPrefixAndPostfixSpacesInValue()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("abc", "  def  ");
      assertTrue(testCase.getHeader("abc").equals("  def  "));

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void setHeaderWithInvalidKey()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setHeader(" ", "abc");

      fail("' ' is not allowed to add as key");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void setHeaderWithNullAsKey()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setHeader(null, "abc");

      fail("'Null' is not allowed to add as key");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void hasHeader()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("abc", "def");

      assertTrue(testCase.hasHeader("abc"));

      assertFalse(testCase.hasHeader("def"));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void getHeaderWithValidValue()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("abc", "def");

      assertTrue(testCase.getHeader("abc").equals("def"));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void getHeaderWithNonExistingKey()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.getHeader("abc");

      fail("Request for non-existing key 'abc' should throw an exception");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void setVariableWithValidValue()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("abc", "def");

      assertTrue(testCase.getVariable("abc").equals("def"));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void setVariableWithValidKeyIncludingPrefixAndPostfixSpacesInKey()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("  abc  ", "def");
      assertTrue(testCase.getVariable("abc").equals("def"));

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void setVariableWithValidKeyIncludingPrefixAndPostfixSpacesInValue()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("abc", "  def  ");
      assertTrue(testCase.getVariable("abc").equals("  def  "));

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void setVariableWithInvalidKey()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setVariable(" ", "abc");

      fail("' ' is not allowed to add as key");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void setVariableWithNullAsKey()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setVariable(null, "abc");

      fail("'Null' is not allowed to add as key");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void hasVariable()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("abc", "def");

      assertTrue(testCase.hasVariable("abc"));

      assertFalse(testCase.hasVariable("def"));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void getVariableWithValidValue()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("abc", "def");

      assertTrue(testCase.getVariable("abc").equals("def"));
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void getVariableWithNonExistingKey()
  {
    try
    {
      TestCase testCase = new TestCase("tc");

      testCase.getVariable("abc");

      fail("Request for non-existing key 'abc' should throw an exception");
    }
    catch(InvalidParameterException eX)
    {

    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * Basic implementation of abstract class TestAction to be able to run tests
   * 
   * @author Sjoerd Boerhout
   */
  private class AbstractTestAction extends TestAction
  {

    protected AbstractTestAction(Logger newLogger) throws NullPointerException
    {
      super(newLogger, 71183);
    }


    @Override
    public TestStatus execute()
    {
      return null;
    }


    @Override
    public boolean isValid()
    {
      return false;
    }


    @Override
    public String toString()
    {
      return null;
    }

  }

}
