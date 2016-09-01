/*
 *  
 *  Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 *  the European Commission - subsequent versions of the EUPL (the "Licence");
 *  You may not use this work except in compliance with the Licence.
 *  You may obtain a copy of the Licence at:
 *  
 *  http://ec.europa.eu/idabc/eupl
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the Licence is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the Licence for the specific language governing permissions and
 *  limitations under the Licence.
 *  
 *  Date:      29-08-2016
 *  Author(s): Coos van der Galiën
 *  
 */
package nl.dictu.prova.plugins.output.selenium;

import java.io.File;
import static java.io.File.pathSeparator;
import java.net.URLDecoder;
import nl.dictu.prova.Config;
import nl.dictu.prova.Prova;
import nl.dictu.prova.TestRunner;
import nl.dictu.prova.TestType;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestCase;
import nl.dictu.prova.plugins.output.selenium.actions.Navigate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Coos van der Galiën
 */
public class SeleniumTest
{
  
  public SeleniumTest()
  {
  }
  
  @BeforeClass
  public static void setUpClass()
  {
  }
  
  @AfterClass
  public static void tearDownClass()
  {
  }
  
  @Before
  public void setUp()
  {
  }
  
  @After
  public void tearDown()
  {
  }

  /**
   * Test of init method, of class Selenium.
   */
  @Test
  public void testInit() throws Exception
  {
    System.out.println("init");
    TestRunner testRunner = new Prova();
    Selenium instance = new Selenium();
    instance.init(testRunner);
    assertEquals(instance.getTestRunner(), testRunner);
  }

  /**
   * Test of shutDown method, of class Selenium.
   */
  @Test
  public void testShutDown() throws Exception
  {
    try
    {
      System.out.println("shutDown");
      Selenium instance = new Selenium();
      Prova prova = new Prova();
      prova.init();
      prova.setProperty(pathSeparator, pathSeparator);
      TestRunner testRunner = prova;
      testRunner.setProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_TYPE, "FireFox");
      instance.init(testRunner);
      instance.startWebdriver();
      instance.shutDown();
    }
    catch (Exception ex)
    {    
      System.out.println(ex.getMessage());
      ex.printStackTrace();
      fail("Exception during testShutDown()");
    }
  }

  /**
   * Test of getName method, of class Selenium.
   */
  @Test
  public void testGetName()
  {
    System.out.println("getName");
    Selenium instance = new Selenium();
    String expResult = "Selenium Webdriver";
    String result = instance.getName();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTestType method, of class Selenium.
   */
  @Test
  public void testGetTestType()
  {
    System.out.println("getTestType");
    Selenium instance = new Selenium();
    TestType[] expResult = {TestType.WEB};
    TestType[] result = instance.getTestType();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of setUp method, of class Selenium.
   */
  @Test
  public void testSetUp()
  {
    try
    {
      System.out.println("setUp");
      TestCase tc = new TestCase("test");
      Selenium instance = new Selenium();
      instance.setUp(tc);
    }
    catch(Exception ex)
    {
      fail("Failure when setting a new TestCase to Selenium");
      ex.printStackTrace();
    }
  }

  /**
   * Test of getTestAction method, of class Selenium.
   */
  @Test
  public void testGetTestAction()
  {
    System.out.println("getTestAction");
    Selenium instance = new Selenium();
    TestAction expResult = new Navigate(instance);
    TestAction result = instance.getTestAction("NAVIGATE");
    assertTrue(expResult.toString().equals(result.toString()));
  }

  /**
   * Test of findElement method, of class Selenium.
   */
  @Test
  public void testFindElement()
  {
    try
    {
      System.out.println("findElement");
      String xPath = "//h1[@id='title']";
      
      Prova prova = new Prova();
      prova.init();
      TestRunner testRunner = prova;
      testRunner.setProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_TYPE, "FireFox");
      Selenium instance = new Selenium();
      instance.init(testRunner);
      
      String sRootPath = SeleniumTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
      sRootPath = URLDecoder.decode(sRootPath, "utf-8");
      sRootPath = sRootPath.substring(1,sRootPath.lastIndexOf('/'));
      File fRootPath = new File(pathSeparator + pathSeparator + sRootPath)
                          .getParentFile()
                          .getParentFile()
                          .getParentFile()
                          .getParentFile()
                          .getParentFile()
                          .getAbsoluteFile();
      String file = fRootPath.getAbsolutePath() + File.separator + "_doc" + File.separator  + "testSite" + File.separator + "index.html";
      file = file.substring(file.lastIndexOf(";")+1);
      
      instance.getWebdriver().navigate().to(file);
      WebElement result = instance.findElement(xPath);
      String foundText = result.getText();
      assertEquals("Unit Test site for Prova", foundText);
      instance.getWebdriver().close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      fail("Exception while running testFindElement");
    }
  }

  /**
   * Test of getTestRunner method, of class Selenium.
   */
  @Test
  public void testGetTestRunner()
  {
    System.out.println("getTestRunner");
    Selenium instance = new Selenium();
    TestRunner expResult = new Prova();
    try
    {
      instance.init(expResult);
    }
    catch (Exception ex)
    {
      fail("Exception during testGetTestRunner() : " + ex.getMessage());
    }
    TestRunner result = instance.getTestRunner();
    assertEquals(expResult, result);
  }

  /**
   * Test of getMaxTimeOut and setMaxTimeOut method, of class Selenium.
   */
  @Test
  public void testGetMaxTimeOut()
  {
    System.out.println("getMaxTimeOut");
    Selenium instance = new Selenium();
    int expResult = 0;
    instance.setMaxTimeOut(expResult);
    int result = instance.getMaxTimeOut();
    assertEquals(expResult, result);
  }

  /**
   * Test of getMaxRetries and setMaxRetries method, of class Selenium.
   */
  @Test
  public void testGetMaxRetries()
  {
    System.out.println("getMaxRetries");
    Selenium instance = new Selenium();
    int expResult = 0;
    instance.setMaxRetries(expResult);
    int result = instance.getMaxTimeOut();
    assertEquals(expResult, result);
  }
  
}
