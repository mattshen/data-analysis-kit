package io.github.mattshen.dakit;

import io.github.mattshen.dakit.tasks.Analyzer;
import io.github.mattshen.dakit.tasks.Generator;
import org.junit.Assert;
import org.junit.Test;


public class CliArgumentsTest {


    @Test
    public void execute_help() {
        CliArguments cliArgs = CliArguments.create().parse(new String[]{"-h"});
        Assert.assertTrue(cliArgs.isHelp());
    }

    @Test
    public void no_args() {
        CliArguments cliArgs = CliArguments.create().parse(new String[]{});
        Assert.assertTrue(cliArgs.hasNoArgs());
    }

    @Test
    public void execute_generate() {
        CliArguments cliArgs = CliArguments.create().parse(new String[]{"-t", "generate"});
        Assert.assertTrue(cliArgs.isGenerateTask());
        Assert.assertFalse(cliArgs.isAnalyzeTask());
        Assert.assertEquals(Generator.DEFAULT_OUTPUT_FILE, cliArgs.getOutputFile());
    }

    @Test
    public void execute_analyze() {
        CliArguments cliArgs = CliArguments.create().parse(new String[]{"-t", "analyze"});
        Assert.assertTrue(cliArgs.isAnalyzeTask());
        Assert.assertFalse(cliArgs.isGenerateTask());
        Assert.assertEquals(Analyzer.DEFAULT_INTPUT_FILE, cliArgs.getInputFile());
    }


}