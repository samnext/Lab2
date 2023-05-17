package org.example;

import org.kohsuke.args4j.*;

import java.io.IOException;

public class TransposeLaunch {

    @Option(name = "-a",metaVar = "NumOfSymbols",usage = "Number of max symbols")
    private String num;

    @Option(name = "-o",metaVar = "OutputName",usage = "Output file name")
    private String outputFileName;

    @Argument(required = true,metaVar = "DoCut",usage = "Cut word if not fits")
    private String cut;

    public static void main(String[] args) throws IOException {
        Transpose a = new Transpose("input.txt");
        a.transposes();

    }
    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException exception) {
            System.err.println(exception.getMessage());
            System.err.println();
            parser.printUsage(System.err);
        }


    }
}