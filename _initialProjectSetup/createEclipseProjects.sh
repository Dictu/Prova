#!/bin/bash
#
# Create Eclipse projects
#
# Run from dir _initialProjectSetup or change PROVA_ROOT var!
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

echo "---= Start creating Eclipse projects =---"

echo "Root dir: " ${PROVA_ROOT}
echo ""

echo "- Create project for Prova "${CORE}
cd ${PROVA_ROOT}/${CORE}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for input plugin "${MSEXCEL}
cd ${PROVA_ROOT}/${PLUGINS}/${INPUT}/${MSEXCEL}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for output plugin "${APACHESOAP}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${APACHESOAP}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for output plugin "${JDBC}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${JDBC}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for output plugin "${SCRIPTPRINTER}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${SCRIPTPRINTER}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for output plugin "${SELENIUM}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${SELENIUM}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for output plugin "${SHELLCOMMAND}
cd ${PROVA_ROOT}/${PLUGINS}/${OUTPUT}/${SHELLCOMMAND}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for reporting plugin "${SIMPLEREPORT}
cd ${PROVA_ROOT}/${PLUGINS}/${REPORTING}/${SIMPLEREPORT}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for prova runner "${CLI}
cd ${PROVA_ROOT}/${RUNNERS}/${CLI}
pwd
mvn eclipse:eclipse
echo ""

echo "- Create project for prova runner "${GUI}
cd ${PROVA_ROOT}/${RUNNERS}/${GUI}
pwd
mvn eclipse:eclipse
echo ""

echo "---= Finished creating Eclipse projects =---"

exit