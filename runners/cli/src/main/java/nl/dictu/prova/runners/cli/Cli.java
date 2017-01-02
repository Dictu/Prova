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

package nl.dictu.prova.runners.cli;

import java.util.ArrayList;
import java.util.Properties;
import nl.dictu.prova.runners.ProvaRunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * PROVA-10: Command Line Interface
 * Initialize and run Prova from a command line interface
 * 
 * @author Sjoerd Boerhout
 */
public class Cli extends ProvaRunner
{

  private final static Logger LOGGER = LogManager.getLogger(Cli.class.getName());

  private CommandLineParser cmdLineParser;
  private CommandLine cmdLine;
  private Options options;
  private Properties cliProperties;
  private ArrayList<String> cliArguments;
  
  
  /**
   * Program entry point.
   * 
   * - Initialize the Cli-class
   * - Parse command line  
   *
   * @param args
   */
  public static void main(String[] args)
  {
    int statusCode = 0;

    try
    {
      LOGGER.trace("Start of program execution");

      System.out.println("Hello world, I am Prova the testing framework!");
      System.out.println("Active log level: " + LOGGER.getLevel().name());

      Cli cli = new Cli();

      // Init sequence and handle command line options
      cli.init(args);

      // Start Prova execution and wait until it is finished
      cli.setUp();
      
      cli.execute();
    }
    catch(Exception eX)
    {
      LOGGER.fatal("A fatal exception occured. Program execution is aborted.", eX);
      statusCode = -1;
    }
    finally
    {
      System.out.println("Bye bye cruel world...");
    }

    System.exit(statusCode);
  }


  /**
   * Private constructor
   * <p>
   */
  private Cli()
  {
    // Call the parent constructor
    super(LOGGER);

    LOGGER.trace("Constructor - Init local variables");

    // Create new instances for all command line handling tools
    cmdLineParser = new DefaultParser();
    options = new Options();
    cmdLine = null;
    cliProperties = new Properties();
    cliArguments = new ArrayList<String>();
  }


  /**
   * Parse command line arguments and options
   *
   * @param args
   * @throws Exception
   */
  public void init(String[] args) throws Exception
  {
    LOGGER.debug("Init - Parse supplied {} arguments: '{}'", () -> args.length, () -> args);
    
    super.init();
  }


  /**
   * Setup this ProvaRunner
   *
   * @throws Exception
   */
  public void setUp() throws Exception
  {

  }


  /**
   * Execute the testrunner
   *
   * @throws Exception
   */
  public void execute() throws Exception
  {

  }


  /**
   * Configure all accepted command line parameters
   * <p>
   */
  private void configureCliArguments()
  {
  }


  /**
   * Parse all arguments supplied on the command line by the user
   *
   * @param args
   * @return
   */
  private Properties parseCliArguments(String[] args)
  {
    return null;
  }


  /**
   * Parse all options supplied on the command line by the user
   *
   * @param args
   * @return
   */
  private Properties parseCliOptions(String[] args)
  {
    return null;
  }


  /**
   * Print the cli help
   */
  private void printHelp()
  {

  }

}
