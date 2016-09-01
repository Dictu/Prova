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
package nl.dictu.prova.plugins.output.selenium.actions;

import java.io.File;
import static java.io.File.pathSeparator;
import java.net.URLDecoder;
import nl.dictu.prova.Config;
import nl.dictu.prova.Prova;
import nl.dictu.prova.TestRunner;
import nl.dictu.prova.framework.TestStatus;
import nl.dictu.prova.plugins.output.selenium.Selenium;
import nl.dictu.prova.plugins.output.selenium.SeleniumTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Coos van der GaliÃ«n
 */
public class ValidateTextTest
{
  Selenium selenium;
  String file;
  
  public ValidateTextTest()
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
  public void setUp() throws Exception
  {
    Prova prova = new Prova();
    prova.init();
    TestRunner testRunner = prova;
    testRunner.setProperty(Config.PROVA_PLUGINS_OUT_WEB_BROWSER_TYPE, "FireFox");
    selenium = new Selenium();
    selenium.init(testRunner);

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
    file = fRootPath.getAbsolutePath() + File.separator + "_doc" + File.separator  + "testSite" + File.separator + "index.html";
    file = file.substring(file.lastIndexOf(";")+1);
    selenium.getWebdriver().navigate().to(file);
  }
  
  @After
  public void tearDown()
  {
    selenium.getWebdriver().close();
    selenium.shutDown();
  }

  /**
   * Test of execute method, of class ValidateText.
   */
  @Test
  public void testExecute()
  {
    System.out.println("execute");
    SendKeys instance1 = new SendKeys(selenium);
    instance1.setAttribute("KEYS", "Nachos Supremos");
    instance1.setAttribute("XPATH", "//input[@id='input']");
    instance1.execute();
    
    Click instance2 = new Click(selenium);
    instance2.setAttribute("XPATH", "//div[@id='add']/button");
    instance2.setAttribute("RIGHTCLICK", "false");
    instance2.setAttribute("NUMBEROFCLICKS", "1");
    instance2.setAttribute("WAITUNTILPAGELOADED", "true");
    instance2.execute();
    
    ValidateText instance3 = new ValidateText(selenium);
    TestStatus expResult = TestStatus.PASSED;
    instance3.setAttribute("XPATH", "//ul/li[4]");
    instance3.setAttribute("VALUE", "Nachos Supremos");
    TestStatus result = instance3.execute();
    assertEquals(expResult, result);
  }
  
}
