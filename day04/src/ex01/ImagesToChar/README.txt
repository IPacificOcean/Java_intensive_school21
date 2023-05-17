
  rm -rf target

  1) // create directory for *.class and compilation in byte-code
   Command: javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java

    // universal variant
    Command: javac src/**/*.java -d target -sourcepath src

  2) // copy directory resource to target
    Command: cp -r src/resources target

  3) // create an archive jar
    Command: jar -cfm target/printer-img.jar src/manifest.txt -C target .

  4) // run application
    Command: java -jar target/printer-img.jar _ #