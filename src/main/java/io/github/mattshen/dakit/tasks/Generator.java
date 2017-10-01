package io.github.mattshen.dakit.tasks;


import io.github.mattshen.dakit.utils.Console;
import io.github.mattshen.dakit.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.stream.IntStream;

public class Generator {

    private static Logger LOG = LoggerFactory.getLogger(Generator.class);

    public static final Integer DEFAULT_REQUIRED_RECORDS = 50000;

    public static final String DEFAULT_OUTPUT_FILE = "./data_file.txt";

    private Integer requiredRecords = DEFAULT_REQUIRED_RECORDS;

    private String outputFile = DEFAULT_OUTPUT_FILE;

    private Generator() {}

    public static Generator create() {
        return new Generator();
    }

    public Generator setRequiredRecords(Integer requiredRecords) {
        this.requiredRecords = requiredRecords;
        return this;
    }

    public Generator setOutputFile(String outputFile) {
        this.outputFile = outputFile;
        return this;
    }


    public void execute() {

        Console.log(MessageFormat.format("Generating {0} records into file {1}", this.requiredRecords, outputFile));

        try (FileWriter fileWriter = new FileWriter(outputFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            IntStream.range(0, this.requiredRecords)
                    .parallel()
                    .mapToObj(i -> DataUtils.generateDummyObservation().toString())
                    .forEach(line -> printWriter.println(line));

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

    }

}
