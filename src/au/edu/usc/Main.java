package au.edu.usc;

import java.io.IOException;

/*
AUTHOR:     Harrison Shepherd
Student ID: 1121132
email:      HJS028@student.usc.edu.au
 */

public class Main {
    public static void main(String[] args) {
        // List of input file paths
        String[] inputFiles = {
                "T1_test_cases/input_01.txt",
                "T1_test_cases/input_02.txt",
                "T1_test_cases/input_03.txt",
                "T1_test_cases/input_04.txt",
                "T1_test_cases/input_05.txt",
                "T1_test_cases/input_06.txt",
                };


        for (String inputFile : inputFiles) {
            Inventory inventory = new Inventory();
            try {
                System.out.println('\n'+"Processing input file: " + inputFile);
                CommandReader.readCommands(inputFile, inventory);
                System.out.println("Completed processing for: " + inputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
    } }
} // end of class



