package edu.school21.printer.logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validation {

    public void check(String[] args, String pathToImg) {
        if (!(args.length >= 2)) {
            exit("too few arguments or error of input");
        }
        if (!args[0].startsWith("--white=") || !args[1].startsWith("--black=")) {
            exit("illegal arguments");
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
