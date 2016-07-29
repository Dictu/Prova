#!/bin/bash
#
# Prova project setup
#
#

CURRDIR=`pwd`
PROVA_ROOT=${CURRDIR}/..
PLUGINS='plugins'
INPUT='input'
OUTPUT='output'
REPORTING='reporting'
RUNNERS='runners'

CORE='core'
INSTALLER='installer'
JDBC='jdbc'
MSEXCEL='msExcel'
SCRIPTPRINTER='scriptPrinter'
SELENIUM='selenium'
SHELLCOMMAND='shellCommand'
APACHESOAP='apacheSoap'
SIMPLEREPORT='SimpleReport'
CLI='cli'
GUI='gui'

clear

echo "---= Start creating Prova projects =---"

echo "Install dir: " ${PROVA_ROOT}
echo ""

echo "- Create directory structure"
mkdir ${PROVA_ROOT}/${PLUGINS}
mkdir ${PROVA_ROOT}/${PLUGINS}/${INPUT}
mkdir ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}
mkdir ${PROVA_ROOT}/${PLUGINS}/${REPORTING}
mkdir ${PROVA_ROOT}/${RUNNERS}


echo "- Create Core project"
cd ${PROVA_ROOT}
mvn archetype:generate -DgroupId=nl.dictu.prova -DartifactId=${CORE} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


echo "- Create "${INSTALLER}
cd ${PROVA_ROOT}
mvn archetype:generate -DgroupId=nl.dictu.prova -DartifactId=${INSTALLER} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


echo "- Create input plugin "${MSEXCEL}
cd ${PROVA_ROOT}/${PLUGINS}/${INPUT}
mvn archetype:generate -DgroupId=nl.dictu.prova.plugins.input -DartifactId=${MSEXCEL} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false



echo "- Create output plugin "${APACHESOAP}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}
mvn archetype:generate -DgroupId=nl.dictu.prova.plugins.output -DartifactId=${APACHESOAP} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


echo "- Create output plugin "${JDBC}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}
mvn archetype:generate -DgroupId=nl.dictu.prova.plugins.output -DartifactId=${JDBC} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

echo "- Create output plugin "${SCRIPTPRINTER}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}
mvn archetype:generate -DgroupId=nl.dictu.prova.plugins.output -DartifactId=${SCRIPTPRINTER} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

echo "- Create output plugin "${SELENIUM}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}
mvn archetype:generate -DgroupId=nl.dictu.prova.plugins.output -DartifactId=${SELENIUM} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

echo "- Create output plugin "${SHELLCOMMAND}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}
mvn archetype:generate -DgroupId=nl.dictu.prova.plugins.output -DartifactId=${SHELLCOMMAND} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


echo "- Create reporting plugin "${SIMPLEREPORT}
cd ${PROVA_ROOT}/${PLUGINS}/${REPORTING}
mvn archetype:generate -DgroupId=nl.dictu.prova.plugins.reporting -DartifactId=${SIMPLEREPORT} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


echo "- Create prova runner "${CLI}
cd ${PROVA_ROOT}/${RUNNERS}
mvn archetype:generate -DgroupId=nl.dictu.prova.runners -DartifactId=${CLI} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

echo "- Create prova runner "${GUI}
cd ${PROVA_ROOT}/${RUNNERS}
mvn archetype:generate -DgroupId=nl.dictu.prova.runners -DartifactId=${GUI} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


echo "- Copy all new POM-files"
cp ${CURRDIR}/pom_parent.xml           /${PROVA_ROOT}/pom.xml
cp ${CURRDIR}/pom_${CORE}.xml          /${PROVA_ROOT}/${CORE}/pom.xml

cp ${CURRDIR}/pom_${INSTALLER}.xml     /${PROVA_ROOT}/${INSTALLER}/pom.xml

cp ${CURRDIR}/pom_${MSEXCEL}.xml       /${PROVA_ROOT}/${PLUGINS}/${INPUT}/${MSEXCEL}/pom.xml

cp ${CURRDIR}/pom_${APACHESOAP}.xml    /${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${APACHESOAP}/pom.xml
cp ${CURRDIR}/pom_${JDBC}.xml          /${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${JDBC}/pom.xml
cp ${CURRDIR}/pom_${SCRIPTPRINTER}.xml /${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${SCRIPTPRINTER}/pom.xml
cp ${CURRDIR}/pom_${SELENIUM}.xml      /${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${SELENIUM}/pom.xml
cp ${CURRDIR}/pom_${SHELLCOMMAND}.xml  /${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${SHELLCOMMAND}/pom.xml

cp ${CURRDIR}/pom_${SIMPLEREPORT}.xml  /${PROVA_ROOT}/${PLUGINS}/${REPORTING}/${SIMPLEREPORT}/pom.xml
cp ${CURRDIR}/pom_${CLI}.xml           /${PROVA_ROOT}/${RUNNERS}/${CLI}/pom.xml
cp ${CURRDIR}/pom_${GUI}.xml           /${PROVA_ROOT}/${RUNNERS}/${GUI}/pom.xml

cp ${CURRDIR}/README.md                /${PROVA_ROOT}/README.md


echo "- Build all projects"
cd ${PROVA_ROOT}
mvn clean install

echo "---= Finished Prova project setup =---"

exit