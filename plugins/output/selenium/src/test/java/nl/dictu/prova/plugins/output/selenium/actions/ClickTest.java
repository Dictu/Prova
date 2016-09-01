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
 * @author Coos van der Galiën
 */
public class ClickTest
{
  Selenium selenium;
  String file;
  
  public ClickTest()
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
   * Test of execute method, of class Click.
   */
  @Test
  public void testExecute()
  {
    System.out.println("execute");
    Click instance = new Click(selenium);
    TestStatus expResult = TestStatus.PASSED;
    instance.setAttribute("XPATH", "//div[@id='add']/button");
    instance.setAttribute("RIGHTCLICK", "false");
    instance.setAttribute("NUMBEROFCLICKS", "1");
    instance.setAttribute("WAITUNTILPAGELOADED", "true");
    TestStatus result = instance.execute();
    assertEquals(expResult, result);
  }
  
}
