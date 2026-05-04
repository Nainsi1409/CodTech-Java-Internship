package com.codtech.filehandling;

import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String fileName = "data.txt";

        try {

            // CREATE & WRITE
            FileWriter writer = new FileWriter(fileName);
            System.out.println("Enter text to write into file:");
            String input = sc.nextLine();
            writer.write(input + "\n");
            writer.close();
            System.out.println("Data written successfully.\n");

            // READ
            System.out.println("Reading file content:");
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

            // APPEND (MODIFY)
            FileWriter appendWriter = new FileWriter(fileName, true);
            System.out.println("\nEnter text to append:");
            String appendText = sc.nextLine();
            appendWriter.write(appendText + "\n");
            appendWriter.close();

            // READ AGAIN
            System.out.println("\nUpdated file content:");
            BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
            while ((line = reader2.readLine()) != null) {
                System.out.println(line);
            }
            reader2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}