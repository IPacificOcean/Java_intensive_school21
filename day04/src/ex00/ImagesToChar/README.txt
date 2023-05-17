rm -rf target

// create directory for *.class and compilation in byte-code
javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java

// run application
java -classpath target edu.school21.printer.app.Program . 0 ../it.bmp