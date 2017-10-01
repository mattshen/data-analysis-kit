package io.github.mattshen.dakit;

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

    private static Logger LOG = LoggerFactory.getLogger(CliArguments.class);

    private CliArguments instance;

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

        parser.acceptsAll(asList("t", "task"), "task name")
                .withRequiredArg();

        parser.acceptsAll(asList("n", "required-records")).withRequiredArg()
                .ofType(Integer.class).defaultsTo(Generator.DEFAULT_REQUIRED_RECORDS);

        parser.acceptsAll(asList("o", "output"), "output file").withRequiredArg()
                .describedAs("path").ofType(File.class);

        parser.acceptsAll(asList("i", "input"), "input file").withRequiredArg()
                .describedAs("path").ofType(File.class);


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

    public Integer getRequiredRecords() {
        if (options.hasArgument("required-records") ) {
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
        } else {
            return null;
        }

    }

    public String getInputFile() {
        if (isAnalyzeTask()) {
            if (options.hasArgument("input")) {
                return String.valueOf(options.valueOf("input"));
            } else {
                return Analyzer.DEFAULT_INTPUT_FILE;
            }
        } else {
            return null;
        }
    }

}
