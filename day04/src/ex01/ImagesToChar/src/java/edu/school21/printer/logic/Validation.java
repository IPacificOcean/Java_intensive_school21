package edu.school21.printer.logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validation {

    public void check(String[] args, String pathToImg) {
        if (!(args.length >= 2) || (args[0].length() != 1) || (args[1].length() != 1)) {
            exit("too few arguments or error of input");
        }
        Path file = Paths.get(pathToImg);
        if (!Files.exists(file.toAbsolutePath())) {
            exit(pathToImg + " img file does not exist");
        }
    }

    void exit (String error) {
        System.err.println(error);
        System.exit(-1);
    }
}
