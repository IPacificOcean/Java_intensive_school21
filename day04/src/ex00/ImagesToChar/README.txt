rm -rf target

1) // create directory for *.class and compilation in byte-code
 Command: javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java

  // universal variant (https://ru.hexlet.io/courses/java_101/lessons/java_packages/theory_unit#:~:text=%D0%94%D0%BB%D1%8F%20%D1%82%D0%BE%D0%B3%D0%BE%2C%20%D1%87%D1%82%D0%BE%D0%B1%D1%8B,%D0%B4%D0%B8%D1%80%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%B8%D1%8E%20out.)
  // Для того, чтобы компилятор прошелся по всем директориям в папке, например, src и скомпилировал
     там все исходники — нужно ввести команду так: javac src/**/*.java (пройтись по всем под папкам
     в папке src и скомпилировать там все файлы *.java). Правда они будут лежать радом с исходниками.
     Лучше сразу разложить класс-файлы в отдельную папку: javac src/**/*.java -d out. Все скомпилированные
     классы будут разложены по пакетам так же как они располагались в папке src.

     А если эти классы в папке src зависят друг от друга, то команда будет выглядеть так:
     javac src/**/*.java -d out -sourcepath src. В результате чего компилятор скомпилирует
     все *.java-файлы в *.class-файлы, свяжет их по зависимостям и уложит в директорию out.

  Command: javac src/**/*.java -d target -sourcepath src

  // Некоторые терминалы не понимают звездочки **. Например, команда javac src/**/*.java может просто выдавать ошибку отсутствия файлов.
  "Понимание звездочек" нужно включать! В bash это можно включить командой: shopt -s globstar.

2) // run application
  Command: java -classpath target edu.school21.printer.app.Program _ # ../it.bmp