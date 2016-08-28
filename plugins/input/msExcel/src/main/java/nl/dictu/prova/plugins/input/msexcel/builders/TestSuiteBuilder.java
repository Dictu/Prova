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
 * Date: 23-08-2016
 * Author(s): Sjoerd Boerhout
 * <p>
 */
package nl.dictu.prova.plugins.input.msexcel.builders;

import nl.dictu.prova.TestRunner;
import nl.dictu.prova.framework.TestSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sjoerd Boerhout
 */
public class TestSuiteBuilder
{

  private final static Logger LOGGER = LogManager.getLogger(
          TestSuiteBuilder.class.getName());


  public TestSuiteBuilder(TestRunner testRunner)
  {

  }

  public String[] buildTestSuite(String testSuiteRoot, String[] filters)
  {
        throw new UnsupportedOperationException("Not supported yet.");
  }

  private String[] addTestCases(TestSuite testSuite, String[] filters)
  {
        throw new UnsupportedOperationException("Not supported yet.");
  }
}
