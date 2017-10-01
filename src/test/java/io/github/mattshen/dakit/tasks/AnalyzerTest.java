package io.github.mattshen.dakit.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class AnalyzerTest {

    @Test
    public void testS() {
        System.out.println(1L * 40_000_000 * 500_000_000);
    }

    @Test
    public void analyze_should_work() throws IOException {
        String outputfile = "./test_data_file.txt";
        Generator.create().setRequiredRecords(1000).setOutputFile("./test_data_file.txt").execute();

        Analyzer analyzer = Analyzer.create().setInputFile(outputfile).execute();

        Assert.assertNotNull(analyzer.getResult());
        Assert.assertEquals(1000, analyzer.getResult().getCount());
        Files.delete(Paths.get(outputfile));
    }

}