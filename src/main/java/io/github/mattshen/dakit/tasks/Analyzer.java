package io.github.mattshen.dakit.tasks;


import io.github.mattshen.dakit.datatypes.SummaryStatistics;
import io.github.mattshen.dakit.utils.Console;
import io.github.mattshen.dakit.utils.LineParseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class Analyzer {

    private static Logger LOG = LoggerFactory.getLogger(Analyzer.class);

    public static final String DEFAULT_INTPUT_FILE = "./sorted_data_file.txt";

    private String inputFile = DEFAULT_INTPUT_FILE;

    private SummaryStatistics result;

    private Analyzer() {
    }

    public static Analyzer create() {
        return new Analyzer();
    }

    public Analyzer setInputFile(String inputFile) {
        this.inputFile = inputFile;
        return this;
    }

    public Analyzer execute() {
        try (Stream<String> stream = Files.lines(Paths.get(inputFile))) {
            result = stream
                    .map(LineParseUtils::parse)
                    .filter(Objects::nonNull)
                    .reduce(new SummaryStatistics(), SummaryStatistics::add, (summaryStatistics, summaryStatistics2) -> summaryStatistics)
                    .done();

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return this;
    }


    public void printResults() {
        Console.log(result.toString());
    }

    public SummaryStatistics getResult() {
        return result;
    }

}
