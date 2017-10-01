package io.github.mattshen.dakit.tasks;


import io.github.mattshen.dakit.datatypes.DistanceUnit;
import io.github.mattshen.dakit.datatypes.SummaryStatistics;
import io.github.mattshen.dakit.datatypes.TemperatureUnit;
import io.github.mattshen.dakit.utils.Console;
import io.github.mattshen.dakit.utils.DataUtils;
import io.github.mattshen.dakit.utils.LineParseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.stream.Stream;

public class Analyzer {

    private static final Logger LOG = LoggerFactory.getLogger(Analyzer.class);

    public static final String DEFAULT_INPUT_FILE = "./sorted_data_file.txt";

    private String inputFile = DEFAULT_INPUT_FILE;

    private String outputFile = null;

    private boolean parallel = false;

    private boolean verbose = false;

    private TemperatureUnit temperatureUnit;
    private DistanceUnit distanceUnit;

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

    public Analyzer setOutputFile(String outputFile) {
        this.outputFile = outputFile;
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

    public Analyzer setDistanceUnit(DistanceUnit distanceUnit) {
        this.distanceUnit = distanceUnit;
        return this;
    }

    public Analyzer setTemperatureUnit(TemperatureUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
        return this;
    }

    public Analyzer execute() {

        Console.log(MessageFormat.format("Analyzing file {0} ...", inputFile));

        if (outputFile != null) {
            Console.log(MessageFormat.format("Normalized file saved to {0}", outputFile));
        }

        if (verbose) {
            Console.log(MessageFormat.format("Parallel Mode: {0}", this.parallel));
        }

        try (Stream<String> stream = parallel ?
                Files.lines(Paths.get(inputFile)).parallel() : Files.lines(Paths.get(inputFile));
             FileWriter fileWriter = outputFile == null? null : new FileWriter(outputFile);
             PrintWriter printWriter = fileWriter == null? null : new PrintWriter(fileWriter)
        ) {

            SummaryStatistics stats = new SummaryStatistics();
            stream.map(LineParseUtils::parse)
                    .filter(Objects::nonNull)
                    .forEach(observation -> {

                        synchronized (Analyzer.class) {
                            stats.add(observation);
                        }

                        if (printWriter != null) {
                            printWriter.println(DataUtils.normalize(observation, distanceUnit, temperatureUnit));
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
        Console.log("");
        Console.log("============== Summary ==============");
        Console.log(result.toString());
        Console.log("");
    }

    public SummaryStatistics getResult() {
        return result;
    }

}
