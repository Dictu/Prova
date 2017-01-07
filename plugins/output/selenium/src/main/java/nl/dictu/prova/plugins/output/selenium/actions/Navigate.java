/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package nl.dictu.prova.plugins.output.selenium.actions;

import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestStatus;
import nl.dictu.prova.plugins.output.selenium.Selenium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Coos van der Galiën
 */
public class Navigate extends TestAction
{
  
  private final static Logger LOGGER = LogManager.getLogger(Click.class.
          getName());
  
  private static final String ATTR_HYPERLINK = "HYPERLINK";
  
  Selenium selenium;
  private Url url = null;

  /**
   * Constructor
   */
  public Navigate(Selenium selenium)
  {
    super(LOGGER);
    
    this.selenium = selenium;
    
    try
    {
      url = new Url();
    }
    catch (Exception ex)
    {      
      LOGGER.error("Exception while creating new Navigate TestAction! " + ex.getMessage());
    }
  }

  /**
   * Execute this action
   */  
  @Override
  public TestStatus execute()
  {
    URL qualifiedUrl = null;
	  LOGGER.debug("Checking url for malformations");
	  
	  try 
    {
      qualifiedUrl = new URL(url.getValue());
                
      LOGGER.debug("Url not malformed, navigating to " + qualifiedUrl.getPath());
	  
      selenium.getWebdriver().navigate().to(qualifiedUrl);  
      return TestStatus.PASSED;
	  } 
    catch (MalformedURLException e) 
    {
	  	LOGGER.debug("Provided URL is malformed.");
      e.printStackTrace();
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
    return ("'" + this.getClass().getSimpleName().toUpperCase()  + "': " + url.getValue() + "'");
  }

  
  /**
  * Check if all requirements are met to execute this action
  */
  @Override
  public boolean isValid()
  {
    if(selenium == null) return false;
    if(!url.isValid()) return false;
    
    return true;
  }
  
  
  /**
	 * Set attribute <key> with <value> - Unknown attributes are ignored -
	 * Invalid values result in an exception
	 * 
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	@Override
	public void setAttribute(String key, String value)
  {
    try
    {
      LOGGER.trace("Request to set '{}' to '{}'", () -> key, () -> value);

      switch (key.toUpperCase().trim()) {
      case ATTR_PARAMETER:
      case ATTR_HYPERLINK:
        url.setValue(value);
        break;
      }
    }
    catch (Exception ex)
    {
      LOGGER.error("Exception while setting attribute to TestAction : " + ex.getMessage());
    }
	}
  
}
