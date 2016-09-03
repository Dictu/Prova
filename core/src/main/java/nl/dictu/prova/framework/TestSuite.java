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
 * Date:      23-08-2016
 * Author(s): Sjoerd Boerhout
 * <p>
 */
package nl.dictu.prova.framework;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sjoerd Boerhout
 */
public class TestSuite
{

  private final static Logger LOGGER = LogManager.getLogger(TestSuite.class.
          getName());

  private String id;
  private TestSuite parent;
  private LinkedHashMap<String, TestSuite> testSuites = new LinkedHashMap<>();
  private LinkedHashMap<String, TestCase> testCases = new LinkedHashMap<>();


  /**
   * Constructor. Provided ID must be unique and is an identifier for the input
   * plug-in to locate the test suite
   *
   * @param id
   *
   * @throws InvalidParameterException
   */
  public TestSuite(String id) throws InvalidParameterException
  {
    LOGGER.debug("Create a new test suite with id '{}'", () -> id);

    setId(id);
  }


  /**
   * Constructor with parent. Provided ID must be unique and is an identifier
   * for the input plug-in to locate the test suite.
   *
   * @param id
   * @param parent
   *
   * @throws InvalidParameterException
   */
  public TestSuite(String id, TestSuite parent) throws
          InvalidParameterException
  {
    LOGGER.debug("Create new test suite with id '{}' and parent {}", () -> id,
                 () -> parent == null ? "" : parent.getId());

    setId(id);
    setParent(parent);
  }



  /**
   * Set the ID of this test suite.
   * Id must be != null and trimmed length > 0
   *
   * @param id
   *
   * @throws InvalidParameterException
   *
   * @return
   */
  private void setId(String id) throws
          InvalidParameterException
  {
    LOGGER.trace("Set id of test suite to '{}'", () -> id);

    if(id == null)
    {
      LOGGER.debug("Id can not be null ({})", () -> id);
      throw new InvalidParameterException("Id can not be null");
    }

    if(id.trim().length() < 1)
    {
      LOGGER.debug("Invalid testsuite Id ({})", () -> id);
      throw new InvalidParameterException("Invalid testsuite Id (" + id + ")");
    }

    this.id = id.trim();
  }


  /**
   * Return the ID of this test suite
   *
   * @return
   */
  public String getId()
  {
    LOGGER.trace("Get the id of this test suite ({})", () -> id);

    return id;
  }



  /**
   * Configure a parent test suite.
   * Use NULL to set no parent.
   *
   * @param testSuite
   *
   * @throws InvalidParameterException
   */
  public void setParent(TestSuite testSuite) throws InvalidParameterException
  {
    LOGGER.trace("Set the parent of this test suite to ({})",
                 () -> testSuite == null ? "null" : testSuite.getId());

    if(id != null && hasTestSuite(testSuite.getId(), true))
    {
      LOGGER.debug(
              "This testsuite already exists (as a child) in this suite! ({})",
              () -> testSuite.getId());
      throw new InvalidParameterException(
              "This testsuite already exists (as a child) in this suite!");
    }

    parent = testSuite;
  }


  /**
   * Check if this test suite has a parent.
   * <p>
   * @return
   */
  public Boolean hasParent()
  {
    LOGGER.trace("Check if this test suite has a parent ({})",
                 () -> parent != null ? "Yes: " + parent.getId() : "No");

    return (parent != null);
  }


  /**
   * Get the parent of this test suite.
   * Returns NULL when no parent is set.
   * <p>
   * @return
   */
  public TestSuite getParent()
  {
    LOGGER.trace("Get the parent of this test suite ({})",
                 () -> parent == null ? "No parent" : parent.getId());

    return parent;
  }


  /**
   * Get the root parent of this test suite.
   * Returns NULL when no parent is set.
   * <p>
   * @return
   */
  public TestSuite getRootParent()
  {
    LOGGER.trace("Get the root parent of this test suite ({})",
                 () -> parent == null ? "No parent" : parent.getId());

    TestSuite rootTestSuite = this;

    while(rootTestSuite.getParent() != null)
    {
      rootTestSuite = rootTestSuite.getParent();
    }

    LOGGER.debug("Rootparent of '{}': '{}'", getId(), rootTestSuite.getId());

    return rootTestSuite;
  }



  /**
   * Add the given {@link testSuite} as a child to this test suite
   *
   * @param testSuite
   *
   * @throws InvalidParameterException
   */
  public void addTestSuite(TestSuite testSuite) throws InvalidParameterException
  {
    LOGGER.trace("Add test suite '{}' to this test suite",
                 () -> testSuite != null ? testSuite.getId() : "null");

    if(testSuite == null)
    {
      throw new InvalidParameterException("testSuite can not be null");
    }

    // First check if this test suite doesn't exist yet in the structure
    if(!this.hasTestSuite(testSuite.getRootParent().getId(), true))
    {
      testSuites.put(testSuite.getId(), testSuite);
      testSuite.setParent(this);
    }
    else
    {
      throw new InvalidParameterException("TestSuite " + testSuite.getId()
              + " is already a member of this testsuite.");
    }
  }


  /**
   * Get the number of sub-test suites
   *
   * @return
   */
  public int numberOfTestSuites()
  {
    LOGGER.trace("Count the number of test suites in this test suite");

    return numberOfTestSuites(false);
  }


  /**
   * Get the number of child test suites of this test suite. When
   * countSubTestSuites is set to true all sub-suites are counted too.
   *
   * @param countSubTestSuites
   *
   * @return
   */
  public int numberOfTestSuites(boolean countSubTestSuites)
  {
    LOGGER.trace(
            "Count the number of test suites in this test suite. (Count subTestSuites: {}",
            () -> countSubTestSuites);

    int iCount = testSuites.size();

    if(countSubTestSuites)
    {
      iCount = testSuites.entrySet().stream().
              map((entry) -> entry.getValue().numberOfTestSuites(
                      countSubTestSuites)).
              reduce(iCount, Integer::sum);
    }

    return iCount;
  }


  /**
   * Check if test suite has a test suite with {@link id}
   *
   * @param id
   *
   * @return
   */
  public Boolean hasTestSuite(String id)
  {
    LOGGER.trace(
            "Check if this test suites has a test suite with id '{}'", () -> id);

    return hasTestSuite(id, false);
  }


  /**
   * Check if test suite has a test suite with {@link id}
   *
   * @param id
   * @param checkSubTestSuites
   *
   * @return
   */
  public Boolean hasTestSuite(String id, boolean checkSubTestSuites)
  {
    LOGGER.trace(
            "Check if this test suites has a test suite with id '{}' (Check subTestSuites: {}",
            () -> id, () -> checkSubTestSuites);

    if(this.getId().equals(id))
    {
      return true;
    }

    for(Map.Entry<String, TestSuite> entry : testSuites.entrySet())
    {
      if(entry.getValue().getId().equals(id))
      {
        return true;
      }

      if(checkSubTestSuites
              && entry.getValue().hasTestSuite(id, checkSubTestSuites))
      {
        return true;
      }
    }

    return false;
  }


  /**
   * Return the test suite with {@link id}
   *
   * @param id
   *
   * @return
   *
   * @throws NoSuchElementException
   *
   */
  public TestSuite getTestSuite(String id) throws NoSuchElementException
  {
    LOGGER.trace("Find and return sub test suites with id '{}'", () -> id);

    try
    {
      return this.testSuites.get(id);
    }
    catch (Exception eX)
    {
      LOGGER.debug("Element '{}' not found ({})", () -> id, () -> eX.
                   getMessage());

      throw new NoSuchElementException(id);
    }

  }


  /**
   * Return a list of the sub-test suites in this test suite
   *
   * @return
   */
  public LinkedHashMap<String, TestSuite> getTestSuites()
  {
    LOGGER.trace("Return all test suites (nr: {})", testSuites.size());

    return testSuites;
  }


  /**
   * Add the given {@link testCase} to this test suite
   *
   * @param testCase
   *
   * @throws InvalidParameterException
   */
  public void addTestCase(TestCase testCase) throws InvalidParameterException
  {

    LOGGER.trace("Add test case '{}' to this test suite",
                 () -> testCase != null ? testCase.getId() : "null");

    if(testCase == null)
    {
      throw new InvalidParameterException("testSuite can not be null");
    }

    if(this.hasTestCase(testCase.getId()))
    {
      throw new InvalidParameterException(
              "TestCase " + testCase.getId() + " is already a member of this testsuite.");
    }

    testCases.put(testCase.getId(), testCase);
  }


  /**
   * Get the number of test cases of this test suite
   *
   * @return
   */
  public int numberOfTestCases()
  {
    LOGGER.trace("Count the number of test cases in this test suite");

    return numberOfTestCases(false);
  }


  /**
   * Get the number of test cases of this test suite. When
   * countSubTestCases is set to true all sub-cases are counted too.
   *
   * @param countSubTestCases
   *
   * @return
   */
  public int numberOfTestCases(boolean countSubTestCases)
  {
    LOGGER.trace("Count the number of test cases in this test suite");

    int iCount = testCases.size();

    if(countSubTestCases)
    {
      iCount = testSuites.entrySet().stream().
              map((entry) -> entry.getValue().numberOfTestCases(
                      countSubTestCases)).
              reduce(iCount,
                     Integer::sum);
    }

    return iCount;
  }


  /**
   * Check if test suite has a test case with {@link id}
   *
   * @param id
   *
   * @return
   */
  public Boolean hasTestCase(String id)
  {
    return hasTestCase(id, false);
  }


  /**
   * Check if test suite has a test case with {@link id}
   *
   * @param id
   * @param checkSubTestSuites
   *
   * @return
   */
  public Boolean hasTestCase(String id, boolean checkSubTestSuites)
  {
    LOGGER.trace(
            "Check if this test case has a test case with id '{}' (Check subTestSuites: {}",
            () -> id, () -> checkSubTestSuites);

    for(Map.Entry<String, TestCase> entry : testCases.entrySet())
    {
      if(entry.getValue().getId().equals(id))
      {
        return true;
      }
    }

    if(checkSubTestSuites)
    {
      if(testSuites.entrySet().stream().
              anyMatch((entry) -> (entry.getValue().hasTestCase(id,
                                                                checkSubTestSuites))))
      {
        return true;
      }
    }

    return false;
  }


  /**
   * Return the test case with {@link id}
   *
   * @param id
   *
   * @return
   */
  public TestCase getTestCase(String id)
  {
    LOGGER.trace("Find and return sub test case with id '{}'", () -> id);

    try
    {
      return testCases.get(id);
    }
    catch (Exception eX)
    {
      LOGGER.debug("Element '{}' not found ({})", () -> id, () -> eX.
                   getMessage());

      throw new NoSuchElementException(id);
    }

  }


  /**
   * Return a list of the test cases in this test suite
   *
   * @return
   */
  public LinkedHashMap<String, TestCase> getTestCases()
  {
    return testCases;
  }

}
