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


  /*
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
    String line = level + ":";

    for(int i = 0; i < level; i++)
    {
      line += " ";
    }
    line += name;

    LOGGER.trace(line);
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


  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Create a test suite with a valid identifier.
   */
  @Test
  public void createTestSuiteWithValidIdentifier()
  {
    try
    {
      TestSuite testSuite = new TestSuite("qwerty");
      assertTrue(testSuite.getId().contentEquals("qwerty"));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Validate that ' ' is not a valid identifier
   */
  @Test
  public void createTestSuiteWithEmptyIdentifier()
  {
    try
    {
      @SuppressWarnings("unused")
      TestSuite tmpTestSuite1 = new TestSuite(" ");

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
   * Unique identifier per test suite which is not empty.
   * Validate that 'null' is not a valid identifier
   */
  @Test
  public void createTestSuiteWithNullAsIdentifier()
  {
    try
    {
      @SuppressWarnings("unused")
      TestSuite tmpTestSuite1 = new TestSuite(null);

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
   * Unique identifier per test suite which is not empty.
   * Create a test suite with a parent and a valid identifier.
   */
  @Test
  public void createTestSuiteWithValidIdentifierAndParent()
  {
    try
    {
      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty", parent);

      assertTrue(testSuite.getId().contentEquals("qwerty"));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Validate that ' ' is not a valid identifier
   */
  @Test
  public void createTestSuiteWithEmptyIdentifierAndParent()
  {
    try
    {
      TestSuite parent = new TestSuite("parent");
      @SuppressWarnings("unused")
      TestSuite testSuite = new TestSuite(" ", parent);

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
   * Unique identifier per test suite which is not empty.
   * Validate that 'null' is not a valid identifier
   */
  @Test
  public void createTestSuiteWithoutParentAndNullAsIdentifier()
  {
    try
    {
      TestSuite parent = new TestSuite("parent");
      @SuppressWarnings("unused")
      TestSuite testSuite = new TestSuite(null, parent);

      fail("'Null' identifier is not allowed!");
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
   * Unique identifier per test suite which is not empty.
   * Test the getId-function.
   */
  @Test
  public void getId()
  {
    try
    {
      TestSuite testSuite = new TestSuite("qwerty");
      assertTrue(testSuite.getId().contentEquals("qwerty"));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }
  
  
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Set parent with a valid identifier
   */
  @Test
  public void checkSetParentWithSetParent()
  {
    try
    {
      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty");
      
      testSuite.setParent(parent);
      
      assertTrue(testSuite.hasParent());
    }
    catch(InvalidParameterException eX)
    {
      fail("Identifier 'parent' is valid!");
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Reset parent with identifier 'null'
   */
  @Test
  public void checkSetParentWithResetParent()
  {
    try
    {
      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty");
      
      testSuite.setParent(parent);
      
      assertTrue(testSuite.hasParent());
      
      testSuite.setParent(null);
      
      assertFalse(testSuite.hasParent());
    }
    catch(InvalidParameterException eX)
    {
      fail("Identifier 'null' is valid to reset the parent!");
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }

  
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Check if test suite has a parent with valid parent
   */
  @Test
  public void checkHasParentWithParent()
  {
    try
    {
      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite1 = new TestSuite("qwerty", parent);
      TestSuite testSuite2 = new TestSuite("qwerty");
      testSuite2.setParent(parent);
      
      assertTrue(testSuite1.hasParent());
      assertTrue(testSuite2.hasParent());
    }
    catch(InvalidParameterException eX)
    {
      fail("Identifier 'parent' is valid!");
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Check if test suite has a parent without a parent
   */
  @Test
  public void checkHasParentWithoutParent()
  {
    try
    {
      TestSuite testSuite1 = new TestSuite("qwerty", null);
      TestSuite testSuite2 = new TestSuite("qwerty");
      testSuite2.setParent(null);
      
      assertFalse(testSuite1.hasParent());
      assertFalse(testSuite2.hasParent());
    }
    catch(InvalidParameterException eX)
    {
      fail("Identifier 'null' is valid!");
    }
    catch(Exception eX)
    {
      fail("Unexpected exception " + eX.getMessage());
    }
  }

  
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Check get parent test suite with a valid parent.
   */
  @Test
  public void checkGetParentWithValidParent()
  {
    try
    {
      TestSuite parent = new TestSuite("parent");
      TestSuite testSuite = new TestSuite("qwerty", parent);
      assertTrue(testSuite.getParent().getId().contentEquals("parent"));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Check get parent test suite with no parent.
   */
  @Test
  public void checkGetParentWithoutParent()
  {
    try
    {
      TestSuite testSuite = new TestSuite("qwerty");
      assertTrue(testSuite.getParent() == null);
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }

  

  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Check get root parent test suite with a valid parent.
   */
  @Test
  public void checkGetRootParentWithValidParent()
  {
    try
    {
      assertTrue(subTestSuite.getRootParent().equals(testRoot));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }
  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Unique identifier per test suite which is not empty.
   * Check get root parent test suite with no parent.
   */
  @Test
  public void checkGetRootParentWithoutParent()
  {
    try
    {
      TestSuite testSuite = new TestSuite("qwerty");
      assertTrue(testSuite.getRootParent() == testSuite);
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }

 
  /*
  public void setParent(TestSuite testSuite) throws InvalidParameterException
  public Boolean hasParent()
  public TestSuite getParent()
  public TestSuite getRootParent()
  */
  
  
  

  
  
  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each child test suite must be unique to prevent a loop. Validate that an
   * added test suite doesn't already exists in the tree.
   */
  @Test
  public void createTestSuiteWithDirectParentLoop()
  {
    TestSuite testSuiteRoot = null;

    try
    {
      testSuiteRoot = new TestSuite("root");
      TestSuite testSuite1 = new TestSuite("azerty", testSuiteRoot);

      testSuiteRoot.addTestSuite(testSuite1);

      fail("Child test suites must be unique!");
    }
    catch(Exception eX)
    {
      assertEquals(0, testSuiteRoot.getTestSuites().size());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each child test suite must be unique to prevent a loop. Validate that an
   * added test suite doesn't already exists in the tree.
   */
  @Test
  public void createTestSuiteWithIndirectParentLoop()
  {
    TestSuite testSuiteRoot = null;

    try
    {
      testSuiteRoot = new TestSuite("root");
      TestSuite testSuite1 = new TestSuite("azerty", testSuiteRoot);
      TestSuite testSuite2 = new TestSuite("azerty", testSuite1);

      testSuiteRoot.addTestSuite(testSuite2);

      fail("Child test suites must be unique!");
    }
    catch(Exception eX)
    {
      assertEquals(0, testSuiteRoot.getTestSuites().size());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each child test suite must be unique to prevent a loop Validate that an
   * added test suite doesn't already exists in the tree.
   */
  @Test
  public void createTestSuiteWithItselfAsParent()
  {
    TestSuite testSuiteRoot = null;

    try
    {
      testSuiteRoot = new TestSuite("root");

      testSuiteRoot.addTestSuite(testSuiteRoot);
      testSuiteRoot.setParent(testSuiteRoot);

      fail("A test suite can't be it's own parent!");
    }
    catch(Exception eX)
    {
      assertEquals(0, testSuiteRoot.getTestSuites().size());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have no or 1 parent. Create a test suite with no
   * parent.
   */
  @Test
  public void createTestSuiteWithNoParent()
  {
    try
    {
      TestSuite tmpTestSuite01 = new TestSuite("qwerty");

      assertNull(tmpTestSuite01.getParent());
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have non or 1 parent. Create a test suite with a parent
   * and check it.
   */
  @Test
  public void checkTestSuiteParent()
  {
    try
    {
      TestSuite tmpTestSuite01 = new TestSuite("qwerty");
      TestSuite tmpTestSuite02 = new TestSuite("azerty");
      tmpTestSuite02.setParent(tmpTestSuite01);

      assertTrue(tmpTestSuite02.getParent().equals(tmpTestSuite01));
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have no or 1 parent. Set and check the parent of a test
   * suite.
   */
  @Test
  public void checkIfTestSuiteHasParentViaConstructor()
  {
    try
    {
      TestSuite tmpTestSuite01 = new TestSuite("qwerty");
      TestSuite tmpTestSuite02 = new TestSuite("azerty");
      tmpTestSuite02.setParent(tmpTestSuite01);

      assertFalse(tmpTestSuite01.hasParent());
      assertTrue(tmpTestSuite02.hasParent());
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have no or 1 parent. Set and check the parent of a test
   * suite.
   */
  @Test
  public void checkIfTestSuiteHasParentViaSetter()
  {
    try
    {
      TestSuite tmpTestSuite01 = new TestSuite("qwerty");
      TestSuite tmpTestSuite02 = new TestSuite("azerty");

      assertFalse(tmpTestSuite02.hasParent());

      tmpTestSuite02.setParent(tmpTestSuite01);

      assertTrue(tmpTestSuite02.hasParent());
      assertFalse(tmpTestSuite01.hasParent());
      assertTrue(tmpTestSuite02.getParent().equals(tmpTestSuite01));

    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have 0...* child test suites. Check that test suites
   * can be added to the set.
   */
  @Test
  public void checkNumberOfTestSuites()
  {
    try
    {
      TestSuite testSuite = new TestSuite("root");
      TestSuite child1 = new TestSuite("child1");
      TestSuite child2 = new TestSuite("child2");

      assertEquals(0, testSuite.getTestSuites().size());

      testSuite.addTestSuite(child1);
      assertEquals(1, testSuite.getTestSuites().size());

      testSuite.addTestSuite(child2);
      assertEquals(2, testSuite.getTestSuites().size());
    }
    catch(Exception eX)
    {
      fail(eX.getMessage());
    }
  }


  /*
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


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Look in provided structure for a direct child test suite.
   */
  @Test
  public void hasChildTestSuite()
  {
    assertTrue(testRoot.hasTestSuite(childTestSuite.getId()));
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Check recursively all number of child test suites if a specific test suite
   * doesn't already exists.
   */
  @Test
  public void hasChildTestSuiteOnASubLevel()
  {
    assertFalse(testRoot.hasTestSuite(subTestSuite.getId()));
    // assertFalse(testRoot.hasTestSuite(subTestSuite, false));

    assertTrue(testRoot.hasTestSuite(subTestSuite.getId(), true));
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have 0...* child test suites. Check for correct number
   * of child test suites.
   */
  @Test
  public void CountAllLevelsChildTestSuites()
  {
    assertEquals(1, testRoot.numberOfTestSuites());
    assertEquals(1, testRoot.numberOfTestSuites(false));
    assertEquals(7, testRoot.numberOfTestSuites(true));
  }


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have 0...* child test suites. Check for correct number
   * of child test suites.
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


  /*
   * PROVA-12: Structure to handle test suites, cases and actions
   * Requirement:
   * Each test suite can have 0...* child test suites. Check for correct number of child test suites.
   */
  @Test
  public void CountAllLevelsChildTestCases()
  {
    assertEquals(2, testRoot.numberOfTestCases());
    assertEquals(2, testRoot.numberOfTestCases(false));
    assertEquals(20, testRoot.numberOfTestCases(true));
  }


  /**
   * Test of getId method, of class TestSuite.
   */
  @Test
  public void testGetId()
  {
  }


  /**
   * Test of setParent method, of class TestSuite.
   */
  @Test
  public void testSetParent()
  {
  }


  /**
   * Test of addTestSuite method, of class TestSuite.
   */
  @Test
  public void testAddTestSuite()
  {
  }


  /**
   * Test of addTestCase method, of class TestSuite.
   */
  @Test
  public void testAddTestCase()
  {
  }


  /**
   * Test of getTestSuites method, of class TestSuite.
   */
  @Test
  public void testGetTestSuites()
  {
  }


  /**
   * Test of getTestCases method, of class TestSuite.
   */
  @Test
  public void testGetTestCases()
  {
  }

}
