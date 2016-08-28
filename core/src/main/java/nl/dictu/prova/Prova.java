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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Properties;
import nl.dictu.prova.framework.TestSuite;
import nl.dictu.prova.plugins.input.InputPlugin;
import nl.dictu.prova.plugins.output.OutputPlugin;
import nl.dictu.prova.plugins.reporting.ReportingPlugin;
import nl.dictu.prova.util.PluginLoader;

/**
 *
 * @author Sjoerd Boerhout
 */
public class Prova implements TestRunner
{

  PluginLoader pluginLoader;

  LinkedHashMap<String, InputPlugin> inputPlugins;
  LinkedHashMap<String, OutputPlugin> outputPlugins;
  LinkedHashMap<String, ReportingPlugin> reportingPlugins;

  TestSuite rootTestSuite;

  Properties properties;

  Thread thread;


  /**
   * Constructor
   */
  public Prova()
  {

  }


  /**
   * Initialize the test runner.
   * - Create/initialize (local) variables
   * - Check if required items are available
   * - Load the plug-ins
   * - ...
   *
   * @throws Exception
   */
  public void init() throws Exception
  {

  }


  /**
   * Prepare everything for the execution fase:
   * - Load the structure of test suites and test cases
   * - Execute the configure (optional) projects setup script
   *
   * @throws Exception
   */
  public void setUp() throws Exception
  {

  }


  /**
   * Run the test suites
   *
   * @throws Exception
   */
  public void execute() throws Exception
  {

  }


  /**
   * Finish the test run.
   * - Execute the configure (optional) projects teardown script
   * - Log a summary
   * - ...
   *
   * @throws Exception
   */
  public void tearDown() throws Exception
  {

  }


  /**
   * Shutdown all plug-ins and end program execution
   *
   * @throws Exception
   */
  public void shutDown() throws Exception
  {

  }


  @Override
  public LinkedList<InputPlugin> getInputPlugins()
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public LinkedList<OutputPlugin> getOutputPlugins()
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public LinkedList<OutputPlugin> getOutputPlugins(TestType testType)
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public LinkedList<ReportingPlugin> getReportingPlugins()
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public void addInputPlugin(InputPlugin inputPlugin)
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public void addOutputPlugin(OutputPlugin outputPlugin, TestType testType)
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public void addReportingPlugin(ReportingPlugin reportingPlugin)
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public void addTestSuite(TestSuite testSuite, InputPlugin inputPlugin)
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public void setProperty(String key, String value) throws NullPointerException
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public boolean hasProperty(String key)
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public String getProperty(String key) throws InvalidParameterException
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public void start()
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public void join()
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
