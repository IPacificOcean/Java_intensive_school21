package java.edu.school21.printer.logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validation {
    private String  white_;
    private String  black_;
    private String  pathToImg_;

    public String getWhite_() {
        return white_;
    }

    public String getBlack_() {
        return black_;
    }

    public String getPathToImg_() {
        return pathToImg_;
    }

    public void check(String[] args) {
        if ((args.length >= 3) && args[0].equals(".") && args[1].equals("0")) {
            try {
                white_ = args[0];
                black_ = args[1];
                Path file = Paths.get(args[2]);
                if (!Files.exists(file)) {
                    exit(args[2] + "file does not exist");
                }
                pathToImg_ = args[0];

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            exit("too few arguments or error of input");
        }
    }

    void exit (String error) {
        System.err.println(error);
        System.exit(-1);
    }
}
