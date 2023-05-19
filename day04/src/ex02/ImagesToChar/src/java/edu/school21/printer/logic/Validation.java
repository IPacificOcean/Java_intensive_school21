package edu.school21.printer.logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.JCommander;
@Parameters(separators = "=")
public class Validation {

    @Parameter(names = {"--white"})
    private String  white_;
    @Parameter(names = {"--black"})
    private String  black_;

    public String getWhite_() {
        return white_;
    }

    public String getBlack_() {
        return black_;
    }

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

    public void commandLineParser (String[] args) {
        JCommander.newBuilder().addObject(this).build().parse(args);
    }

    void exit (String error) {
        System.err.println(error);
        System.exit(-1);
    }
}
