package io.github.mattshen.dakit;


import io.github.mattshen.dakit.tasks.Analyzer;
import io.github.mattshen.dakit.tasks.Generator;
import io.github.mattshen.dakit.utils.Console;

public class CliApplication {

    public static void main(String[] args) {

        CliArguments cliArgs = CliArguments.create().parse(args);

        if (cliArgs.hasNoArgs() || cliArgs.isHelp()) {
            cliArgs.printHelp();
        } else {
            if (cliArgs.isGenerateTask()) {
                Generator.create()
                        .setRequiredRecords(cliArgs.getRequiredRecords())
                        .setOutputFile(cliArgs.getOutputFile())
                        .setParallel(cliArgs.isParallel())
                        .setVerbose(cliArgs.isVerbose())
                        .execute();
            } else if (cliArgs.isAnalyzeTask()) {
                Analyzer.create()
                        .setInputFile(cliArgs.getInputFile())
                        .setOutputFile(cliArgs.getOutputFile())
                        .setDistanceUnit(cliArgs.getDistanceUnit())
                        .setTemperatureUnit(cliArgs.getTemperatureUnit())
                        .setParallel(cliArgs.isParallel())
                        .setVerbose(cliArgs.isVerbose())
                        .execute()
                        .printResults();
            } else {
                Console.log("Error: must specify task");
                Console.log("");
                cliArgs.printHelp();
            }
        }

        System.exit(0);
    }

}
