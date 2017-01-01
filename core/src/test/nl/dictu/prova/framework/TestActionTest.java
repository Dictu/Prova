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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.security.InvalidParameterException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 * PROVA-12: Structure to handle test suites, cases and actions
 * <p>
 * 
 * @author Sjoerd Boerhout
 */
public class TestActionTest
{
  private final static Logger LOGGER = LogManager.getLogger(TestActionTest.class.getName());


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
   * A mandatory unique identifier is set for each test action.
   */
  @Test
  public void testGetIdReturnsTheActionsId()
  {
    try
    {
      LOGGER.debug("TC: testGetIdReturnsTheActionsId");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER, 71183);

      assertTrue(testAction.getId() == 71183);
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
   * The unique identifier of a test action is not 'null'.
   */
  @Test
  public void testThatSetIdDoesntAcceptNull()
  {
    try
    {
      LOGGER.debug("TC: testThatSetIdDoesntAcceptNull");

      @SuppressWarnings("unused")
      AbstractTestAction testAction = new AbstractTestAction(LOGGER, null);

      fail("'null' is not allowed as action id.");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
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
   * The unique identifier of a test action is not negative.
   */
  @Test
  public void testThatSetIdDoesntAcceptNegativeValues()
  {
    try
    {
      LOGGER.debug("TC: testThatSetIdDoesntAcceptNegativeValues");

      @SuppressWarnings("unused")
      AbstractTestAction testAction = new AbstractTestAction(LOGGER, -1);

      fail("Negative id's are not allowed as action id.");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
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
   * Each test action must be part of a test case to be executed.
   */
  @Test
  public void testSetAndGetParentToValidTestCase()
  {
    try
    {
      LOGGER.debug("TC: testSetAndGetParentToValidTestCase");

      TestCase testCase = new TestCase("UniqueId");
      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      testAction.setParent(testCase);

      assertTrue(testAction.getParent() == testCase);
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
   * A test actions parent can't be set to 'null'
   */
  @Test
  public void testSetParentDoesntAcceptNull()
  {
    try
    {
      LOGGER.debug("TC: testSetParentDoesntAcceptNull");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      testAction.setParent(null);

      fail("'NULL' is not allowed as action id.");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
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
   * The initial state of a test action is 'NotRun'.
   */
  @Test
  public void testThatTheInitialTestActionStateIsNotRun()
  {
    try
    {
      LOGGER.debug("TC: testThatTheInitialTestActionStateIsNotRun");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      assertTrue(testAction.getStatus() == TestStatus.NOTRUN);
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
   * Each test action has a test status.
   * A test action state can be 'NotRun', 'Blocked', 'Passed' or 'Failed'
   */
  @Test
  public void testThatTestActionStatusCanBeUpdatedToBlocked()
  {
    try
    {
      LOGGER.debug("TC: testThatTestActionStatusCanBeUpdatedToBlocked");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      assertTrue(testAction.getStatus() == TestStatus.NOTRUN);

      testAction.updateStatus(TestStatus.BLOCKED);

      assertTrue(testAction.getStatus() == TestStatus.BLOCKED);
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
   * Each test action has a test status.
   * A test action state can be 'NotRun', 'Blocked', 'Passed' or 'Failed'
   */
  @Test
  public void testThatTestActionStatusCanBeUpdatedToPassed()
  {
    try
    {
      LOGGER.debug("TC: testThatTestActionStatusCanBeUpdatedToPassed");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      assertTrue(testAction.getStatus() == TestStatus.NOTRUN);

      testAction.updateStatus(TestStatus.PASSED);

      assertTrue(testAction.getStatus() == TestStatus.PASSED);
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
   * Each test action has a test status.
   * A test action state can be 'NotRun', 'Blocked', 'Passed' or 'Failed'
   */
  @Test
  public void testThatTestActionStatusCanBeUpdatedToFailed()
  {
    try
    {
      LOGGER.debug("TC: testThatTestActionStatusCanBeUpdatedToFailed");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      assertTrue(testAction.getStatus() == TestStatus.NOTRUN);

      testAction.updateStatus(TestStatus.FAILED);
      assertTrue(testAction.getStatus() == TestStatus.FAILED);
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
   * A test action state can be 'NotRun', 'Blocked', 'Passed' or 'Failed'
   */
  @Test
  public void testThatTestActionStatusCantBeUpdatedToNull()
  {
    try
    {
      LOGGER.debug("TC: testThatTestActionStatusCantBeUpdatedToNull");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      assertTrue(testAction.getStatus() == TestStatus.NOTRUN);

      testAction.updateStatus(null);

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
   * - Test actions can contain 0..* attributes
   * - Attribute are key and value pairs (String)
   */
  @Test
  public void testAttributesCanContainMultipleSetsOfStrings()
  {
    try
    {
      LOGGER.debug("TC: testAttributesCanContainMultipleSetsOfData");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      assertTrue(testAction.getAttributes().size() == 0);

      testAction.setAttribute("abc", "def");
      testAction.setAttribute("ghi", "jkl");
      testAction.setAttribute("mno", "pqr");

      assertTrue(testAction.getAttributes().size() == 3);

      assertTrue(testAction.getAttribute("abc") instanceof String);

      assertTrue(testAction.getAttribute("abc").equals("def"));
      assertTrue(testAction.getAttribute("ghi").equals("jkl"));
      assertTrue(testAction.getAttribute("mno").equals("pqr"));
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
   * - Key are not allowed to have prefix and post fix spaces
   */
  @Test
  public void testSetAttributeTrimsSpacesFromKeys()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeTrimsSpacesFromKeys");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      testAction.setAttribute("  abc  ", "def");
      
      assertTrue(testAction.getAttribute("abc").equals("def"));
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
   * - Values are allowed to have prefix and post fix spaces
   */
  @Test
  public void testSetAttributeDoesntTrimSpacesFromValues()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeDoesntTrimSpacesFromValues");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setAttribute("abc", "  def  ");

      assertTrue(testAction.getAttribute("abc").equals("  def  "));
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
   * - Keys must have a minimal length of 1 character
   */
  @Test
  public void testSetAttributeDoesntAcceptsEmptyKeys()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeDoesntAcceptsEmptyKeys");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setAttribute("", "abc");

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
   * - Keys must have a minimal length of 1 character
   */
  @Test
  public void testSetAttributeDoesntAcceptOnlySpacesAsKey()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeDoesntAcceptsEmptyKeys");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setAttribute(" ", "abc");

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
   * - Keys must have a minimal length of 1 character
   */
  @Test
  public void testSetAttributeDoesntAcceptsNullAsKey()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeDoesntAcceptsEmptyKeys");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setAttribute(null, "abc");

      fail("'null' is not allowed to add as key");
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
   * - Values don't have a minimal length
   */
  @Test
  public void testSetAttributeAcceptsEmptyValues()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeAcceptsEmptyValues");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setAttribute("abc", "");

      assertTrue(testAction.getAttribute("abc").equals(""));
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
   * - Values don't have a minimal length
   */
  @Test
  public void testSetAttributeAcceptOnlySpacesAsValue()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeAcceptOnlySpacesAsValue");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setAttribute("abc", " ");

      assertTrue(testAction.getAttribute("abc").equals(" "));
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
   * - Values don't have a minimal length
   */
  @Test
  public void testSetAttributeDoesntAcceptsNullAsValue()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeDoesntAcceptsNullAsValue");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setAttribute("abc", null);

      fail("'null' is not allowed to add as value");
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
   * - Attributes can be searched based on the key value
   */
  @Test
  public void testHasAttributeFindsAddedAttributes()
  {
    try
    {
      LOGGER.debug("TC: testSetAttributeDoesntAcceptsNullAsValue");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      assertFalse(testAction.hasAttribute("abc"));

      testAction.setAttribute("abc", "def");

      assertTrue(testAction.hasAttribute("abc"));
    }
    catch(Exception eX)
    {
      if(LOGGER.isErrorEnabled())
        eX.printStackTrace();
      
      fail("Unexpected exception: " + eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * - Attributes can be retrieved based on the key value
   */
  @Test
  public void testGetAttributeRetrievesValuesBasedOnTheRelatedKey()
  {
    try
    {
      LOGGER.debug("TC: testGetAttributeRetrievesValuesBasedOnTheRelatedKey");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      testAction.setAttribute("abc", "def");

      assertTrue(testAction.getAttribute("abc").equals("def"));
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
   * - Return variables can contain 0..* variables
   * - Variables are key and value pairs (String)
   */
  @Test
  public void testReturnVariablesCanContainMultipleSetsOfStrings()
  {
    try
    {
      LOGGER.debug("TC: testReturnVariablesCanContainMultipleSetsOfStrings");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      assertTrue(testAction.getReturnVariables().size() == 0);

      testAction.setReturnVariable("abc", "def");
      testAction.setReturnVariable("ghi", "jkl");
      testAction.setReturnVariable("mno", "pqr");

      assertTrue(testAction.getReturnVariables().size() == 3);

      assertTrue(testAction.getReturnVariables().getProperty("abc") instanceof String);

      assertTrue(testAction.getReturnVariables().getProperty("abc").equals("def"));
      assertTrue(testAction.getReturnVariables().getProperty("ghi").equals("jkl"));
      assertTrue(testAction.getReturnVariables().getProperty("mno").equals("pqr"));
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
   * - Key are not allowed to have prefix and post fix spaces
   */
  @Test
  public void testSetReturnVariablesTrimsSpacesFromKeys()
  {
    try
    {
      LOGGER.debug("TC: testSetReturnVariablesTrimsSpacesFromKeys");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);

      testAction.setReturnVariable("  abc  ", "def");

      assertTrue(testAction.getReturnVariables().getProperty("abc").equals("def"));
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
   * - Values are allowed to have prefix and post fix spaces
   */
  @Test
  public void testSetReturnVariablesDoesntTrimSpacesFromValues()
  {
    try
    {
      LOGGER.debug("TC: testSetReturnVariablesDoesntTrimSpacesFromValues");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setReturnVariable("abc", "  def  ");

      assertTrue(testAction.getReturnVariables().getProperty("abc").equals("  def  "));
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
   * - Keys must have a minimal length of 1 character
   */
  @Test
  public void testSetReturnVariableDoesntAcceptsEmptyKeys()
  {
    try
    {
      LOGGER.debug("TC: testSetReturnVariableDoesntAcceptsEmptyKeys");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setReturnVariable("", "abc");

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
   * - Keys must have a minimal length of 1 character
   */
  @Test
  public void testSetReturnVariableDoesntAcceptOnlySpacesAsKey()
  {
    try
    {
      LOGGER.debug("TC: testSetReturnVariableDoesntAcceptOnlySpacesAsKey");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setReturnVariable(" ", "abc");

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
   * - Keys must have a minimal length of 1 character
   */
  @Test
  public void testSetReturnVariableDoesntAcceptsNullAsKey()
  {
    try
    {
      LOGGER.debug("TC: testSetReturnVariableDoesntAcceptsNullAsKey");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setReturnVariable(null, "abc");

      fail("'null' is not allowed to use as key");
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
   * - Values don't have a minimal length
   */
  @Test
  public void testSetReturnVariableAcceptsEmptyValues()
  {
    try
    {
      LOGGER.debug("TC: testSetReturnVariableAcceptsEmptyValues");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setReturnVariable("abc", "");

      assertTrue(testAction.getReturnVariables().getProperty("abc").equals(""));
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
   * - Values don't have a minimal length
   */
  @Test
  public void testSetReturnVariableAcceptOnlySpacesAsValue()
  {
    try
    {
      LOGGER.debug("TC: testSetReturnVariableAcceptOnlySpacesAsValue");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setReturnVariable("abc", " ");

      assertTrue(testAction.getReturnVariables().getProperty("abc").equals(" "));
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
   * - Values don't have a minimal length
   */
  @Test
  public void testSetReturnVariableDoesntAcceptsNullAsValue()
  {
    try
    {
      LOGGER.debug("TC: testSetReturnVariableDoesntAcceptsNullAsValue");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.setReturnVariable("abc", null);

      fail("'null' is not allowed to use as value");
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
   * - Test actions can optionally write a file with results for the specific
   * test.
   */
  @Test
  public void testResultFileIsInitialNull()
  {
    LOGGER.debug("TC: testResultFileIsInitialNull");

    AbstractTestAction testAction = new AbstractTestAction(LOGGER);

    assertTrue(testAction.getResultFile() == null);
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * - Test actions can optionally write a file with results for the specific
   * test.
   */
  @Test
  public void testThatResultFileCanBeSavedAndRetrieved()
  {
    LOGGER.debug("TC: testThatResultFileCanBeSavedAndRetrieved");

    AbstractTestAction testAction = new AbstractTestAction(LOGGER);

    File retFile = new File(".");

    testAction.setResultFile(retFile);

    assertTrue(testAction.getResultFile() == retFile);
  }


  /**
   * Test of getLastException method, of class TestAction.
   */
  @Ignore
  @Test
  public void testGetLastException()
  {

  }


  /**
   * Test of action execution time method.
   */
  @Test
  public void testActionExecutionTimeCalculation()
  {
    LOGGER.debug("TC: testActionExecutionTimeCalculation");

    long startTime, endTime, exectionTime;
    AbstractTestAction testAction = new AbstractTestAction(LOGGER);

    startTime = testAction.startExecution();

    try
    {
      Thread.sleep(50);
    }
    catch(Exception eX){}

    endTime = testAction.endExecution();

    exectionTime = testAction.getExecutionTime();

    assertTrue(exectionTime == (endTime - startTime));
  }


  /**
   * Test of action execution time method with invalid start time.
   */
  @Test
  public void testActionExecutionTimeWithInvalidStartTime()
  {
    try
    {
      LOGGER.debug("TC: testActionExecutionTimeWithInvalidStartTime");
      
      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
      testAction.endExecution();
      testAction.getExecutionTime();

      fail("Calculation should fail because start time was not set");
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
   * Test of action execution time method with invalid end time.
   */
  @Test
  public void testActionExecutionTimeWithInvalidEndTime()
  {
    try
    {
      LOGGER.debug("TC: testActionExecutionTimeWithInvalidEndTime");

      AbstractTestAction testAction = new AbstractTestAction(LOGGER);
    
      testAction.startExecution();
      testAction.getExecutionTime();

      fail("Calculation should fail because end time was not set");
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

