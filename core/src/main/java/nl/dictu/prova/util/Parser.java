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

/**
 * Provides global available parser functions
 *
 * @author Sjoerd Boerhout
 */
public class Parser
{

  /**
   * Try to parse the given string to an integer
   *
   * @param value
   *
   * @return
   */
  public static Integer ParseNumber(String value) throws NumberFormatException
  {
    return -1;
  }


  /**
   * Try to parse the given string to a floating point value
   *
   * @param value
   *
   * @return
   */
  public static Integer ParseNumberWithDecimal(String value) throws
          NumberFormatException
  {
    return -1;
  }


  /**
   * Try to parse the given string to a floating point value with respect to the
   * given range
   *
   * @param value
   * @param minValue
   * @param maxValue
   *
   * @return
   */
  public static Integer ParseNumberWithDecimal(String value, double minValue,
                                               double maxValue) throws
          NumberFormatException
  {
    return -1;
  }

}
