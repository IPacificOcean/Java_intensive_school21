package edu.school21.printer.logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validation {

    public void check(String[] args) {
        if (!(args.length >= 3) || (args[0].length() != 1) || (args[1].length() != 1)) {
            exit("too few arguments or error of input");
        }
        Path file = Paths.get(args[2]);
        if (!Files.exists(file.toAbsolutePath())) {
            exit(args[2] + " file does not exist");
        }
    }

    void exit (String error) {
        System.err.println(error);
        System.exit(-1);
    }
}
