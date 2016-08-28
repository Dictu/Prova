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
package nl.dictu.prova.logging;

/**
 * Defines the available log levels for Prova
 *
 * @author Sjoerd Boerhout
 *
 * Fatal:
 * ------
 * Means that the execution of some task led to an event that will
 * presumably lead the application to abort.
 * Something has definitively gone wrong!
 * <p>
 * Error:
 * ------
 * Means that the execution of some task could not be completed; an
 * email couldn't be sent, a page couldn't be rendered, some data
 * couldn't be stored to a database, something like that.
 * <p>
 * Warning:
 * --------
 * Means that something unexpected happened, but that execution can
 * continue, perhaps in a degraded mode; a configuration file was
 * missing but defaults were used, a price was calculated as
 * negative, so it was clamped to zero, etc.
 * Something is not right, but it hasn't gone properly wrong yet.
 * Warnings are often a sign that there will be an error very soon.
 * <p>
 * Info:
 * -----
 * Means that something normal but significant happened; the system
 * started, the system stopped, the daily inventory update job ran,
 * etc. There shouldn't be a continual torrent of these, otherwise
 * there's just too much to read.
 * <p>
 * Debug:
 * ------
 * Means that something normal and insignificant happened; a new user
 * came to the site, a page was rendered, an order was taken, a price
 * was updated. This is the stuff excluded from info because there
 * would be too much of it.
 * <p>
 * Trace:
 * ------
 * Would be for extremely detailed and potentially high volume logs
 * that you don't typically want enabled even during normal
 * development. Examples include dumping a full object hierarchy,
 * logging some state during every iteration of a large loop, etc.
 */
public enum LogLevel
{
  FATAL(0),
  ERROR(1),
  WARN(2),
  INFO(3),
  DEBUG(4),
  TRACE(5);

  private final int logLevelValue;


  /**
   * Constructor.
   *
   * @param value Value for this log level
   */
  private LogLevel(int value)
  {
    logLevelValue = value;
  }


  /**
   * Find enum by it's name
   *
   * @param name Name for this log level
   * @return
   */
  public static LogLevel lookup(String name)
  {
    name = name.toUpperCase();

    for(LogLevel logLevel : LogLevel.values())
    {
      if(logLevel.name().equalsIgnoreCase(name))
      {
        return logLevel;
      }
    }
    return null;
  }


  /**
   * Get the index for this log level
   *
   * @return LogLevel value
   */
  public int getValue()
  {
    return logLevelValue;
  }


  /**
   * Get the index for this log level
   *
   * @return LogLevel value
   */
  public String getName()
  {
    for(LogLevel logLevel : LogLevel.values())
    {
      if(logLevelValue == logLevel.logLevelValue)
      {
        return logLevel.name();
      }
    }
    return null;
  }


  /**
   * Get the name of this log level
   *
   * @return LogLevel name
   */
  @Override
  public String toString()
  {
    return this.name();
  }

}
