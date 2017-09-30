package io.github.mattshen.dakit;


import io.github.mattshen.dakit.datatypes.SummaryStatistics;
import io.github.mattshen.dakit.utils.LineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class Analyzer {

    private static Logger LOG = LoggerFactory.getLogger(Analyzer.class);

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get("./sorted_dummy_data_small.txt"))) {

            SummaryStatistics stats = stream
                    .map(LineParser::parse)
                    .filter(Objects::nonNull)
                    .reduce(new SummaryStatistics(), SummaryStatistics::add, (summaryStatistics, summaryStatistics2) -> summaryStatistics)
                    .done();

            System.out.println("\nfinal statistics: \n" + stats);


        } catch (IOException e) {
            LOG.error("Errors", e);
        }
    }

}
