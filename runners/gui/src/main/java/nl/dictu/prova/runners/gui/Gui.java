package nl.dictu.prova.runners.gui;

import nl.dictu.prova.runners.ProvaRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class Gui extends ProvaRunner
{

  private static Logger LOGGER = LogManager.getLogger(
          Gui.class.getName());


  /**
   * Program entry point
   *
   * @param args
   */
  public static void main(String[] args)
  {
    System.out.println("Hello World! I'm the Gui for Prova");
  }


  /**
   * Private constructor.
   * Set the logger to the logger of the implementing class
   *
   * @param newLogger
   *
   * @throws NullPointerException
   */
  private Gui(Logger newLogger) throws NullPointerException
  {
    super(LOGGER);
  }

}
