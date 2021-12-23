package org.giovanna.test;

import org.giovanna.app.Statistics;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TestStatistics {
    private File fileName = new File("./src/main/resources/basic_example.txt");
    private Scanner myScan;

    @Before
    public void setUp() {
        try {
            myScan = new Scanner(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStatisticsInit()
    {
        Statistics results = new Statistics(myScan);

        assertEquals(results.getScanner(), myScan);
        assertEquals(results.getWordCounter(), 0);
        assertEquals(results.getTotalWordLength(), 0);
        assertEquals(results.getLengthsCounter().keySet().size(), 0);
        assertEquals(results.getLengthsCounter().values().size(), 0);
    }

    @Test
    public void testElaborateInputFromScanner()
    {
        Statistics results = new Statistics(myScan);
        results.elaborateInputFromScanner();

        assertEquals(results.getWordCounter(), 9);
        assertEquals(results.getTotalWordLength(), 41);
        assertEquals(results.getLengthsCounter().size(), 7);

        assertEquals(results.getLengthsCounter().get(10).intValue(), 1);
        assertEquals(results.getLengthsCounter().get(4).intValue(), 2);
    }

}