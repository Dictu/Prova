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
package nl.dictu.prova;


/**
 * All configuration properties are defined in this class with a public final
 * constant string for an actual up to date overview of all configuration
 * properties available in Prova. 
 * Plug-ins extend this class with their own property names.
 * Rules:
 * - Property constants are defined in upper case using _ as separator
 * - Property names are defined in upper case using . as separator
 * - Property names are formatted like this:
 *   'prova' 'module' 'category' 'keyname'
 *
 * @author Sjoerd Boerhout
 */
public class Property
{
  //                         Name in Prova              Name in property file      Description & type
  public final static String PROVA_CORE_ROOT_DIR        = "prova.core.root.dir";   // Root dir of Prova (String)
}
