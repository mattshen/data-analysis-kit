package io.github.mattshen.dakit.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GeneratorTest {

    @Test
    public void generate_should_work() throws IOException {
        String outputfile = "./test_data_file.txt";
        Generator.create().setRequiredRecords(1000).setOutputFile("./test_data_file.txt").execute();

        List<String> lines = Files.readAllLines(Paths.get(outputfile));
        Assert.assertEquals(1000, lines.size());
        Files.delete(Paths.get(outputfile));
    }

}