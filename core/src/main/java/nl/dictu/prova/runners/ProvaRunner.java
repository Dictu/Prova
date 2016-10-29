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
package nl.dictu.prova.runners;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import nl.dictu.prova.TestRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base class for Prova runners providing some common functions
 *
 * @author Sjoerd Boerhout
 */
public class ProvaRunner
{

  private static Logger LOGGER = LogManager.getLogger(ProvaRunner.class.
          getName());

  protected TestRunner testRunner;
  protected Properties properties;


  /**
   * Constructor
   *
   * @param newLogger
   */
  protected ProvaRunner(Logger newLogger) throws NullPointerException
  {
    if(newLogger == null)
    {
      throw new NullPointerException("Logger instance is null");
    }

    LOGGER = newLogger;
    properties = new Properties();
  }


  /**
   * Initialize local variables
   * @throws java.lang.Exception
   */
  protected void init() throws Exception
  {
	LOGGER.debug("Init ProvaRunner");
    properties = new Properties();
  }


  /**
   * Retrieve the root path of this Prova instance
   *
   * @return
   */
  protected String getProvaRootPath()
  {
    return "";
  }


  /**
   * Load properties from:
   * - The included resource file with default Prova properties
   * - The default properties file from the 'config' directory
   *
   * @return
   *
   * @throws FileNotFoundException
   * @throws InvalidPropertiesFormatException
   */
  protected Properties loadDefaultProperties() throws FileNotFoundException,
                                                      InvalidPropertiesFormatException
  {
    return new Properties();
  }


  /**
   * Load the project specific properties (if available)
   *
   * @param projectName
   * @return
   *
   * @throws FileNotFoundException
   * @throws InvalidPropertiesFormatException
   */
  protected Properties loadProjectPropertiesFromFile(String projectName) throws
          InvalidPathException, FileNotFoundException,
          InvalidPropertiesFormatException
  {
    return new Properties();
  }


  /**
   * Load properties from the given resource file
   *
   * @param fileName
   *
   * @return
   *
   * @throws FileNotFoundException
   * @throws InvalidPropertiesFormatException
   */
  protected Properties loadPropertiesFromResource(File fileName) throws
          InvalidPathException, FileNotFoundException,
          InvalidPropertiesFormatException
  {
    return new Properties();
  }


  /**
   * Load properties from the given file
   *
   * @param fileName
   *
   * @return
   *
   * @throws FileNotFoundException
   * @throws InvalidPropertiesFormatException
   */
  protected Properties loadPropertiesFromFile(File fileName) throws
          InvalidPathException, FileNotFoundException,
          InvalidPropertiesFormatException
  {
    return new Properties();
  }


  /**
   * Update the log level for Log4j2
   *
   * @param loggerName
   * @param newLogLevel
   *
   * @return
   *
   * @throws IllegalArgumentException
   */
  protected String setLogLevel(String loggerName, String newLogLevel) throws
          IllegalArgumentException
  {
    return "currentLogLevel";
  }


  /**
   * Update the log pattern for files for Log4j2
   *
   * @param newLogPattern
   *
   * @return
   *
   * @throws IllegalArgumentException
   */
  protected String setLogLevelPatternFile(String newLogPattern) throws
          IllegalArgumentException
  {
    return "currentLogLevel";
  }


  /**
   * Update the log pattern for std out for Log4j2
   *
   * @param newLogPattern
   *
   * @return
   *
   * @throws IllegalArgumentException
   */
  protected String setLogLevelPatternStdOut(String newLogPattern) throws
          IllegalArgumentException
  {
    return "currentLogLevel";
  }

}
