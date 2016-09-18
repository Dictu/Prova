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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * PROVA-12: Structure to handle test suites, cases and actions
 * <p>
 * Defines the different states a test case can have.
 *
 * @author Sjoerd Boerhout
 */
public enum TestStatus
{
  NOTRUN("NotRun"),
  BLOCKED("Blocked"),
  PASSED("Passed"),
  FAILED("Failed");

  private final static Logger LOGGER = LogManager.getLogger(TestStatus.class.
          getName());

  private final String name;


  private TestStatus(String name)
  {
    this.name = name;
  }


  /**
   * Get the name of this log level
   *
   * @return
   */
  @Override
  public String toString()
  {
    return name;
  }


  /**
   * Get the name for this test status
   *
   * @return
   */
  public String getValue()
  {
    return name;
  }


  /**
   * Find enum by it's name
   *
   * @param name
   *
   * @return
   */
  public static TestStatus lookup(String name) throws IllegalArgumentException
  {
    LOGGER.trace("Lookup for enum with value '{}'", name);

    try
    {
      name = name.toUpperCase();

      for(TestStatus testStatus : TestStatus.values())
      {
        if(testStatus.name().equalsIgnoreCase(name))
        {
          LOGGER.trace("Found enum with value '{}'", name);

          return testStatus;
        }
      }
    }
    catch (Exception eX)
    {
      LOGGER.error("'{}' not found in TestStatus. ({})", name, eX);
    }

    LOGGER.trace("Lookup for enum with value '{}' failed.", name);

    throw new IllegalArgumentException(name + " not found in TestStatus");
  }
}
