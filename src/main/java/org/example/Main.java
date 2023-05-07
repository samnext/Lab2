package org.example;

import java.io.*;

import org.kohsuke.args4j.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Program Files (x86)\\Kotlin&Java Projects\\Lab2\\input.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            String greetings = "Hello Poppy!";
            fileOutputStream.write(greetings.getBytes());
        } catch (IOException exception) {
            throw new FileNotFoundException();
        }
    }
}