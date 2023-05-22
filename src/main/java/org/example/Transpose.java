package org.example;


import java.io.*;
import java.util.Scanner;


public class Transpose {

    private String[][] matrix;
    private int raws = 0;
    private int column = 0;

    private String num;
    private String t;//cut if not fits
    private String r;//align to right
    private String inputName;
    private String outputName;

    public Transpose(String t, String r, String num) {
        this.num = num;
        this.t = t;
        this.r = r;
    }


    private void rowsAndColumns() throws IOException {
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

    private void fillMatrix() throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(inputName))) {
            matrix = new String[raws][column];
            String l = r.readLine();
            while (l != null) {
                for (int i = 0; i < Math.max(column, raws); i++) {
                    for (int j = 0; j < l.split(" +").length; j++) {
                        matrix[i][j] = l.split(" +")[j];
                    }
                    l = r.readLine();
                    if (l == null) break;
                }
            }
        }
    }

    public void transpose(String inputName, String outputName) throws IOException {
        this.inputName = inputName;
        this.outputName = outputName;
        if (inputName == null) {
            System.out.println("Input your text here:");
            Scanner t = new Scanner(System.in);
            File temp = new File("tempInput.txt");
            try (BufferedWriter tem = new BufferedWriter(new FileWriter(temp))) {
                while (t.hasNext()) {
                    tem.write(t.nextLine());
                }
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {
            rowsAndColumns();
            fillMatrix();
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
                        writer.write(string + " ");
                    }

                }
                writer.write("\n");
            }

        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}

