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
import java.util.NoSuchElementException;

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
  public void testThatTestCaseCanBeCreatedWithAnIndentifier()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseCanBeCreatedWithAnIndentifier");

      TestCase testCase = new TestCase("qwerty");
      
      assertTrue(testCase.getId().equals("qwerty"));
      assertNotNull(testCase);
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test case which is not empty.
   * Validate that '' is not a valid identifier
   */
  @Test
  public void testThatTestCaseCantBeCreatedWithEmptyIdentifier()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseCantBeCreatedWithEmptyIdentifier");

      @SuppressWarnings("unused")
      TestCase testCase = new TestCase("");

      fail("Empty identifier is not allowed!");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test case which is not empty.
   * Validate that ' ' is not a valid identifier
   */
  @Test
  public void testThatTestCaseCantBeCreatedWithSpaceAsIdentifier()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseCantBeCreatedWithSpaceAsIdentifier");

      @SuppressWarnings("unused")
      TestCase testCase = new TestCase(" ");

      fail("Empty identifier is not allowed!");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
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
  public void testThatTestCaseCantBeCreatedWithNullAsIdentifier()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseCantBeCreatedWithNullAsIdentifier");
      
      @SuppressWarnings("unused")
      TestCase testCase = new TestCase(null);

      fail("Null as identifier is not allowed!");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
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
  public void testThatGetIdReturnsTheTestCaseId()
  {
    try
    {
      LOGGER.debug("TC: testThatGetIdReturnsTheTestCaseId");
      
      TestCase testCase = new TestCase("qwerty");
      
      assertTrue(testCase.getId().contentEquals("qwerty"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
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
  public void testThatNewTestCasesHaveInitialStateNotrun()
  {
    try
    {
      LOGGER.debug("TC: testThatNewTestCasesHaveInitialStateNotrun");
      
      TestCase testCase = new TestCase("tc");
      
      assertTrue(testCase.getTestCaseStatus() == TestStatus.NOTRUN);
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test case has a test status.
   * Validate that a test case can be updated to 'blocked'.
   */
  @Test
  public void testThatTestCaseStatusCanBeSetToBlockedFromAnyState()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseStatusCanBeSetToBlockedFromAnyState");
      
      TestCase testCase = new TestCase("tc");
      assertTrue(testCase.getTestCaseStatus() == TestStatus.NOTRUN);

      testCase.updateTestCaseStatus(TestStatus.BLOCKED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.BLOCKED);
      
      
      testCase.updateTestCaseStatus(TestStatus.FAILED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.FAILED);

      testCase.updateTestCaseStatus(TestStatus.BLOCKED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.BLOCKED);
      

      testCase.updateTestCaseStatus(TestStatus.PASSED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.PASSED);

      testCase.updateTestCaseStatus(TestStatus.BLOCKED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.BLOCKED);
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test case has a test status.
   * Validate that a test case can be updated to 'failed'.
   */
  @Test
  public void testThatTestCaseStatusCanBeSetToFailedFromAnyState()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseStatusCanBeSetToFailedFromAnyState");
      
      TestCase testCase = new TestCase("tc");
      assertTrue(testCase.getTestCaseStatus() == TestStatus.NOTRUN);
      
      testCase.updateTestCaseStatus(TestStatus.FAILED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.FAILED);

      
      testCase.updateTestCaseStatus(TestStatus.BLOCKED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.BLOCKED);
      
      testCase.updateTestCaseStatus(TestStatus.FAILED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.FAILED);
      

      testCase.updateTestCaseStatus(TestStatus.PASSED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.PASSED);

      testCase.updateTestCaseStatus(TestStatus.FAILED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.FAILED);
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test case has a test status.
   * Validate that a test case can be updated to 'passed'.
   */
  @Test
  public void testThatTestCaseStatusCanBeSetToPassedFromAnyState()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseStatusCanBeSetToPassedFromAnyState");
      
      TestCase testCase = new TestCase("tc");
      assertTrue(testCase.getTestCaseStatus() == TestStatus.NOTRUN);
      
      testCase.updateTestCaseStatus(TestStatus.PASSED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.PASSED);

      
      testCase.updateTestCaseStatus(TestStatus.BLOCKED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.BLOCKED);
      
      testCase.updateTestCaseStatus(TestStatus.PASSED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.PASSED);
      

      testCase.updateTestCaseStatus(TestStatus.FAILED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.FAILED);
      
      testCase.updateTestCaseStatus(TestStatus.PASSED);
      assertTrue(testCase.getTestCaseStatus() == TestStatus.PASSED);
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
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
  public void testThatTestCaseStatusCantBeSetToNull()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseStatusCantBeSetToNull");
      
      TestCase testCase = new TestCase("tc");
      assertTrue(testCase.getTestCaseStatus() == TestStatus.NOTRUN);

      testCase.updateTestCaseStatus(null);

      fail("Test case status can't be 'null'");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void testThatSetupActionsCanBeAddedToTestCases()
  {
    try
    {
      LOGGER.debug("TC: testThatSetupActionsCanBeAddedToTestCases");
      
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
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case. Null is not allowed.
   */
  @Test
  public void testThatSetupActionsAddedToTestCasesCantBeNull()
  {
    try
    {
      LOGGER.debug("TC: testThatSetupActionsAddedToTestCasesCantBeNull");
      
      TestCase testCase = new TestCase("tc");

      testCase.addSetUpAction(null);

      fail("'Null' is not allowed to add as action");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case and retrieved in the same order.
   */
  @Test
  public void testThatSetupActionsAddedToTestCasesCanBeRetrievedInTheSameOrder()
  {
    try
    {
      LOGGER.debug("TC: testThatSetupActionsAddedToTestCasesCanBeRetrievedInTheSameOrder");
      
      TestCase testCase = new TestCase("tc");
      TestAction testAction1 = new AbstractTestAction(LOGGER,1);
      TestAction testAction2 = new AbstractTestAction(LOGGER,2);
      TestAction testAction3 = new AbstractTestAction(LOGGER,3);
      LinkedList<TestAction> testActions;

      testCase.addSetUpAction(testAction1);
      testCase.addSetUpAction(testAction2);
      testCase.addSetUpAction(testAction3);

      testActions = testCase.getSetUpActions();
      assertTrue(testActions.size() == 3);

      assertTrue(testActions.get(0).equals(testAction1));
      assertTrue(testActions.get(1).equals(testAction2));
      assertTrue(testActions.get(2).equals(testAction3));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case only once.
   */
  @Test
  public void testThatSetupActionsCanBeAddedOnlyOnceToTestCases()
  {
    try
    {
      LOGGER.debug("TC: testThatSetupActionsAddedToTestCasesCanBeRetrievedInTheSameOrder");
      
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);

      testCase.addSetUpAction(testAction);
      testCase.addSetUpAction(testAction);

      fail("Actions can't be added twice to a test case!");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void testThatTestActionsCanBeAddedToTestCases()
  {
    try
    {
      LOGGER.debug("TC: testThatTestActionsCanBeAddedToTestCases");
      
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
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case. Null is not allowed.
   */
  @Test
  public void testThatTestActionsAddedToTestCasesCantBeNull()
  {
    try
    {
      LOGGER.debug("TC: testThatTestActionsAddedToTestCasesCantBeNull");
      
      TestCase testCase = new TestCase("tc");

      testCase.addTestAction(null);

      fail("'Null' is not allowed to add as action");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case and retrieved in the same order.
   */
  @Test
  public void testThatTestActionsAddedToTestCasesCanBeRetrievedInTheSameOrder()
  {
    try
    {
      LOGGER.debug("TC: testThatTestActionsAddedToTestCasesCanBeRetrievedInTheSameOrder");
      
      TestCase testCase = new TestCase("tc");
      TestAction testAction1 = new AbstractTestAction(LOGGER,1);
      TestAction testAction2 = new AbstractTestAction(LOGGER,2);
      TestAction testAction3 = new AbstractTestAction(LOGGER,3);
      LinkedList<TestAction> testActions;

      testCase.addTestAction(testAction1);
      testCase.addTestAction(testAction2);
      testCase.addTestAction(testAction3);

      testActions = testCase.getTestActions();
      assertTrue(testActions.size() == 3);

      assertTrue(testActions.get(0).equals(testAction1));
      assertTrue(testActions.get(1).equals(testAction2));
      assertTrue(testActions.get(2).equals(testAction3));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case only once.
   */
  @Test
  public void testThatTestActionsCanBeAddedOnlyOnceToTestCases()
  {
    try
    {
      LOGGER.debug("TC: testThatTestActionsAddedToTestCasesCanBeRetrievedInTheSameOrder");
      
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);

      testCase.addTestAction(testAction);
      testCase.addTestAction(testAction);

      fail("Actions can't be added twice to a test case!");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case
   */
  @Test
  public void testThatTearDownActionsCanBeAddedToTestCases()
  {
    try
    {
      LOGGER.debug("TC: testThatTearDownActionsCanBeAddedToTestCases");
      
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
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case. Null is not allowed.
   */
  @Test
  public void testThatTearDownActionsAddedToTestCasesCantBeNull()
  {
    try
    {
      LOGGER.debug("TC: testThatTearDownActionsAddedToTestCasesCantBeNull");
      
      TestCase testCase = new TestCase("tc");

      testCase.addTearDownAction(null);

      fail("'Null' is not allowed to add as action");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case and retrieved in the same order.
   */
  @Test
  public void testThatTearDownActionsAddedToTestCasesCanBeRetrievedInTheSameOrder()
  {
    try
    {
      LOGGER.debug("TC: testThatTearDownActionsAddedToTestCasesCanBeRetrievedInTheSameOrder");
      
      TestCase testCase = new TestCase("tc");
      TestAction testAction1 = new AbstractTestAction(LOGGER,1);
      TestAction testAction2 = new AbstractTestAction(LOGGER,2);
      TestAction testAction3 = new AbstractTestAction(LOGGER,3);
      LinkedList<TestAction> testActions;

      testCase.addTearDownAction(testAction1);
      testCase.addTearDownAction(testAction2);
      testCase.addTearDownAction(testAction3);

      testActions = testCase.getTearDownActions();
      assertTrue(testActions.size() == 3);

      assertTrue(testActions.get(0).equals(testAction1));
      assertTrue(testActions.get(1).equals(testAction2));
      assertTrue(testActions.get(2).equals(testAction3));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Actions can be added to a test case only once.
   */
  @Test
  public void testThatTearDownActionsCanBeAddedOnlyOnceToTestCases()
  {
    try
    {
      LOGGER.debug("TC: testThatTearDownActionsAddedToTestCasesCanBeRetrievedInTheSameOrder");
      
      TestCase testCase = new TestCase("tc");
      TestAction testAction = new AbstractTestAction(LOGGER);

      testCase.addTearDownAction(testAction);
      testCase.addTearDownAction(testAction);

      fail("Actions can't be added twice to a test case!");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }

  
  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void testThatHeadersWithCustomValuesCanBeAddedToAndRetrievedFromTestCases()
  {
    try
    {
      LOGGER.debug("TC: testThatHeadersWithCustomValuesCanBeAddedToAndRetrievedFromTestCases");
      
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("abc", "def");

      assertTrue(testCase.getHeader("abc").equals("def"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void testThatHeadersWithPreAndPostSpacesInKeysAreTrimmed()
  {
    try
    {
      LOGGER.debug("TC: testThatHeadersWithPreAndPostSpacesInKeysAreTrimmed");
      
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("  abc  ", "def");
      
      assertTrue(testCase.getHeader("abc").equals("def"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void testThatHeadersWithPreAndPostSpacesInValuesAreNotTrimmed()
  {
    try
    {
      LOGGER.debug("TC: testThatHeadersWithPreAndPostSpacesInValuesAreNotTrimmed");
      
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("abc", "  def  ");
      
      assertTrue(testCase.getHeader("abc").equals("  def  "));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void testThatHeadersWithOnlySpacesInKeyAreNotAccepted()
  {
    try
    {
      LOGGER.debug("TC: testThatHeadersWithOnlySpacesInKeyAreNotAccepted");
      
      TestCase testCase = new TestCase("tc");

      testCase.setHeader(" ", "abc");

      fail("' ' is not allowed to add as key");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void testThatHeadersWithOnlySpacesInValuesAreAccepted()
  {
    try
    {
      LOGGER.debug("TC: testThatHeadersWithOnlySpacesInValuesAreAccepted");
      
      TestCase testCase = new TestCase("tc");

      testCase.setHeader("abc", " ");

      assertTrue(testCase.getHeader("abc").equals(" "));
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void testThatHeadersWithNullAsKeyAreNotAccepted()
  {
    try
    {
      LOGGER.debug("TC: testThatHeadersWithNullAsKeyAreNotAccepted");
      
      TestCase testCase = new TestCase("tc");
      testCase.setHeader(null, "abc");

      fail("'Null' is not allowed to add as key");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
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
  public void testThatHeadersWithNullAsValueAreNotAccepted()
  {
    try
    {
      LOGGER.debug("TC: testThatHeadersWithNullAsValueAreNotAccepted");
      
      TestCase testCase = new TestCase("tc");
      testCase.setHeader("abc", null);

      fail("'Null' is not allowed to add as value");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
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
  public void testThatTestCasesCanBeSearchedForSpecificHeadersOnKeyValue()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCasesCanBeSearchedForSpecificHeadersOnKeyValue");
      
      TestCase testCase = new TestCase("tc");
      testCase.setHeader("abc", "def");

      assertTrue(testCase.hasHeader("abc"));
      assertFalse(testCase.hasHeader("def"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }
  

  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* headers. A key value pair chosen by the user.
   */
  @Test
  public void testThatTestCasesReturnAnExceptionWhenAnNonExisitingHeaderIsRequested()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCasesReturnAnExceptionWhenAnNonExisitingHeaderIsRequested");
      
      TestCase testCase = new TestCase("tc");

      testCase.getHeader("abc");

      fail("Request for non-existing key 'abc' should throw an NoSuchElementException");
    }
    catch(NoSuchElementException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }

  
  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void testThatVariablesWithCustomValuesCanBeAddedToAndRetrievedFromTestCases()
  {
    try
    {
      LOGGER.debug("TC: testThatVariablesWithCustomValuesCanBeAddedToAndRetrievedFromTestCases");
      
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("abc", "def");

      assertTrue(testCase.getVariable("abc").equals("def"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void testThatVariablesWithPreAndPostSpacesInKeysAreTrimmed()
  {
    try
    {
      LOGGER.debug("TC: testThatVariablesWithPreAndPostSpacesInKeysAreTrimmed");
      
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("  abc  ", "def");
      
      assertTrue(testCase.getVariable("abc").equals("def"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void testThatVariablesWithPreAndPostSpacesInValuesAreNotTrimmed()
  {
    try
    {
      LOGGER.debug("TC: testThatVariablesWithPreAndPostSpacesInValuesAreNotTrimmed");
      
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("abc", "  def  ");
      
      assertTrue(testCase.getVariable("abc").equals("  def  "));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void testThatVariablesWithOnlySpacesInKeyAreNotAccepted()
  {
    try
    {
      LOGGER.debug("TC: testThatVariablesWithOnlySpacesInKeyAreNotAccepted");
      
      TestCase testCase = new TestCase("tc");

      testCase.setVariable(" ", "abc");

      fail("' ' is not allowed to add as key");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void testThatVariablesWithOnlySpacesInValuesAreAccepted()
  {
    try
    {
      LOGGER.debug("TC: testThatVariablesWithOnlySpacesInValuesAreAccepted");
      
      TestCase testCase = new TestCase("tc");

      testCase.setVariable("abc", " ");

      assertTrue(testCase.getVariable("abc").equals(" "));
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void testThatVariablesWithNullAsKeyAreNotAccepted()
  {
    try
    {
      LOGGER.debug("TC: testThatVariablesWithNullAsKeyAreNotAccepted");
      
      TestCase testCase = new TestCase("tc");
      testCase.setVariable(null, "abc");

      fail("'Null' is not allowed to add as key");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
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
  public void testThatVariablesWithNullAsValueAreNotAccepted()
  {
    try
    {
      LOGGER.debug("TC: testThatVariablesWithNullAsValueAreNotAccepted");
      
      TestCase testCase = new TestCase("tc");
      testCase.setVariable("abc", null);

      fail("'Null' is not allowed to add as value");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
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
  public void testThatTestCasesCanBeSearchedForSpecificVariablesOnKeyValue()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCasesCanBeSearchedForSpecificVariablesOnKeyValue");
      
      TestCase testCase = new TestCase("tc");
      testCase.setVariable("abc", "def");

      assertTrue(testCase.hasVariable("abc"));
      assertFalse(testCase.hasVariable("def"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception " + eX.getMessage());
    }
  }
  

  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Testcases can contain 0..* variables. A key value pair chosen by the user.
   */
  @Test
  public void testThatTestCasesReturnAnExceptionWhenAnNonExisitingVariableIsRequested()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCasesReturnAnExceptionWhenAnNonExisitingVariableIsRequested");
      
      TestCase testCase = new TestCase("tc");

      testCase.getVariable("abc");

      fail("Request for non-existing key 'abc' should throw an NoSuchElementException");
    }
    catch(NoSuchElementException eX)
    {
      // Test passed
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
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


    protected AbstractTestAction(Logger newLogger, Integer id) throws NullPointerException, InvalidParameterException
    {
      super(newLogger, id);
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
