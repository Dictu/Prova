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
package nl.dictu.prova.plugins.input.msexcel.readers.testdata;

import java.io.File;
import java.util.LinkedList;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sjoerd Boerhout
 */
public class SoapTestDataReader extends TestDataReader
{

  private final static Logger LOGGER = LogManager.getLogger(
          SoapTestDataReader.class.getName());


  /**
   * Constructor
   */
  public SoapTestDataReader()
  {
    super(LOGGER);
  }


  @Override
  public LinkedList<String> getDataSetNames(File dataFile)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Properties getVariables(File dataFileName, String dataSetName)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
