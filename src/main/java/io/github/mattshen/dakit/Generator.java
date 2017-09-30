package io.github.mattshen.dakit;


import io.github.mattshen.dakit.utils.DataUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;

public class Generator {

    public static void main(String[] args) {

        try (FileWriter fileWriter = new FileWriter("./dummy_data_small.txt");
             PrintWriter printWriter = new PrintWriter(fileWriter))
        {
            IntStream.range(0, 5_000)
                    .parallel()
                    .mapToObj(i -> DataUtils.generateDummyGPSRecord().toString())
                    .forEach(line -> printWriter.println(line));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
