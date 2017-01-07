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

import static org.junit.Assert.assertTrue;

/**
 * All configuration property names are defined in this class with a public final
 * constant string for an actual up to date overview of all configuration
 * properties available in Prova. 
 * Plug-ins extend this class with their own property names.
 * Rules:
 * - Property constants are defined in upper case using _ as separator
 * - Property names are defined in upper case using . as separator
 * - Property names are formatted like this:
 *   'prova' 'module' 'category' 'keyname'
 *
 * @author Sjoerd Boerhout
 */
public class Property
{
  //                         Name in Prova                Name in property file         Description & type
  
  public final static String SYSTEM_FILE_SEPARATOR        = "file.separator";           // System defined file separator (String)
  public final static String SYSTEM_LINE_SEPARATOR        = "line.separator";           // System defined line separator (String)
  public final static String SYSTEM_PATH_SEPARATOR        = "path.separator";           // System defined path separator (String)
  
  public final static String SYSTEM_OS_NAME               = "os.name";                  // OS Name (String)
  public final static String SYSTEM_OS_ARCHITECTURE       = "os.arch";                  // OS Architecture (String)
  public final static String SYSTEM_OS_VERSION            = "os.version";               // OS Version (String)
  public final static String SYSTEM_JAVA_VM_VENDOR        = "java.vm.vendor";           // Java Virtual machine vendor (String)
  public final static String SYSTEM_JAVA_RUNTIME_VERSION  = "java.runtime.version";     // Java Runtime version (String)
  
  public final static String SYSTEM_USER_NAME             = "user.name";                // User name (String)
  public final static String SYSTEM_USER_DIR              = "user.dir";                 // System defined path separator (String)
  public final static String SYSTEM_USER_HOME             = "user.home";                // System defined path separator (String)
 
  
  public final static String PROVA_CORE_ROOT_DIR          = "prova.root.dir";           // Abs. root dir of Prova (String)

  public final static String PROVA_CONF_DIR               = "prova.conf.dir";           // Relative dir of config files (String)
  public final static String PROVA_CONF_FILE_PFX          = "prova.conf.file.pfx";      // Config filenames prefix (String)
  public final static String PROVA_CONF_FILE_DEF          = "prova.conf.file.default";  // Filename for default config file (String)
  public final static String PROVA_CONF_FILE_TEST         = "prova.conf.file.test";     // Test config filename post-fix (String) 
  public final static String PROVA_CONF_FILE_USER         = "prova.conf.file.user";     // User provided abs. config file (String)
  public final static String PROVA_CONF_FILE_EXT          = "prova.conf.file.ext";      // Config file extension
  
  public final static String PROVA_LOG_LEVEL              = "prova.log.level";          // Active log level
  public final static String PROVA_LOG_ROOT               = "prova.log.dir.root";       // Rel. dir for log files (String)
  public final static String PROVA_LOG_HISTORY            = "prova.log.dir.history";    // Rel. dir for history log files (String)
  public final static String PROVA_LOG_FILENAME           = "prova.log.filename";       // Log file name pattern
  public final static String PROVA_LOG_EXT_TXT            = "prova.log.ext.txt";        // Log file extension
  public final static String PROVA_LOG_PATTERN_CONSOLE    = "prova.log.pattern.console";// Log pattern on console
  public final static String PROVA_LOG_PATTERN_FILE       = "prova.log.pattern.file";   // Log pattern in log file
}
