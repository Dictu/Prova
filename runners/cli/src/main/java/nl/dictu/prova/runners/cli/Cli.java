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
package nl.dictu.prova.runners.cli;

import java.util.Properties;
import nl.dictu.prova.runners.ProvaRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sjoerd Boerhout
 */
public class Cli extends ProvaRunner
{

  private final static Logger LOGGER = LogManager.getLogger(
          Cli.class.getName());


  /**
   * Program entry point
   *
   * @param args
   */
  public static void main(String[] args)
  {
    System.out.println("Hello world! I am Prova, the testing framework!");
    System.out.println("Active log level: " + LOGGER.getLevel().name());

    LOGGER.fatal("FATAL");
    LOGGER.error("ERROR");
    LOGGER.warn("WARN");
    LOGGER.info("INFO");
    LOGGER.debug("DEBUG");
    LOGGER.trace("TRACE");
  }


  /**
   * Private constructor
   * <p>
   */
  private Cli()
  {
    super(LOGGER);
  }


  /**
   * Parse command line arguments and options
   *
   * @param args
   *
   * @throws Exception
   */
  public void init(String[] args) throws Exception
  {
    super.init();

    LOGGER.debug("init with args " + args.length);
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
   *
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
   *
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
