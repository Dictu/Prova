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
package nl.dictu.prova.plugins.input.msexcel.readers.testcase;

import java.util.LinkedList;
import java.util.Properties;
import nl.dictu.prova.framework.TestAction;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Sjoerd Boerhout
 */
public abstract class TestCaseReader
{

  /**
   * Retrieves all the test actions from the given sheet
   *
   * @param sheet
   *
   * @return
   *
   * @throws java.lang.Exception
   */
  public abstract LinkedList<TestAction> parseActionSheet(Sheet sheet)
          throws Exception;


  /**
   * Parse package
   *
   * @param sheet
   *
   * @return
   *
   * @throws java.lang.Exception
   */
  public Properties parseHeaders(Sheet sheet)
          throws Exception
  {
    return null;
  }

  /**
   * Parse package
   *
   * @param sheet
   *
   * @return
   *
   * @throws java.lang.Exception
   */
  public LinkedList<TestAction> parsePackage(Sheet sheet)
          throws Exception
  {
    return null;
  }

}
