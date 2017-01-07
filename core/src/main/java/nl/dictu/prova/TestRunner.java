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
 * Date: 23-08-2016
 * Author(s): Sjoerd Boerhout
 * <p>
 */

package nl.dictu.prova;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nl.dictu.prova.framework.TestSuite;
import nl.dictu.prova.plugins.input.InputPlugin;
import nl.dictu.prova.plugins.output.OutputPlugin;
import nl.dictu.prova.plugins.reporting.ReportingPlugin;
import nl.dictu.prova.util.PluginLoader;


/**
 * This class is the base class for each test runner and acts as the central
 * part of Prova.
 * It load, initializes and run all plug-ins to load and run test scripts.
 * The test runner is configured and started by a test runner (cli, gui, etc).
 * Issues:
 * - PROVA-9: Support for properties
 * 
 * @author Sjoerd Boerhout
 */
public abstract class TestRunner
{

  private static Logger LOGGER = LogManager.getLogger(TestRunner.class.getName());

  private PluginLoader pluginLoader;

  private LinkedHashMap<String, InputPlugin> inputPlugins;
  private LinkedHashMap<String, OutputPlugin> outputPlugins;
  private LinkedHashMap<String, ReportingPlugin> reportingPlugins;

  private TestSuite rootTestSuite;

  private Properties properties;

  private Thread thread;


  /**
   * Constructor.
   * Set the local logger to the logger of the implementing class
   *
   * @param newLogger
   * @throws NullPointerException
   */
  protected TestRunner(Logger newLogger) throws Exception
  {
    if(newLogger == null)
    {
      throw new NullPointerException("Logger instance is null");
    }

    LOGGER = newLogger;

    loadDefaultProperties();
  }


  /**
   * Load the default configuration from resource file
   */
  private void loadDefaultProperties() throws Exception
  {
    try
    {
      properties = new Properties();
      
      // Include system properties for file.separator and related properties
      properties.putAll(System.getProperties());
      
      LOGGER.debug("Loaded {} properties from system", properties.size());
      
      if(LOGGER.isTraceEnabled())
      {
        for(String key : properties.stringPropertyNames())
        {
          LOGGER.trace("> " + key + " => " + properties.getProperty(key));
        }
      }
      
      // Load the default Prova settings from resource file
      LOGGER.trace("Load the hard coded Prova default properties");
      properties.putAll(loadPropertiesFromResource("/config/prova-defaults.prop"));
    }
    catch(Exception eX)
    {
      LOGGER.fatal(eX);
      throw eX;
    }
  }
  
  
  /**
   * Returns a list of all registered input plug-ins
   *
   * @return
   */
  public LinkedList<InputPlugin> getInputPlugins()
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Returns a list of all registered output plug-ins
   *
   * @return
   */
  public LinkedList<OutputPlugin> getOutputPlugins()
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Returns a list of all registered output plug-ins of the requested type
   *
   * @param testType
   * @return
   */
  public LinkedList<OutputPlugin> getOutputPlugins(TestType testType)
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Returns a list of all registered reporting plug-ins
   *
   * @return
   */
  public LinkedList<ReportingPlugin> getReportingPlugins()
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Registers the given input plug-in to the TestRunner
   *
   * @param inputPlugin
   */
  public void addInputPlugin(InputPlugin inputPlugin)
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Registers the given output plug-in to the TestRunner
   *
   * @param outputPlugin
   * @param testType
   */
  public void addOutputPlugin(OutputPlugin outputPlugin, TestType testType)
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Registers the given reporting plug-in to the TestRunner
   *
   * @param reportingPlugin
   */
  public void addReportingPlugin(ReportingPlugin reportingPlugin)
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Adds the provided test suite to the test runner. Test suites
   * are executed in the order of registration in the test runner
   *
   * @param testSuite
   * @param inputPlugin
   */
  public void addTestSuite(TestSuite testSuite, InputPlugin inputPlugin)
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Start the execution thread of the test runner
   */
  public void start()
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }


  /**
   * Wait until the execution of the test runner is finished
   */
  public void join()
  {
    throw new UnsupportedOperationException("Not supported yet."); // To change
                                                                   // body of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
                                                                   // Tools |
                                                                   // Templates.
  }
  
  
  /**
   * Load a set of properties from a resource
   * 
   * @param fileName
   * @return
   * @throws Exception
   */
  private Properties loadPropertiesFromResource(String fileName) throws Exception
  {
    Properties properties = new Properties();
    InputStream inputStream = null;
    
    try
    {      
      inputStream = this.getClass().getResourceAsStream(fileName);
    
      properties.load(inputStream);
    
      LOGGER.debug("Loaded {} properties from resource '{}'", properties.size(), fileName);
      
      if(LOGGER.isTraceEnabled())
      {
        for(String key : properties.stringPropertyNames())
        {
          LOGGER.trace("> " + key + " => " + properties.getProperty(key));
        }
      }
    }
    catch(Exception eX) 
    {
      LOGGER.trace("Failed to load hard coded default property file", eX.getMessage());
      throw eX;
    }
    finally
    {
      if(inputStream != null)
        inputStream.close();
    }
    
    return properties;
  }


  /**
   * Set or update the value of property with {@link key} to {@link value}
   *
   * @param key
   * @param value
   * @throws InvalidParameterException
   */
  public void setProperty(String key, String value) throws InvalidParameterException
  {
    try
    {
      LOGGER.debug("Set value of property with key '{}' to '{}'", () -> key, () -> value);

      if(key == null)
      {
        throw new InvalidParameterException("Key value 'null' is not allowed for value '" + value
                                            + "'!");
      }

      properties.put(key, value);
    }
    catch(InvalidParameterException eX)
    {
      LOGGER.warn("{}", eX.getMessage());
      throw eX;
    }
    catch(Exception eX)
    {
      LOGGER.error("Unhandled exception!", eX);
    }
  }


  /**
   * Set or update a set of properties with {@link properties}
   *
   * @param properties
   * @throws InvalidParameterException
   */
  public void setProperties(Properties newProperties) throws InvalidParameterException
  {
    try
    {
      LOGGER.debug("Add/update the values of {} supplied properties", () -> newProperties.size());

      if(newProperties == null)
      {
        throw new InvalidParameterException("Set of properties is 'null'!");
      }

      if(LOGGER.isTraceEnabled())
      {
        LOGGER.debug("Found {} properties to set:", () -> newProperties.size());

        for(String key : newProperties.stringPropertyNames())
        {
          LOGGER.trace(key + " => " + newProperties.getProperty(key));
        }
      }

      this.properties.putAll(newProperties);
    }
    catch(InvalidParameterException eX)
    {
      LOGGER.warn("{}", eX.getMessage());
      throw eX;
    }
    catch(Exception eX)
    {
      LOGGER.error("Unhandled exception!", eX);
    }
  }


  /**
   * Check if the given {@link key} exists in the properties
   *
   * @param key
   * @return
   */
  public boolean hasProperty(String key)
  {
    try
    {
      LOGGER.debug("Check if property with key '{}' exists: {}", () -> key,
                   () -> properties.containsKey(key) ? "yes" : "no");

      return properties.containsKey(key);
    }
    catch(Exception eX)
    {
      LOGGER.error("Unhandled exception! ({})", eX.getCause().getMessage(), eX);
    }

    return false;
  }


  /**
   * Get the value of the property with key {@link key}
   *
   * @param key
   * @return
   * @throws InvalidParameterException
   */
  public String getProperty(String key) throws InvalidParameterException
  {
    try
    {
      LOGGER.debug("Get value of property with key '{}'", () -> key);

      if(key == null)
      {
        throw new InvalidParameterException("Key value 'null' is not allowed!");
      }

      if(!hasProperty(key))
      {
        throw new InvalidParameterException("Key value '{}' doesn't exist!");
      }

      return properties.getProperty(key);
    }
    catch(InvalidParameterException eX)
    {
      LOGGER.warn("{}", eX.getMessage());
      throw eX;
    }
    catch(Exception eX)
    {
      LOGGER.error("Unhandled exception!", eX);
    }

    return "";
  }


  /**
   * Get a copy of all properties
   *
   * @return
   */
  public Properties getProperties() throws InvalidParameterException
  {
    try
    {
      LOGGER.debug("Get a copy of the whole collection of properties");

      if( !(properties instanceof Properties))
      {
        throw new InvalidParameterException("No valid set of properties to copy!");
      }

      Properties copyOfProperties = new Properties();
      copyOfProperties.putAll(properties);

      if(LOGGER.isTraceEnabled())
      {
        LOGGER.debug("Copied {} properties to return:", () -> copyOfProperties.size());

        for(String key : copyOfProperties.stringPropertyNames())
        {
          LOGGER.trace(key + " => " + copyOfProperties.getProperty(key));
        }
      }

      return copyOfProperties;
    }
    catch(InvalidParameterException eX)
    {
      LOGGER.warn("{}", eX.getMessage());
      throw eX;
    }
    catch(Exception eX)
    {
      LOGGER.error("Unhandled exception!", eX);
    }

    return new Properties();
  }
}
