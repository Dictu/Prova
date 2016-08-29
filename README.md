Prova is a java-based modular keyword driven framework able to combine different kind of tests in a single test case.
The project was started in an attempt to create a user friendly frond-end for tests scripts for Oracle ATS that allows us to separate test flow and data and doesn't require any technical knowledge about a tool. 
Now it's a standalone framework able to read test from different sources and execute tests in different types of test tools.

# Plugin structure
All input, output and reporting goes through plugins for maximum flexibility. Prova connects all the plugins and directs the data from input to output without exact knowledge about the test type and specific action. 

# Available input plugins
- MS Excel (using Apache POI)

# Available output plugins
## WEB tests
- Selenium: Test web pages with the well known Selenium web driver.

## Shell commands
- planned: Execute commands on the OS command line.

## Database tests
- Under development: Validate db scheme's and execute queries.

## SOAP tests
- Under development: Send and receive SOAP messages.

## JSON tests
- planned: Send and receive JSON messages.

## Script Printer
- planned: Print all tests and test data as a manual for manually testing

# Authors and Contributors
Prova started as an internal company project when no suitable tools were found for our needs. While developing the proof of concept other parties also showed interest in the project and we decided to release Prova for the public as an open source tool.
Prova was started by Sjoerd Boerhout (@sjoerdboerhout) & Robert Bralts (@bralts). Other contributors of the first version are Hielke de Haan (@hylkdh) and Coos van der Galiën (@coos88).

# License and usage
Prova is licensed with the EU Public license. This means everyone is free to use Prova for their own purpose and to contribute to it's development with new plug-ins or features.
             
# Required software to run Prova
- Java JRE 1.8.x or newer
- Depending on the active modules:
  - MS Excel
  - Different kinds of browsers

# Required software for developers 
- Maven 3.x
- Git client
- Java IDE of your choice (Eclipse, Netbeans, ...)
- JDK 1.8.x or newer

# How to use Prova?
- See our [Wiki](https://github.com/Dictu/Prova/wiki)

# Demo
- Available soon
