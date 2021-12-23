package org.giovanna.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    // arg[0] is supposed to be the path of file to be parsed
    public static void main(String[] args) {

        // Use default file if nothing is get from user
        String inputFile = (args.length > 0) ? args[0] : "./src/main/resources/basic_example.txt";

        try
        {
            File file = new File(inputFile);
            Scanner myScan = new Scanner(file);

            Statistics results = new Statistics(myScan);
            results.elaborateInputFromScanner();

            results.printOutWordCount();
            results.printOutAverageLength();
            results.printOutFrequencies();
            results.printOutMostFrequent();

            myScan.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }

    }

}