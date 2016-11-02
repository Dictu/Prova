Prova is a java-based modular keyword driven framework able to combine different kind of tests in a single test script.
The project was started in an attempt to create a user friendly frond-end for tests scripts for Oracle ATS that allows us to separate test flow and data and doesn't require programming skills or technical knowledge of a tool. 
Now it's a standalone framework able to read test from different sources and execute tests in different types of test tools.

# History
Prova version 1 was developed as an internal project. Version 2 will be developed as an open source project here on Github. We will start from scratch and only parts of the original code are re-used.
If you are interested in verion 1 please contact Robert Bralts and request a compiled version of version 1.

# Prova architecture
See https://github.com/Dictu/Prova/issues/1

# Plugin structure
All input, output and reporting goes through plugins for maximum flexibility. Prova connects all the plugins and directs the data from input to output without exact knowledge about the test type and specific action. 

# Input plugins
- MS Excel (using Apache POI)

# Output plugins
## WEB tests
- Selenium: Test web pages with the well known Selenium web driver.

## Shell commands
- Execute commands on the OS command line.

## Image comparation
- Compare two images and check if they are (more or less) the same (Usefull to verify if a webpage is displayed correctly

## Database tests
- JDBC: Validate db scheme's and execute queries.

## SOAP tests
- Apache SOAP: Send and receive SOAP messages.

## JSON tests
- Send and receive JSON messages.

## Script Printer
- Print all tests and test data to execute tests manually

## (S)FTP
- Download and upload files with the (S)FTP-protocol

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
