package org.giovanna.app;

import java.util.*;

public class Statistics implements AvailableData
{
    // key: length of word
    // value: number of words found with length = key
    private TreeMap<Integer, Integer> lengthsCounter;
    private Scanner scanner;
    private int wordCounter;
    private int totalWordLength;

    public Statistics(Scanner currentScan)
    {
        scanner = currentScan;
        lengthsCounter = new TreeMap<Integer, Integer>();
        wordCounter = 0;
        totalWordLength = 0;
    }

    public Scanner getScanner() {return scanner; };
    public TreeMap<Integer, Integer> getLengthsCounter() { return lengthsCounter; }
    public int getWordCounter() { return wordCounter; }
    public int getTotalWordLength() { return totalWordLength; }

    public void elaborateInputFromScanner()
    {
        if (scanner == null)
            return;

        int currLength = 0;
        int newValue = 0;
        while (scanner.hasNextLine())
        {
            String currentLine = scanner.nextLine();

            // A simple word is intended to be a 'List of consecutive characters found between:
            // spaces or [ ] or { } or ( )
            for (String elem: currentLine.split("[\\Q[]\"'\\E(){} ]+") )
            {
                // Remove any punctuation or special characters from the beginning/ending of current word
                elem = cleanWord(elem);

                // Check after cleaning if there is still a valid word
                if (elem.length() == 0) continue;

                currLength = elem.length();
                if (lengthsCounter.containsKey(currLength))
                {
                    newValue = lengthsCounter.get(currLength) + 1;
                    lengthsCounter.put(currLength, newValue);
                }
                else
                {
                    lengthsCounter.put(currLength, 1);
                }
                // Total length calculated from all valid/recognised words
                totalWordLength += currLength;
            }
        }

        wordCounter = sumIntegerList(lengthsCounter.values());
    }

    // print out the word count
    public void printOutWordCount()
    {
        System.out.println("Word count = " + wordCounter);
    }

    // print out Average word lengths
    public void printOutAverageLength()
    {
        double average;

        // Average word length
        if (wordCounter == 0)
            average = 0.0;
        else
            average = (double)totalWordLength/wordCounter;

        System.out.println("Average word length = " + String.format("%.3f", average));
    }

    // print out Frequencies
    public void printOutFrequencies()
    {
        // Print out the TreeMap Content: key=length, value=frequency
        for (Map.Entry<Integer, Integer> entry : lengthsCounter.entrySet())
            System.out.println("Number of words of length " + entry.getKey() + " is " + entry.getValue());
    }

    // print out the most frequently occurred word length
    public void printOutMostFrequent()
    {
        OptionalInt maxFrequency = lengthsCounter.values().stream().mapToInt(Integer::intValue).max();
        if (!maxFrequency.isPresent())
        {
            System.out.println("No words have been found in the give stream. No frequencies can be calculated");
            return;
        }

        String formattedLengths = "";
        int numOfWords = 0;

        for (Map.Entry<Integer, Integer> entry : lengthsCounter.entrySet())
        {
            System.out.println("Number of words of length " + entry.getKey() + " is " + entry.getValue());
            if (entry.getValue() == maxFrequency.getAsInt())
            {
                formattedLengths += " & " + entry.getKey().toString();
                numOfWords++;
            }
        }


        //Most Frequently occurring word length
        String plural = (numOfWords > 1) ? "lengths of" : "length of";
        System.out.println("The most frequently occurring word length is " + maxFrequency.getAsInt() +
                ", for word " + plural + formattedLengths.substring(2));
    }

    // Return the sum of given list of Integer
    private int sumIntegerList(Collection<Integer> collection)
    {
        if (collection == null)
            return 0;
        else
            return collection.stream().mapToInt(Integer::intValue).sum();
    }

    /* input: String
    |  output: String cleaned by all recognised punctuation characters found
    |          at the beginning and/or end of give string
    |
    | Note: These characters will be considered part of the word:
    |       "#", "$", "%", "&", "@", "~"
    */
    private String cleanWord(String toClean)
    {
        // list of characters will be removed if found at the beginning or end of the given string
        Set<Character> punctuation = new HashSet<>(
                Arrays.asList('!', '\'', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '^', '_', '|')
        );

        StringBuilder sb = new StringBuilder(toClean);

        //Clean the "prefix" if needed
        while(sb.length()>0 && punctuation.contains(sb.charAt(0)))
            sb.deleteCharAt(0);

        //Clean the "suffix" if needed
        while(sb.length()>0 && punctuation.contains(sb.charAt(sb.length() - 1)))
            sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

}
