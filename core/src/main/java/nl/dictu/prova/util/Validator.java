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
package nl.dictu.prova.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides global available validation functions
 *
 * @author Sjoerd Boerhout
 */
public class Validator
{

  private final static Logger LOGGER = LogManager.getLogger(Validator.class.
          getName());


  /**
   * Validates if the given filename:
   * - Is available on the file system
   * - Is a file
   * - Is readable
   *
   * @param fileName
   *
   * @return
   */
  public static boolean isValidFileName(String fileName)
  {
    return false;
  }


  /**
   * Validates if the given string is a valid Xpath
   *
   * @param xPath
   *
   * @return
   */
  public static boolean isValidXpath(String xPath)
  {
    return false;
  }

}
