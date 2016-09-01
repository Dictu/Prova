/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package nl.dictu.prova.plugins.output.selenium.actions;

import java.util.Set;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestStatus;
import nl.dictu.prova.plugins.output.selenium.Selenium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;

/**
 *
 * @author Coos van der Galiën
 */
public class SwitchScreen extends TestAction
{

  private final static Logger LOGGER = LogManager.getLogger(Click.class.
          getName());
  
  Selenium selenium;

  /**
   * Constructor
   */
  public SwitchScreen(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
  }

  /**
   * Execute this action
   */  
  @Override
  public TestStatus execute()
  {
    if(!isValid())
    {
      LOGGER.error("Action is not validated!");
      return TestStatus.FAILED;
    }
    
	  try
	  {
		  Set<String> windowHandles = selenium.getWebdriver().getWindowHandles();
		  String currentHandle = selenium.getWebdriver().getWindowHandle();
		  
		  if(windowHandles.isEmpty()){
			  LOGGER.debug("No window handles available.");
			  throw new Exception("No window handles available.");
		  }
		  
		  if(windowHandles.size() == 1){
			  LOGGER.debug("No second screen available to switch to.");
			  throw new Exception("No second screen available to switch to.");
		  }
		  
		  for(String handle : windowHandles){
			  if(!currentHandle.equals(handle)){
				  LOGGER.trace("Switching to screen: " + handle);
				  selenium.getWebdriver().switchTo().window(handle);
				  break;
			  }
		  }
      return TestStatus.PASSED;
	  }
	  catch(NoSuchWindowException eX)
	  {
		  LOGGER.debug("Exception while switching screens: No such window!");
		  eX.printStackTrace();
      return TestStatus.FAILED;
	  }
	  catch(Exception eX)
	  {
		  LOGGER.debug("Exception while switching screens");
		  eX.printStackTrace();
      return TestStatus.FAILED;
	  }
  }

  
  /**
   * Return a string representation of the objects content
   * 
   * @return 
   */
  @Override
  public String toString()
  {
    return ("'" + this.getClass().getSimpleName().toUpperCase() + "'");  
  }
  
  
  /**
   * Check if all requirements are met to execute this action
   */
  @Override
  public boolean isValid()
  {
    if (selenium == null)			return false;

		return true;
  }
  
}
