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
import java.util.LinkedList;
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
  private LinkedList<TestCase> testCases;


  /**
   * Constructor. Provided ID must be unique and is an identifier for the input
   * plug-in to locate the test suite
   *
   * @param newId
   */
  public TestSuite(String newId) throws InvalidParameterException
  {

  }


  /**
   * Return the ID of this test suite
   *
   * @return
   */
  public String getId()
  {
    return null;
  }


  /**
   * Configure a parent test suite.
   * Use NULL to set no parent.
   *
   * @param testSuite
   */
  public void setParent(TestSuite testSuite)
  {

  }


  /**
   * Add the given {@link testSuite} as a child to this test suite
   *
   * @param testSuite
   *
   * @throws NullPointerException
   */
  public void addTestSuite(TestSuite testSuite) throws NullPointerException
  {

  }


  /**
   * Add the given {@link testCase} to this test suite
   *
   * @param testCase
   *
   * @throws NullPointerException
   */
  public void addTestCase(TestCase testCase) throws NullPointerException
  {

  }


  /**
   * Return a list of the sub-test suites in this test suite
   *
   * @return
   */
  public LinkedList<TestSuite> getTestSuites()
  {
    return null;
  }


  /**
   * Return a list of the test cases in this test suite
   *
   * @return
   */
  public LinkedList<TestCase> getTestCases()
  {
    return null;
  }

}
