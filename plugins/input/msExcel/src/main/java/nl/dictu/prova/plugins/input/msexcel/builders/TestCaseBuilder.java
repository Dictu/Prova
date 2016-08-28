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

import java.io.File;
import java.util.LinkedList;
import nl.dictu.prova.TestRunner;
import nl.dictu.prova.framework.TestAction;
import nl.dictu.prova.framework.TestCase;
import nl.dictu.prova.plugins.input.msexcel.readers.testcase.TestCaseReader;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Sjoerd Boerhout
 */
public class TestCaseBuilder
{

  private TestCaseReader testCaseReader;


  public TestCaseBuilder(TestRunner testRunner)
  {
  }


  public File buildTestCase(File fileName)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }


  public TestCase parseSheet(Sheet sheet, TestCase testCase)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }


  public Sheet parseHeaders(Sheet sheet)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }


  public LinkedList<TestAction> parseTestActions() throws Exception
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
