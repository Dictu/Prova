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

/**
 * All configuration options are defined in this class with a public final
 * constant string for an actual up to date overview of all configuration
 * options available in Prova. Plug-ins extend this class with their own options
 *
 * @author Sjoerd Boerhout
 */
public class Config
{
  //                         Name in Prova                              Name in property file                           Type          Used for:
  public final static String PROVA_OS_FILE_SEPARATOR                    = "file.separator";                             // String,    OS file separator character
  
  public final static String PROVA_CONF_DIR                             = "prova.conf.dir";                             // String,    Relative dir with config files
  public final static String PROVA_CONF_FILE_PFX                        = "prova.conf.file.pfx";                        // String,    Prefix property filename for Prova 
  public final static String PROVA_CONF_FILE_DEF                        = "prova.conf.file.default";                    // String,    Default property file for Prova 
  public final static String PROVA_CONF_FILE_TEST                       = "prova.conf.file.test";                       // String,    Default property file for Prova 
  public final static String PROVA_CONF_FILE_USER                       = "prova.conf.file.user";                       // String,    Default property file for Prova
  public final static String PROVA_CONF_FILE_EXT                        = "prova.conf.file.ext";                        // String,    File extension for property files
  
  public final static String PROVA_DIR                                  = "prova.dir";                                  // String,    Root directory of Prova.
  public final static String PROVA_PROJECT                              = "prova.project";                              // String,    Name of the project Prova is testing
  public final static String PROVA_TIMEOUT                              = "prova.timeout";                              // Long,      0-180000 Ms default timeout on actions
  
  public final static String PROVA_CLI_CONFIG                           = "prova.cli.config";                           // String,    Full path to a properties file 
  public final static String PROVA_CLI_PLUGINS                          = "prova.cli.plugins";                          // String[],  Comma separated list of plug-ins 
  
  public final static String PROVA_ENV                                  = "prova.env";                                  // String,    Defines the environment (OTAP) to use
  public final static String PROVA_ENV_PFX                              = "prova.env";                                  // String[],  Environment prefix 
  
  public final static String PROVA_LOG_FILENAME                         = "prova.log.filename";                         // String,    Name of the Prova log file 
  public final static String PROVA_LOG_LEVEL                            = "prova.log.level";                            // String,    Log level for Prova 
  public final static String PROVA_LOG_DIR_ROOT                         = "prova.log.dir.root";                         // String,    Dir for writing log file
  public final static String PROVA_LOG_DIR_HIST                         = "prova.log.dir.history";                      // String,    Dir for saving old log files
  public final static String PROVA_LOG_EXT_TXT                          = "prova.log.ext.txt";                          // String,    Log file file extension
  public final static String PROVA_LOG_PATTERN_CONS                     = "prova.log.pattern.console";                  // String,    Log4j2 log pattern 
  public final static String PROVA_LOG_PATTERN_FILE                     = "prova.log.pattern.file";                     // String,    Log4j2 log pattern 
  
  public final static String PROVA_PLUGINS_OUT_WEB_LOCATOR_PFX          = "prova.plugins.out.web.locator";              // String,    Prefix for locators 
  public final static String PROVA_PLUGINS_OUT_SOAP_LOCATOR_PFX         = "prova.plugins.out.soap.locator";             // String,    Prefix for locators 
  public final static String PROVA_PLUGINS_OUT_DB_LOCATOR_PFX           = "prova.plugins.out.db.locator";               // String,    Prefix for locators
  
  public final static String PROVA_PLUGINS_OUT_WEB_BROWSER_PATH_CHROME  = "prova.plugins.out.web.browser.path.chrome";  // String,    Browser executable location 
  public final static String PROVA_PLUGINS_OUT_WEB_BROWSER_PATH_IE      = "prova.plugins.out.web.browser.path.ie";      // String,    Browser executable location 
  public final static String PROVA_PLUGINS_OUT_WEB_BROWSER_TYPE         = "prova.plugins.out.web.browser.type";         // String,    Browser for executing tests 
  public final static String PROVA_PLUGINS_OUT_WEB_BROWSER_PROFILE      = "prova.plugins.out.web.browser.profile";      // String,    Name for a browser profile to load 
  public final static String PROVA_PLUGINS_OUT_MAX_RETRIES              = "prova.plugins.out.web.maxretries";           // Integer,   Max number of retries of an action before failure 
  
  public final static String PROVA_PLUGINS_DIR                          = "prova.plugins.dir";                          // String,    Relative path to plug-ins directory
  public final static String PROVA_PLUGINS_EXT                          = "prova.plugins.ext";                          // String,    File extension for plug-ins   
  public final static String PROVA_PLUGINS_INPUT_PACKAGE                = "prova.plugins.in.package";                   // String,    Active input plug-in 
  public final static String PROVA_PLUGINS_INPUT                        = "prova.plugins.in";                           // String,    Active input plug-in 
  public final static String PROVA_PLUGINS_OUTPUT_WEB_PACKAGE           = "prova.plugins.out.web.package";              // String,    Active output plug-in for web 
  public final static String PROVA_PLUGINS_OUTPUT_WEB                   = "prova.plugins.out.web";                      // String,    Active output plug-in for web
  public final static String PROVA_PLUGINS_OUTPUT_SOAP_PACKAGE          = "prova.plugins.out.soap.package";             // String,    Active output plug-in for soap
  public final static String PROVA_PLUGINS_OUTPUT_SOAP                  = "prova.plugins.out.soap";                     // String,    Active output plug-in for soap
  public final static String PROVA_PLUGINS_OUTPUT_DB_PACKAGE            = "prova.plugins.out.db.package";               // String,    Active output plug-in for db 
  public final static String PROVA_PLUGINS_OUTPUT_DB                    = "prova.plugins.out.db";                       // String,    Active output plug-in for db 
  public final static String PROVA_PLUGINS_OUTPUT_SHELL_PACKAGE         = "prova.plugins.out.shell.package";            // String,    Active output plug-in for shell 
  public final static String PROVA_PLUGINS_OUTPUT_SHELL                 = "prova.plugins.out.shell";                    // String,    Active output plug-in for shell 
  public final static String PROVA_PLUGINS_REPORTING_PACKAGE            = "prova.plugins.reporting.package";            // Boolean,   Execute or only validate 
  public final static String PROVA_PLUGINS_REPORTING                    = "prova.plugins.reporting";                    // Boolean,   Execute or only validate 
  
  public final static String PROVA_PLUGINS_REPORTING_DIR                = "prova.plugins.reporting.dir";
  public final static String PROVA_PLUGINS_REPORTING_FILE               = "prova.plugins.reporting.file";               // String,    Full path to file for logging test results 
  
  public final static String PROVA_TESTS_EXECUTE                        = "prova.tests.execute";                        // Boolean,   Execute or only validate 
  public final static String PROVA_TESTS_FILTERS                        = "prova.tests.filters";                        // String[],  Comma separated filtering for test scripts
  public final static String PROVA_TESTS_ROOT                           = "prova.tests.root";                           // String,    Points to the root of the test scripts
  public final static String PROVA_TESTS_DATA_DIR                       = "prova.tests.data.dir";                       // String,    Dir name containing test data
  public final static String PROVA_TESTS_START                          = "prova.tests.start";                          // Integer,   (Re)Start the first test on this line
  public final static String PROVA_TESTS_DELAY                          = "prova.tests.delay";                          // Long,      Delay between test actions (for debugging)
}
