package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.JCommander;

@Parameters(separators = "=")
public class ParserCL {

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

    public void commandLineParser (String[] args) {
        JCommander.newBuilder().addObject(this).build().parse(args);
    }
}
