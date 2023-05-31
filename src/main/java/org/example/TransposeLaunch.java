package org.example;

import org.kohsuke.args4j.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TransposeLaunch {

    @Option(name = "-a", metaVar = "NumOfSymbols", usage = "Number of max symbols")
    private String num;
    @Option(name = "-o", metaVar = "ofile", usage = "Output file name")
    private String outputFileName;
    @Option(name = "-t", metaVar = "-t", usage = "Cut word if not fits")
    private boolean cut;
    @Option(name = "-r", metaVar = "-r", usage = "Align to right")
    private boolean align;
    @Argument(metaVar = "Input file name")
    private String inputFileName;

    public static void main(String[] args) throws IOException {
        new TransposeLaunch().launch(args);

    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println();
            parser.printUsage(System.err);
        }
        Transpose text = new Transpose(cut, align,num);
        try {
            text.transpose(inputFileName, outputFileName);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }

    }
}