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
package nl.dictu.prova.plugins.output.selenium;

import java.io.File;
import java.security.InvalidParameterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import nl.dictu.prova.Config;
import nl.dictu.prova.TestRunner;
import nl.dictu.prova.TestType;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestCase;
import nl.dictu.prova.plugins.output.OutputPlugin;
import nl.dictu.prova.plugins.output.selenium.actions.CaptureScreen;
import nl.dictu.prova.plugins.output.selenium.actions.Click;
import nl.dictu.prova.plugins.output.selenium.actions.Select;
import nl.dictu.prova.plugins.output.selenium.actions.SelectDropDown;
import nl.dictu.prova.plugins.output.selenium.actions.SendKeys;
import nl.dictu.prova.plugins.output.selenium.actions.SetText;
import nl.dictu.prova.plugins.output.selenium.actions.Sleep;
import nl.dictu.prova.plugins.output.selenium.actions.SwitchFrame;
import nl.dictu.prova.plugins.output.selenium.actions.ValidateElement;
import nl.dictu.prova.plugins.output.selenium.actions.ValidateText;
import nl.dictu.prova.plugins.output.selenium.actions.SwitchScreen;
import nl.dictu.prova.plugins.output.selenium.actions.Navigate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Sjoerd Boerhout
 */
public class Selenium implements OutputPlugin
{

  protected final static Logger LOGGER = LogManager.getLogger(Selenium.class.
          getName());

  private TestRunner testRunner;
  private WebDriver webdriver;
  private int maxTimeOut;
  private int maxRetries;
  private TestCase testCase;
  
  public final static String ACTION_CAPTURESCREEN   = "CAPTURESCREEN";
  public final static String ACTION_CLICK           = "CLICK";
  //public final static String ACTION_DOWNLOADFILE    = "DOWNLOADFILE";
  public final static String ACTION_NAVIGATE	      = "NAVIGATE";
  public final static String ACTION_SELECT          = "SELECT";
  public final static String ACTION_SELECTDROPDOWN  = "SELECTDROPDOWN";
  public final static String ACTION_SENDKEYS        = "SENDKEYS";
  public final static String ACTION_SETTEXT         = "SETTEXT";
  public final static String ACTION_SLEEP           = "SLEEP";
  public final static String ACTION_SWITCHFRAME     = "SWITCHFRAME";
  public final static String ACTION_SWITCHSCREEN    = "SWITCHSCREEN";
  public final static String ACTION_VALIDATEELEMENT = "VALIDATEELEMENT";
  public final static String ACTION_VALIDATETEXT    = "VALIDATETEXT";

  @Override
  public void init(TestRunner testRunner) throws Exception
  {
    this.testRunner = testRunner;
  }

  @Override
  public void shutDown()
  {
    try
    {
      webdriver.close();
    }
    catch(NullPointerException eX)
    {
      // Ignore. Browser already closed
    }
    catch(Exception eX)
    {
      LOGGER.error("Exception during shutDown: '{}'", eX.getMessage());
    }
  }

  @Override
  public String getName()
  {
    return "Selenium Webdriver";
  }

  @Override
  public TestType[] getTestType()
  {
    TestType[] testType = {TestType.WEB};
    return testType;
  }

  @Override
  public void setUp(TestCase tc)
  {
    this.testCase = tc;
  }

  @Override
  public void tearDown(TestCase tc)
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /*
     * Factory functions that returns an instance of the requested action.
     * All actions extend nl.dictu.prova.framework.TestAction.
   */
  @Override
  public TestAction getTestAction(String name) throws InvalidParameterException
  {
    LOGGER.trace("Request to produce webaction '{}'", () -> name);

    switch (name.toUpperCase())
    {
      case ACTION_CAPTURESCREEN:      return new CaptureScreen(this);
      case ACTION_CLICK:              return new Click(this);
      //case ACTION_DOWNLOADFILE:       return new DownloadFile(this);
      case ACTION_NAVIGATE:           return new Navigate(this);
      case ACTION_SELECT:             return new Select(this);
      case ACTION_SELECTDROPDOWN:     return new SelectDropDown(this);
      case ACTION_SENDKEYS:           return new SendKeys(this);
      case ACTION_SETTEXT:            return new SetText(this);
      case ACTION_SLEEP:              return new Sleep(this);
      case ACTION_SWITCHFRAME:        return new SwitchFrame(this);
      case ACTION_SWITCHSCREEN:       return new SwitchScreen(this);
      case ACTION_VALIDATEELEMENT:    return new ValidateElement(this);
      case ACTION_VALIDATETEXT:       return new ValidateText(this);
    }
    
    throw new InvalidParameterException("Unknown action '" + name + "' requested");
  }  

  public WebElement findElement(String xPath)
  {
    WebElement element = null;
    
    if(webdriver == null){
      startWebdriver();
    }

    int count = 0;

    while (true)
    {
      try
      {
        LOGGER.trace("Find element: '{}'", xPath);

        // Wait until element visible
        Integer timeOut = (int) (maxTimeOut / 1000);
        LOGGER.trace("Wait time for element: '{}'", timeOut);
        WebDriverWait wait = new WebDriverWait(webdriver, timeOut);
        //wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        LOGGER.trace("Found element is clickable. Let's try!");

        element = webdriver.findElement(By.xpath(xPath));

        LOGGER.trace("Found element: '{}'", (element != null ? "YES" : "NO"));

        return element;
      }
      catch (StaleElementReferenceException eX)
      {
        if (++count > maxRetries)
        {
          throw eX;
        }
        LOGGER.trace("Element '{}' is no longer attached to the DOM. Try again. ({})", xPath, count);
      }
      catch (NoSuchElementException eX)
      {
        if (++count > maxRetries)
        {
          throw eX;
        }
        LOGGER.trace("Element '{}' not found. Try again. ({})", xPath, count);
      }
      catch (TimeoutException eX)
      {
        if (++count > maxRetries)
        {
          throw eX;
        }
        LOGGER.trace("TimeOut Exception on '{}'. Try again. ({})", xPath, count);
      }
      catch (Exception eX)
      {
        if (++count > maxRetries)
        {
          LOGGER.error("Exception while searching element '{}' retry count: '{}', Type: '{}' : '{}'",
                  xPath,
                  count,
                  eX.getClass().getSimpleName(),
                  eX.getMessage());

          throw eX;
        }
        else
        {
          LOGGER.trace("Exception while searching element '{}' retry count: '{}', Type: '{}' : '{}'",
                  xPath,
                  count,
                  eX.getClass().getName(),
                  eX.getMessage());
        }
      }
    }
  }
  
  public TestRunner getTestRunner()
  {
    LOGGER.trace("Request for testRunner");
    
    return testRunner;
  }

  public WebDriver getWebdriver()
  {
    if(webdriver == null){
      startWebdriver();
    }
    LOGGER.trace("Request for webdriver '{}'", () -> webdriver.toString());
    
    return webdriver;
  }

  public int getMaxTimeOut()
  {
    LOGGER.trace("Request for max timeout '{}'", () -> maxTimeOut);
    
    return maxTimeOut;
  }

  public void setMaxTimeOut(int maxTimeOut)
  {
    LOGGER.trace("Set value of max timeout to '{}'", () -> maxTimeOut);
    
    this.maxTimeOut = maxTimeOut;
  }

  public int getMaxRetries()
  {
    LOGGER.trace("Request for max retries '{}'", () -> maxRetries);
    
    return maxRetries;
  }

  public void setMaxRetries(int maxRetries)
  {
    LOGGER.trace("Set value of max retries to '{}'", () -> maxRetries);
    
    this.maxRetries = maxRetries;
  }
  
  public void startWebdriver()
  {
    try
    {
      String browserType = testRunner.getProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_TYPE);
      
      LOGGER.debug("Setup: Test Case ID '{}' with browser '{}'", () -> testCase.getId(), () -> browserType);
      
      if(browserType.equalsIgnoreCase("FireFox"))
      {
        if(testRunner.hasProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_PROFILE))
        {
          ProfilesIni profile = new ProfilesIni();
          FirefoxProfile ffProfile = profile.getProfile(testRunner.getProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_PROFILE));
          LOGGER.trace("Try to load webdriver 'FireFox' with profile '{}'", testRunner.getProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_PROFILE));
          webdriver = new FirefoxDriver(ffProfile);
        }
        else
        {
          LOGGER.trace("Try to load webdriver 'FireFox'");
          webdriver = new FirefoxDriver();
        }
      }
      else if(browserType.equalsIgnoreCase("Chrome"))
      {
        String chromePath = testRunner.getProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_PATH_CHROME);
        
        LOGGER.trace("Try to load webdriver 'Chrome' ({})", chromePath);
        System.setProperty("webdriver.chrome.driver", chromePath);  

        webdriver = new ChromeDriver();
      }
      else if(browserType.equalsIgnoreCase("InternetExplorer") || browserType.equalsIgnoreCase("IE"))
      {
        String iePath = testRunner.getProperty(Config.PROVA_DIR) +
                        File.separator +
                        testRunner.getProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_PATH_IE);
        
        LOGGER.trace("Try to locate webdriver 'IE': ({})", iePath);
        if(!new File(iePath).isFile())
        {
          iePath = testRunner.getProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_PATH_IE);
          LOGGER.trace("Try to locate webdriver 'IE': ({})", iePath);
          
          if(!new File(iePath).isFile())
            throw new Exception("No valid path to IE driver. (" + iePath + ")");
        }
        
        LOGGER.trace("Try to load webdriver 'InternetExplorer' ({})", iePath);
        System.setProperty("webdriver.ie.driver", iePath);        
        webdriver = new InternetExplorerDriver();
      }
      else if(browserType.equalsIgnoreCase("Safari"))
      {
        LOGGER.trace("Try to load webdriver 'Safari'");
        webdriver = new SafariDriver();
      }
      else
      {
        throw new Exception("Unsupported browser '" + browserType + "' requested.");
      }
      
      LOGGER.trace("Selenium is ready to start the test!");
    }
    catch(Exception eX)
    {
      LOGGER.trace(eX);
      webdriver.close();
    }
  }

}