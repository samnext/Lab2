package org.example;


import java.io.*;
import java.util.Scanner;


public class Transpose {

    private String[][] matrix;
    private int raws = 0;
    private int column = 0;

    private String num;
    private boolean t;//cut if not fits
    private boolean r;//align to right
    private String inputName;
    private String outputName;

    public Transpose(boolean t, boolean r, String num) {
        this.num = num;
        this.t = t;
        this.r = r;
    }

    private void rowsAndColumnsFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputName))) {
            String b;
            while ((b = reader.readLine()) != null) {
                raws += 1;
                int t = b.split(" +").length;
                column = Math.max(t, column);
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    private void rowsAndColumnsCmd() {
        System.out.println("Input your text here:");
        BufferedReader br = null;


    }

    private void fillMatrixFile() throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(inputName))) {
            matrix = new String[raws][column];
            String l = r.readLine();
            while (l != null) {
                for (int i = 0; i < Math.max(column, raws); i++) {
                    System.arraycopy(l.split(" +"), 0, matrix[i], 0, l.split(" +").length);
                    l = r.readLine();
                    if (l == null) break;
                }
            }
        }
    }

    private void fullMatrixCmd(Scanner scan) {
        matrix = new String[raws][column];
        while (scan.hasNext()) {
            String[] a = scan.nextLine().split(" +");
            for (int i = 0; i < Math.max(column, raws); i++) {
                System.arraycopy(a, 0, matrix[i], 0, a.length);
            }
        }
    }

    public void transpose(String inputName, String outputName) throws IOException {
        this.inputName = inputName;
        this.outputName = outputName;
        if (inputName == null) {
            rowsAndColumnsCmd();
        }
        if (outputName == null) { //cmd output
            rowsAndColumnsFile();
            fillMatrixFile();
            String[][] newMatrix = new String[column][raws];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == null || matrix[i][j].matches(" +")) newMatrix[j][i] = "";
                    else newMatrix[j][i] = matrix[i][j];
                }
            }
            for (String[] strings : newMatrix) {
                for (String string : strings) {
                    if (!string.equals("")) {
                        if (num != null) {
                            StringBuilder temp = new StringBuilder(string);
                            if ((r || t))
                                while (temp.length() < Integer.parseInt(num)) {
                                    if (r) {
                                        temp.insert(0, " ");
                                    } else {
                                        temp.append(" ");
                                    }
                                }
                            if (t) {
                                System.out.print(temp.substring(0, Integer.parseInt(num)) + " ");
                            }
                        } else System.out.print(string + " ");
                    }
                }
                System.out.println();;
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {
                rowsAndColumnsFile();
                fillMatrixFile();
                String[][] newMatrix = new String[column][raws];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        if (matrix[i][j] == null || matrix[i][j].matches(" +")) newMatrix[j][i] = "";
                        else newMatrix[j][i] = matrix[i][j];
                    }
                }
                for (String[] strings : newMatrix) {
                    for (String string : strings) {
                        if (!string.equals("")) {
                            if (num != null) {
                                StringBuilder temp = new StringBuilder(string);
                                if ((r || t))
                                    while (temp.length() < Integer.parseInt(num)) {
                                        if (r) {
                                            temp.insert(0, " ");
                                        } else {
                                            temp.append(" ");
                                        }
                                    }
                                if (t) {
                                    writer.write(temp.substring(0, Integer.parseInt(num)) + " ");
                                }
                            } else writer.write(string + " ");
                        }
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                throw new FileNotFoundException();

            }
        }
    }
}

