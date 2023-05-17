package org.example;


import java.io.*;


public class Transpose extends File {

    private String[][] matrix;
    private String[][] newMatrix;
    private int matRaws = 0;
    private int matCol = 0;

    public Transpose(String pathname) {
        super(pathname);
    }

    private void rowsAndColumns() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String b;
            while ((b = reader.readLine()) != null) {
                matRaws += 1;
                int t = b.split(" +").length;
                matCol = Math.max(t, matCol);
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    private void fillMatrix() throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader("input.txt"))) {
            matrix = new String[matRaws][matCol];
            String l = r.readLine();
            while (l != null) {
                for (int i = 0; i < Math.max(matCol, matRaws); i++) {
                    for (int j = 0; j < l.split(" +").length; j++) {
                        matrix[i][j] = l.split(" +")[j];
                    }
                    l = r.readLine();
                    if (l == null) break;
                }
            }
        }
    }

    public void transpose() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            rowsAndColumns();
            fillMatrix();
            newMatrix = new String[matCol][matRaws];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == null || matrix[i][j].matches(" +")) newMatrix[j][i] = "";
                    else newMatrix[j][i] = matrix[i][j];
                }
            }
            for (String[] strings : newMatrix) {
                for (String string : strings) {
                    if(!string.equals("")) {
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

