/**
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence. You may
 * obtain a copy of the Licence at:
 * <p>
 * http://ec.europa.eu/idabc/eupl
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * Licence for the specific language governing permissions and limitations under
 * the Licence.
 * <p>
 * Date: 27-08-2016 Author(s): Sjoerd Boerhout
 * <p>
 */

package nl.dictu.prova.framework;

import nl.dictu.prova.GlobalSetup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * PROVA-12: Structure to handle test suites, cases and actions
 * <p>
 * 
 * @author Sjoerd Boerhout
 */
public class TestSuiteTest
{
  private final static Logger LOGGER = LogManager.getLogger(TestSuiteTest.class.getName());

  private static TestSuite testRoot;
  private static TestSuite childTestSuite;
  private static TestSuite subTestSuite;


  /**
   * One-time initialization code
   */
  @BeforeClass
  public static void oneTimeSetUp()
  {
    GlobalSetup.configure();

    /*
     * Create virtual structure of test suites and test cases:
     * Root test suite:testRoot
     * 0:TC: Shl-00
     * 0:TC: Web-00
     * 0:TS: TestSuite-00
     * 1: TC: Shl-00-01
     * 1: TC: Web-00-01
     * 1: TS: TestSuite-01-01
     * 2: TC: Shl-01-01-01
     * 2: TC: Web-01-01-01
     * 2: TC: Shl-01-01-02
     * 2: TC: Web-01-01-02
     * 2: TS: TestSuite-01-01-01
     * 3: TC: Shl-01-01-01-01
     * 3: TC: Web-01-01-01-01
     * 2: TS: TestSuite-01-01-02
     * 3: TC: Shl-01-01-02-01
     * 3: TC: Web-01-01-02-01
     * 1: TS: TestSuite-01-02
     * 2: TC: Shl-01-02-01
     * 2: TC: Web-01-02-01
     * 2: TC: Shl-01-02-02
     * 2: TC: Web-01-02-02
     * 2: TS: TestSuite-01-02-01
     * 3: TC: Shl-01-02-01-01
     * 3: TC: Web-01-02-01-01
     * 2: TS: TestSuite-01-02-02
     * 3: TC: Shl-01-02-02-01
     * 3: TC: Web-01-02-02-01
     */
    try
    {
      LOGGER.debug("Create a test structure of test suites and test cases");

      TestSuite tmpTestSuiteI;
      TestSuite tmpTestSuiteJ;
      TestSuite tmpTestSuiteK;

      testRoot = new TestSuite("testRoot");

      testRoot.addTestCase(new TestCase("Shl-00"));
      testRoot.addTestCase(new TestCase("Web-00"));

      tmpTestSuiteI = new TestSuite("TestSuite-00");
      testRoot.addTestSuite(tmpTestSuiteI);
      childTestSuite = tmpTestSuiteI;

      for(int i = 1; i < 2; i++)
      {
        tmpTestSuiteI.addTestCase(new TestCase("Shl-00-0" + i));
        tmpTestSuiteI.addTestCase(new TestCase("Web-00-0" + i));

        for(int j = 1; j < 3; j++)
        {
          tmpTestSuiteJ = new TestSuite("TestSuite-0" + i + "-0" + j);
          tmpTestSuiteI.addTestSuite(tmpTestSuiteJ);

          for(int k = 1; k < 3; k++)
          {
            tmpTestSuiteJ.addTestCase(new TestCase("Shl-0" + i + "-0" + j + "-0" + k));
            tmpTestSuiteJ.addTestCase(new TestCase("Web-0" + i + "-0" + j + "-0" + k));

            tmpTestSuiteK = new TestSuite("TestSuite-0" + i + "-0" + j + "-0" + k);
            tmpTestSuiteJ.addTestSuite(tmpTestSuiteK);
            // For use in one of the unit tests
            subTestSuite = tmpTestSuiteK;

            for(int l = 1; l < 2; l++)
            {
              tmpTestSuiteK.addTestCase(new TestCase("Shl-0" + i + "-0" + j + "-0" + k + "-0" + l));
              tmpTestSuiteK.addTestCase(new TestCase("Web-0" + i + "-0" + j + "-0" + k + "-0" + l));
            }
          }
        }
      }

      if(LOGGER.isTraceEnabled())
      {
        LOGGER.trace("Root test suite: '{}'", testRoot.getId());
        printTestSuite(testRoot, 0);
      }
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /**
   * Trace function used for displaying the test tree of test suites and test
   * cases used in this class. Prints a line with <level>x space, <name> and
   * line end.
   *
   * @param level
   * @param name
   */
  private static void printLine(int level, String name)
  {
    if(LOGGER.isTraceEnabled())
    {
      String line = level + ":";
  
      for(int i = 0; i < level; i++)
      {
        line += " ";
      }
      
      line += name;
  
      LOGGER.trace(line);
    }
  }


  /**
   * Debug function used for displaying the test tree of test suites and test
   * cases used in this class. Loops recursively through the tree of test suites
   * and test cases.
   *
   * @param rootTestSuite
   * @param level
   */
  private static void printTestSuite(TestSuite rootTestSuite, int level)
  {
    if(LOGGER.isTraceEnabled())
    {
      rootTestSuite.getTestCases().entrySet().stream().forEach((entry) -> {
        printLine(level, "TC: " + entry.getValue().getId());
      });
      LOGGER.trace("");
  
      rootTestSuite.getTestSuites().entrySet().stream().map((entry) -> {
        printLine(level, "TS: " + entry.getValue().getId());
        return entry;
      }).forEach((entry) -> {
        printTestSuite(entry.getValue(), level + 1);
      });
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Create a test suite with a valid identifier.
   */
  @Test
  public void testThatTestSuiteCanBeCreatedWithAnIndentifier()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteCanBeCreatedWithAnIndentifier");

      TestSuite testSuite = new TestSuite("qwerty");
      
      assertTrue(testSuite.getId().contentEquals("qwerty"));
      assertNull(testSuite.getParent());
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
   * Unique identifier per test suite which is not empty.
   * Create a test suite with a valid identifier.
   */
  @Test
  public void testThatTestSuiteCanBeCreatedWithAnIndentifierAndWithParent()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteCanBeCreatedWithAnIndentifierAndWithParent");

      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty", parent);
      
      assertTrue(testSuite.getId().contentEquals("qwerty"));
      assertTrue(testSuite.getParent().equals(parent));
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
   * Unique identifier per test suite which is not empty.
   * Validate that '' is not a valid identifier
   */
  @Test
  public void testThatTestSuiteCantBeCreatedWithEmptyIdentifier()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteCantBeCreatedWithEmptyIdentifier");
      
      @SuppressWarnings("unused")
      TestSuite tmpTestSuite1 = new TestSuite("");

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
   * Unique identifier per test suite which is not empty.
   * Validate that '' is not a valid identifier
   */
  @Test
  public void testThatTestSuiteCantBeCreatedWithEmptyIdentifierAndWithParent()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteCantBeCreatedWithEmptyIdentifierAndWithParent");

      TestSuite parent = new TestSuite("");
      @SuppressWarnings("unused")
      TestSuite tmpTestSuite1 = new TestSuite("", parent);

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
   * Unique identifier per test suite which is not empty.
   * Validate that ' ' is not a valid identifier
   */
  @Test
  public void testThatTestSuiteCantBeCreatedWithSpaceAsIdentifier()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteCantBeCreatedWithSpaceAsIdentifier");
      
      @SuppressWarnings("unused")
      TestSuite tmpTestSuite1 = new TestSuite(" ");

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
   * Unique identifier per test suite which is not empty.
   * Validate that ' ' is not a valid identifier
   */
  @Test
  public void testThatTestSuiteCantBeCreatedWithSpaceAsIdentifierAndWithParent()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteCantBeCreatedWithSpaceAsIdentifierAndWithParent");
      
      TestSuite parent = new TestSuite("parent");
      @SuppressWarnings("unused")
      TestSuite tmpTestSuite1 = new TestSuite(" ", parent);

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
   * Unique identifier per test suite which is not empty.
   * Validate that 'null' is not a valid identifier
   */
  @Test
  public void testThatTestSuiteCantBeCreatedWithNullAsIdentifier()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCaseCantBeCreatedWithNullAsIdentifier");
      
      @SuppressWarnings("unused")
      TestSuite tmpTestSuite1 = new TestSuite(null);

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
   * Unique identifier per test suite which is not empty.
   * Validate that 'null' is not a valid identifier
   */
  @Test
  public void testThatTestSuiteCantBeCreatedWithNullAsIdentifierAndWithParent()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteCantBeCreatedWithNullAsIdentifierAndWithParent");

      TestSuite parent = new TestSuite("parent");
      @SuppressWarnings("unused")
      TestSuite tmpTestSuite1 = new TestSuite(null, parent);

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
   * Each (child)test suite in the structure has a unique identifier
   */
  @Test
  public void testThatChildTestSuitesHaveUniqueId()
  {
    try
    {
      LOGGER.debug("TC: testThatChildTestSuitesHaveUniqueId");
      
      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite1 = new TestSuite("qwerty");
      TestSuite testSuite2 = new TestSuite("qwerty");

      parent.addTestSuite(testSuite1);
      assertTrue(parent.getTestSuite("qwerty") == testSuite1);

      parent.addTestSuite(testSuite2);

      fail("Adding a test suite with an already existing ID is not allowed.");
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
   * Each (child)test case in the structure has a unique identifier
   */
  @Test
  public void testThatChildTestCasesHaveUniqueId()
  {
    try
    {
      LOGGER.debug("TC: testThatChildTestCasesHaveUniqueId");
      
      TestSuite parent = new TestSuite("parent");
      TestCase testCase1 = new TestCase("qwerty");
      TestCase testCase2 = new TestCase("qwerty");

      parent.addTestCase(testCase1);
      assertTrue(parent.getTestCase("qwerty") == testCase1);

      parent.addTestCase(testCase2);

      fail("Adding a test case with an already existing ID is not allowed.");
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
   * A test suite can have 0...* child test suites.
   * Each child test suite must be a valid test suite.
   */
  @Test
  public void testThatChildTestSuitesCantBeNull()
  {
    try
    {
      LOGGER.debug("TC: testThatChildTestSuitesCantBeNull");
      
      TestSuite parent = new TestSuite("parent");

      parent.addTestSuite(null);

      fail("Adding a test suite 'null' is not allowed.");
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
   * A test suite can have 0...* child test suites.
   * Each child test case must be a valid test case.
   */
  @Test
  public void testThatChildTestCasesCantBeNull()
  {
    try
    {
      LOGGER.debug("TC: testThatChildTestCasesCantBeNull");
      
      TestSuite parent = new TestSuite("parent");

      parent.addTestCase(null);

      fail("Adding a test case 'null' is not allowed.");
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
   * Each child test suite must be unique to prevent a loop Validate that an
   * added test suite doesn't already exists in the tree.
   */
  @Test
  public void testThatTestSuiteCantBeItsOwnParent()
  {
    TestSuite testSuiteRoot = null;

    try
    {
      LOGGER.debug("TC: testThatTestSuiteCantBeItsOwnParent");
      
      testSuiteRoot = new TestSuite("root");

      testSuiteRoot.addTestSuite(testSuiteRoot);
      testSuiteRoot.setParent(testSuiteRoot);
      
      TestSuite parent = new TestSuite("parent");
      testSuiteRoot = new TestSuite("root", parent);

      testSuiteRoot.addTestSuite(testSuiteRoot);
      testSuiteRoot.setParent(testSuiteRoot);

      fail("A test suite can't be it's own parent!");
    }
    catch(InvalidParameterException eX)
    {
      assertEquals(0, testSuiteRoot.getTestSuites().size());
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
   * Each child test suite must be unique to prevent a loop. Validate that an
   * added test suite doesn't already exists in the tree.
   */
  @Test
  public void testThatTestSuiteCantCreateDirectLoop()
  {
    TestSuite testSuiteRoot = new TestSuite("root");

    try
    {
      LOGGER.debug("TC: testThatTestSuiteCantCreateDirectLoop");
      
      TestSuite testSuite1 = new TestSuite("azerty", testSuiteRoot);
      testSuiteRoot.addTestSuite(testSuite1);

      fail("Child test suites must be unique!");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
      assertEquals(0, testSuiteRoot.getTestSuites().size());
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
   * Each child test suite must be unique to prevent a loop. Validate that an
   * added test suite doesn't already exists in the tree.
   */
  @Test
  public void testThatTestSuiteCantCreateIndirectLoop()
  {
    TestSuite testSuiteRoot = new TestSuite("root");

    try
    {
      LOGGER.debug("TC: testThatTestSuiteCantCreateIndirectLoop");
      
      TestSuite testSuite1 = new TestSuite("azerty", testSuiteRoot);
      TestSuite testSuite2 = new TestSuite("azerty", testSuite1);

      testSuiteRoot.addTestSuite(testSuite2);

      fail("Child test suites must be unique!");
    }
    catch(InvalidParameterException eX)
    {
      // Test passed
      assertEquals(0, testSuiteRoot.getTestSuites().size());
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
   * Unique identifier per test suite which is not empty.
   * Test the getId-function.
   */
  @Test
  public void testThatGetIdReturnsTheTestSuiteId()
  {
    try
    {
      LOGGER.debug("TC: testThatGetIdReturnsTheTestSuiteId");
      
      TestSuite testSuite = new TestSuite("qwerty");
      
      assertTrue(testSuite.getId().contentEquals("qwerty"));
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
   * Unique identifier per test suite which is not empty.
   * Set parent with a valid identifier
   */
  @Test
  public void testThatTestSuiteParentCanBeSetAndRetrieved()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteParentCanBeSetAndRetrieved");
      
      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty", parent);

      assertTrue(testSuite.hasParent());
      assertFalse(parent.hasParent());
      assertTrue(testSuite.getParent() == parent);
      assertNull(parent.getParent());
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
   * Unique identifier per test suite which is not empty.
   * Reset parent with identifier 'null'
   */
  @Test
  public void testThatTestSuiteParentCanBeCleared()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteParentCanBeCleared");
      
      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty");

      testSuite.setParent(parent);

      assertTrue(testSuite.hasParent());
      assertTrue(testSuite.getParent() == parent);

      testSuite.setParent(null);

      assertFalse(testSuite.hasParent());
      assertNull(testSuite.getParent());
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
   * A parent test suite can be set after creating a test suite.
   */
  @Test
  public void testTestSuiteParentCanBeSetAfterCreatingTestSuite()
  {
    try
    {
      LOGGER.debug("TC: testTestSuiteParentCanBeSetAfterCreatingTestSuite");

      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty");
      testSuite.setParent(parent);

      assertTrue(testSuite.hasParent());
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
   * A parent test suite can be set after adding as a child.
   */
  @Test
  public void testTestSuiteParentLinkAfterAddingAsChildTestSuite()
  {
    try
    {
      LOGGER.debug("TC: testTestSuiteParentLinkAfterAddingAsChildTestSuite");

      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty");
      parent.addTestSuite(testSuite);
      
      assertTrue(parent.getTestSuite("qwerty") == testSuite);
      assertTrue(testSuite.getParent() == parent);
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
   * Each test suite can have an optional parent test suite. 
   */
  @Test
  public void testThatTheRootTestSuiteCanBeRetrieved()
  {
    try
    {
      LOGGER.debug("TC: testThatTheRootTestSuiteCanBeRetrieved");
      
      // Use the pre-defined test suite structure for this test
      assertTrue(subTestSuite.getRootParent().equals(testRoot));
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
   * Each test suite can have an optional parent test suite. 
   */
  @Test
  public void testThatTestSuiteCanBeTheRootTestSuite()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuiteCanBeTheRootTestSuite");
      
      TestSuite testSuite = new TestSuite("qwerty");
      assertTrue(testSuite.getRootParent() == testSuite);
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
   * Test suites can be added to a test suite and retrieved in the same order.
   */
  @Test
  public void testThatTestSuitesAddedToTestSuitesCanBeRetrievedInTheSameOrder()
  {
    try
    {
      LOGGER.debug("TC: testThatTestSuitesAddedToTestSuitesCanBeRetrievedInTheSameOrder");
      
      TestSuite parent = new TestSuite("parent");
      TestSuite child1 = new TestSuite("child1");
      TestSuite child2 = new TestSuite("child2");
      TestSuite child3 = new TestSuite("child3");
      LinkedHashMap<String, TestSuite> testSuites;
      
      parent.addTestSuite(child1);
      parent.addTestSuite(child2);
      parent.addTestSuite(child3);
      
      testSuites = parent.getTestSuites();
      
      assertTrue(testSuites.size() == 3);
      // TODO update to get the required element
      assertTrue(testSuites.get(0).equals(child1));
      assertTrue(testSuites.get(1).equals(child2));
      assertTrue(testSuites.get(2).equals(child3));
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
   * Test cases can be added to a test suite and retrieved in the same order.
   */
  @Test
  public void testThatTestCasesAddedToTestSuitesCanBeRetrievedInTheSameOrder()
  {
    try
    {
      LOGGER.debug("TC: testThatTestCasesAddedToTestSuitesCanBeRetrievedInTheSameOrder");
      
      TestSuite parent = new TestSuite("parent");
      TestCase child1 = new TestCase("child1");
      TestCase child2 = new TestCase("child2");
      TestCase child3 = new TestCase("child3");
      LinkedHashMap<String, TestCase> testCases;
      
      parent.addTestCase(child1);
      parent.addTestCase(child2);
      parent.addTestCase(child3);
      
      testCases = parent.getTestCases();
      
      assertTrue(testCases.size() == 3);
      // TODO update to get the required element
      assertTrue(testCases.get(0).equals(child1));
      assertTrue(testCases.get(1).equals(child2));
      assertTrue(testCases.get(2).equals(child3));
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
   * (Child) test suites can be count for current level
   */
  @Test
  public void testTheNumberOfTestSuitesOnCurrentLevelCanBeCounted()
  {
    try
    {
      LOGGER.debug("TC: testTheNumberOfTestSuitesOnCurrentLevelCanBeCounted");
      
      assertEquals(1, testRoot.numberOfTestSuites());
      assertEquals(1, testRoot.numberOfTestSuites(false));
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
   * (Child) test suites can be count for current and lower level
   */
  @Test
  public void testTheNumberOfTestSuitesOnCurrentAndLowerLevelsCanBeCounted()
  {
    try
    {
      LOGGER.debug("TC: testTheNumberOfTestSuitesOnCurrentAndLowerLevelsCanBeCounted");

      assertEquals(7, testRoot.numberOfTestSuites(true));
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
   * (Child) test suites can be searched for a specific test suite
   */
  @Test
  public void testTestSuitesCanBeSearchedForSpecificTestSuite()
  {
    try
    {
      LOGGER.debug("TC: testTestSuitesCanBeSearchedForSpecificTestSuite");

      assertTrue(testRoot.hasTestSuite(childTestSuite.getId()));
      assertFalse(testRoot.hasTestSuite(subTestSuite.getId(), false));
      assertTrue(testRoot.hasTestSuite(subTestSuite.getId(), true));
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
   * (Child) test suites can be searched for a specific test case
   */
  @Test
  public void testTestSuitesCanBeSearchedForSpecificTestCase()
  {
    try
    {
      LOGGER.debug("TC: testTestSuitesCanBeSearchedForSpecificTestCase");
      
      assertTrue(childTestSuite.hasTestCase("Shl-00-01"));  
      assertTrue(childTestSuite.hasTestCase("Shl-00-01",false));         
      assertTrue(childTestSuite.hasTestCase("Shl-00-01",true));                 
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
   * Each test suite can have 0...* child test suites. Check that test suites
   * can be retrieved after adding to the set.
   */
  @Test
  public void getTestSuite()
  {
    try
    {
      TestSuite testSuite = new TestSuite("root");
      TestSuite child1 = new TestSuite("child1");
      TestSuite child2 = new TestSuite("child2");

      testSuite.addTestSuite(child1);
      testSuite.addTestSuite(child2);

      assertTrue(testSuite.getTestSuite("child1").equals(child1));
      assertTrue(testSuite.getTestSuites().get("child1").equals(child1));

      assertTrue(testSuite.getTestSuite("child2").equals(child2));
      assertTrue(testSuite.getTestSuites().get("child2").equals(child2));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }

  
  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have 0...* child test suites. Check that test suites
   * can be retrieved after adding to the set.
   */
  @Test
  public void checkGetAllTestSuites()
  {
    try
    {
      LinkedHashMap<String, TestSuite> allTestSuites = childTestSuite.getTestSuites();
      assertTrue(allTestSuites.size() == 2);
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }
  

  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can hold a collection of 0...* test cases
   */
  @Test
  public void addTestCaseWithValidIdentifier()
  {
    try
    {
      TestSuite testSuite = new TestSuite("ts");
      TestCase testCase1 = new TestCase("tc1");
      TestCase testCase2 = new TestCase("tc2");
      
      assertTrue(testSuite.getTestCases().size() == 0);
      
      testSuite.addTestCase(testCase1);
      assertTrue(testSuite.getTestCases().size() == 1);
      
      testSuite.addTestCase(testCase2);
      assertTrue(testSuite.getTestCases().size() == 2);      
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }



  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can hold a collection of 0...* test cases
   * Validate that each test case identifier is unique
   */
  @Test
  public void addTestCaseWithExistingIdentifier()
  {
    try
    {

      TestSuite testSuite = new TestSuite("ts");
      TestCase testCase1 = new TestCase("tc1");
      
      assertTrue(testSuite.getTestCases().size() == 0);
      
      testSuite.addTestCase(testCase1);
      assertTrue(testSuite.getTestCases().size() == 1);
      
      testSuite.addTestCase(testCase1);

      fail("A test case can only be added once to a test suite!");
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
   * Each test suite can hold a collection of 0...* test cases.
   * Check for correct number of child test suites.
   */
  @Test
  public void CountChildTestCases()
  {
    try
    {
      TestSuite testSuite = new TestSuite("root");

      assertEquals(0, testSuite.numberOfTestCases());
      assertEquals(0, testSuite.numberOfTestCases(false));
      assertEquals(0, testSuite.numberOfTestCases(true));

      testSuite.addTestCase(new TestCase("child1"));
      assertEquals(1, testSuite.numberOfTestCases());
      assertEquals(1, testSuite.numberOfTestCases(false));
      assertEquals(1, testSuite.numberOfTestCases(true));

      testSuite.addTestCase(new TestCase("child2"));
      assertEquals(2, testSuite.numberOfTestCases(false));
      assertEquals(2, testSuite.numberOfTestCases(true));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can hold a collection of 0...* test cases.
   * Check for correct number of child test suites.
   */
  @Test
  public void CountAllLevelsChildTestCases()
  {
    assertEquals(2, testRoot.numberOfTestCases());
    assertEquals(2, testRoot.numberOfTestCases(false));
    assertEquals(20, testRoot.numberOfTestCases(true));
  }
  


  
  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can hold a collection of 0...* test cases.
   * Validate that a test case is added to a sub-test suite.
   */
  @Test
  public void checkHasTestCaseInSubTestSuites()
  {
    try
    {
      assertFalse(testRoot.hasTestCase("Web-01-02-01-01"));  
      assertFalse(testRoot.hasTestCase("Web-01-02-01-01",false));         
      assertTrue(testRoot.hasTestCase("Web-01-02-01-01",true));                 
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  } 

  
  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can hold a collection of 0...* test cases.
   * Validate that added test cases can be retrieved from the test suite.
   */
  @Test
  public void checkGetTestCase()
  {
    try
    {
      TestSuite testSuite = new TestSuite("ts");
      TestCase testCase1 = new TestCase("tc1");
      TestCase testCase2 = new TestCase("tc2");
      TestCase testCase3 = new TestCase("tc3");
      
      
      testSuite.addTestCase(testCase1);
      testSuite.addTestCase(testCase2);
      testSuite.addTestCase(testCase3);
      assertTrue(testSuite.getTestCase("tc1") == testCase1); 
      assertTrue(testSuite.getTestCase("tc2") == testCase2); 
      assertTrue(testSuite.getTestCase("tc3") == testCase3);     
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /**
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can hold a collection of 0...* test cases.
   * Validate that added test cases can be retrieved from the test suite.
   */
  @Test
  public void checkGetTestCases()
  {
    try
    {
      TestSuite testSuite = new TestSuite("ts");
      TestCase testCase1 = new TestCase("tc1");
      TestCase testCase2 = new TestCase("tc2");
      TestCase testCase3 = new TestCase("tc3");
      
      testSuite.addTestCase(testCase1);
      testSuite.addTestCase(testCase2);
      testSuite.addTestCase(testCase3);
      
      LinkedHashMap<String, TestCase> testCases = testSuite.getTestCases();
      
      
      assertTrue(testCases.size() == 3);   
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }

}
