package io.github.mattshen.dakit.tasks;


import io.github.mattshen.dakit.datatypes.SummaryStatistics;
import io.github.mattshen.dakit.utils.Console;
import io.github.mattshen.dakit.utils.LineParseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Analyzer {

    private static Logger LOG = LoggerFactory.getLogger(Analyzer.class);

    public static final String DEFAULT_INTPUT_FILE = "./sorted_data_file.txt";

    private String inputFile = DEFAULT_INTPUT_FILE;

    private boolean parallel = false;

    private boolean verbose = false;

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

    public Analyzer setParallel(boolean parallel) {
        this.parallel = parallel;
        return this;
    }

    public Analyzer setVerbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    public Analyzer execute() {

        Console.log(MessageFormat.format("Analyzing file {0} ...", inputFile));

        if (verbose) {
            Console.log(MessageFormat.format("Parallel Mode: {0}", this.parallel));
        }

        try (Stream<String> stream = parallel ?
                Files.lines(Paths.get(inputFile)).parallel() : Files.lines(Paths.get(inputFile))) {

            SummaryStatistics stats = new SummaryStatistics();
            stream.map(LineParseUtils::parse)
                    .filter(Objects::nonNull)
                    .forEach(observation -> {
                        synchronized (Analyzer.class) {
                            stats.add(observation);
                        }
                    });

            result = stats.done();

            if (parallel) { //distance result in parallel is incorrect
                result.setTraveledDistance(-1L);
            }

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
