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

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Provides the functions to load plug-in from provided JAR-files
 *
 * @author Sjoerd Boerhout
 */
public class PluginLoader extends URLClassLoader
{
  private final static URL URLS[] =
  {
  };


  /**
   * Custom constructor with an empty collection of Uris
   */
  public PluginLoader()
  {
    super(URLS);
  }


  /**
   * Constructor with url(s)
   *
   * @param urls
   */
  public PluginLoader(URL[] urls)
  {
    super(urls);
  }


  /**
   * Search in the given directory for files with the given file extension and
   * add these files to the class path.
   *
   * @param directory
   * @param fileExt
   *
   * @throws FileNotFoundException
   */
  public void addFiles(File directory, String fileExt) throws
          FileNotFoundException
  {

  }


  /**
   * Add the given file to the class path
   *
   * @param fileName
   */
  public void addFile(File fileName)
  {

  }


  /**
   * Get an instance of the given {@link className} and as a {@link classType}
   *
   * @param <T>
   * @param className
   * @param classType
   *
   * @return
   *
   * @throws Exception
   */
  public <T> T getInstanceOf(final String className, final Class<T> classType)
          throws Exception
  {
    return null;
  }

}
