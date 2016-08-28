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
package nl.dictu.prova;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import nl.dictu.prova.framework.TestSuite;
import nl.dictu.prova.plugins.input.InputPlugin;
import nl.dictu.prova.plugins.output.OutputPlugin;
import nl.dictu.prova.plugins.reporting.ReportingPlugin;

/**
 *
 * @author Sjoerd Boerhout
 */
public interface TestRunner
{

  /**
   * Returns a list of all registered input plug-ins
   *
   * @return
   */
  public LinkedList<InputPlugin> getInputPlugins();


  /**
   * Returns a list of all registered output plug-ins
   *
   * @return
   */
  public LinkedList<OutputPlugin> getOutputPlugins();


  /**
   * Returns a list of all registered output plug-ins of the requested type
   *
   * @param testType
   *
   * @return
   */
  public LinkedList<OutputPlugin> getOutputPlugins(TestType testType);


  /**
   * Returns a list of all registered reporting plug-ins
   *
   * @return
   */
  public LinkedList<ReportingPlugin> getReportingPlugins();


  /**
   * Registers the given input plug-in to the TestRunner
   *
   * @param inputPlugin
   */
  public void addInputPlugin(InputPlugin inputPlugin);


  /**
   * Registers the given output plug-in to the TestRunner
   *
   * @param outputPlugin
   * @param testType
   */
  public void addOutputPlugin(OutputPlugin outputPlugin, TestType testType);


  /**
   * Registers the given reporting plug-in to the TestRunner
   *
   * @param reportingPlugin
   */
  public void addReportingPlugin(ReportingPlugin reportingPlugin);


  /**
   * Adds the provided test suite to the test runner. Test suites
   * are executed in the order of registration in the test runner
   *
   * @param testSuite
   * @param inputPlugin
   */
  public void addTestSuite(TestSuite testSuite, InputPlugin inputPlugin);


  /**
   * Set or update the value of property with {@link key} to {@link value}
   *
   * @param key
   * @param value
   */
  public void setProperty(String key, String value) throws NullPointerException;


  /**
   * Check if the given {@link key} exists in the properties
   *
   * @param key
   *
   * @return
   */
  public boolean hasProperty(String key);


  /**
   * Get the value of the property with key {@link key}
   *
   * @param key
   *
   * @return
   *
   * @throws InvalidParameterException
   */
  public String getProperty(String key) throws InvalidParameterException;


  /**
   * Start the execution of the test runner
   */
  public void start();


  /**
   * Wait until the execution of the test runner is finished
   */
  public void join();

}
