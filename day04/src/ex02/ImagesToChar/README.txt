
  rm -rf target
1)
// create libs directory
   Command: mkdir lib target

2)
// downloads libs
   Command: curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
   Command: curl -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar

3)
// extract libs var.1
  Commands: cd target && \
            jar -xf ../lib/JCDP-4.0.2.jar && \
            jar -xf ../lib/jcommander-1.82.jar && \
            rm -rf META-INF && \
            cd ..

// extract libs var.2
  Commands: unzip -d target lib/JCDP-4.0.2.jar && \
            YES | unzip -d target lib/jcommander-1.82.jar && \
            rm -rf target/META-INF

4)
// create directory for *.class and compilation in byte-code
   Command: javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java

// universal variant
    Command: javac src/**/*.java -d target -classpath target -sourcepath src

5)
// copy directory resource to target
    Command: cp -r src/resources target

6)
// create an archive jar
    Command: jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target .

7)
// run application
    Command: java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
