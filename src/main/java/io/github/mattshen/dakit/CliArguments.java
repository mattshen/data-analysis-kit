package io.github.mattshen.dakit;

import io.github.mattshen.dakit.datatypes.DistanceUnit;
import io.github.mattshen.dakit.datatypes.TemperatureUnit;
import io.github.mattshen.dakit.tasks.Analyzer;
import io.github.mattshen.dakit.tasks.Generator;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;

public class CliArguments {

    private static final Logger LOG = LoggerFactory.getLogger(CliArguments.class);

    private final CliArguments instance;

    private OptionParser parser = null;

    private OptionSet options = null;

    private CliArguments() {
        super();
        instance = this;
    }

    public static CliArguments create() {
        return new CliArguments();
    }

    public CliArguments parse(String[] args) {

        parser = new OptionParser();

        parser.acceptsAll(asList("h", "help"), "show help").forHelp();

        parser.acceptsAll(asList("t", "task"), "task name. supports 'analyze' or 'generate'")
                .withRequiredArg();

        parser.acceptsAll(asList("n", "required-records"), "generate task only").withOptionalArg()
                .ofType(Integer.class).defaultsTo(Generator.DEFAULT_REQUIRED_RECORDS);

        parser.acceptsAll(asList("o", "output"), "output file").withRequiredArg()
                .describedAs("path").ofType(File.class);

        parser.acceptsAll(asList("i", "input"), "input file. analyze task only").withRequiredArg()
                .describedAs("path").ofType(File.class);

        parser.acceptsAll(asList("p", "parallel"), "generate and analyze concurrently");

        parser.acceptsAll(asList("v", "verbose"), "print more details in the running");

        parser.acceptsAll(asList("unit-distance"), "normalized distance unit. supports km, m, mile. analyze task only")
                .withRequiredArg().defaultsTo("m");
        parser.acceptsAll(asList("unit-temperature"), "normalized temperature unit. supports celsius, fahrenheit, kelvin. analyze task only")
                .withRequiredArg().defaultsTo("celsius");


        instance.options = parser.parse(args);

        return instance;
    }

    public void printHelp() {
        try {
            parser.printHelpOn(System.out);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public boolean isHelp() {
        return options.has("h") || options.has("help");
    }

    public boolean hasNoArgs() {
        return options.nonOptionArguments().isEmpty() && !options.hasOptions();
    }

    public boolean isGenerateTask() {
        return options.hasArgument("task") && String.valueOf(options.valueOf("task")).equals("generate");
    }

    public boolean isAnalyzeTask() {
        return options.hasArgument("task") && String.valueOf(options.valueOf("task")).equals("analyze");
    }

    public boolean isParallel() {
        return options.has("parallel");
    }

    public boolean isVerbose() {
        return options.has("verbose");
    }

    public Integer getRequiredRecords() {
        if (options.has("required-records") ) {
            return (Integer)options.valueOf("required-records");
        } else {
            return Generator.DEFAULT_REQUIRED_RECORDS;
        }
    }

    public String getOutputFile() {
        if (isGenerateTask()) {
            if (options.hasArgument("output")) {
                return String.valueOf(options.valueOf("output"));
            } else {
                return Generator.DEFAULT_OUTPUT_FILE;
            }
        } else if (isAnalyzeTask()) {
            if (options.hasArgument("output")) {
                return String.valueOf(options.valueOf("output"));
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public String getInputFile() {
        if (isAnalyzeTask()) {
            if (options.hasArgument("input")) {
                return String.valueOf(options.valueOf("input"));
            } else {
                return Analyzer.DEFAULT_INPUT_FILE;
            }
        } else {
            return null;
        }
    }

    public DistanceUnit getDistanceUnit() {
        if (options.hasArgument("unit-distance")) {
            return DistanceUnit.getValue((String)options.valueOf("unit-distance"));
        } else {
            return DistanceUnit.M;
        }
    }

    public TemperatureUnit getTemperatureUnit() {
        if (options.hasArgument("unit-temperature")) {
            return TemperatureUnit.getValue((String)options.valueOf("unit-temperature"));
        } else {
            return TemperatureUnit.CELSIUS;
        }
    }

}
