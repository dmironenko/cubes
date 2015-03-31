package com.util;


import com.cubes.Facet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FileUtils {

    private FileUtils() {
        throw new AssertionError("Instantiating utility class");
    }

    /**
     * Reads @{Facet} from input stream.
     */
    public static List<Facet> readFacets(InputStream stream) throws IOException {
        List<Facet> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            for (int i = 0; i < 2; i++) {
                List<String> line1 = new ArrayList<>();
                List<String> line2 = new ArrayList<>();
                List<String> line3 = new ArrayList<>();

                for (int j = 0; j < 5; j++) {
                    String line = reader.readLine();
                    if (line == null) {
                        throw new IllegalStateException("Input file has invalid format");
                    }

                    line1.add(line.substring(0, 5));
                    line2.add(line.substring(5, 10));
                    line3.add(line.substring(10, 15));


                }
                result.addAll(Arrays.asList(new Facet(line1), new Facet(line2), new Facet(line3)));
            }
        }
        return result;
    }
}
