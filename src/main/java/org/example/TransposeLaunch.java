package org.example;

import org.kohsuke.args4j.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.SortedMap;

public class TransposeLaunch {

    @Option(name = "-a", metaVar = "NumOfSymbols", usage = "Number of max symbols")
    private String num;
    @Option(name = "-o", metaVar = "ofile", usage = "Output file name")
    private String outputFileName;
    @Argument(metaVar = "-t", usage = "Cut word if not fits")
    private String cut;
    @Argument(metaVar = "-r", usage = "Align to right")
    private String align;
    @Argument(metaVar = "file", usage = "Input file name")
    private String inputFileName;

    public static void main(String[] args) throws IOException {
        new TransposeLaunch().launch(args);

    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            System.out.println(parser.getArguments());
        } catch (CmdLineException exception) {
            System.err.println(exception.getMessage());
            System.err.println();
            parser.printUsage(System.err);
        } try {
            Transpose t = new Transpose(cut, align, num);
            t.transpose(inputFileName, outputFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}