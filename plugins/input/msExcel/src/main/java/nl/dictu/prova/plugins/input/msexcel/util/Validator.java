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
package nl.dictu.prova.plugins.input.msexcel.util;

import nl.dictu.prova.TestType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Sjoerd Boerhout
 */
public class Validator
{

  private final static Logger LOGGER = LogManager.getLogger(Validator.class.
          getName());


  /**
   * Parse the prefix from the given sheet name.
   * No prefix results in an empty string
   *
   * @param sheet
   *
   * @return
   */
  public static TestType getTestType(Sheet sheet)
  {
    return null;
  }


  /**
   * Parse the prefix from the given sheet name.
   * No prefix results in an empty string
   *
   * @param sheet
   *
   * @return
   */
  public static String getSheetPrefix(Sheet sheet)
  {
    return null;
  }

}
