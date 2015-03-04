package com;

import com.cubes.Side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        InputStream stream = App.class.getClassLoader().getResourceAsStream("blue_cube.txt");

        List<Side> result = readSides(stream);

        for (Side s : result) {
            System.out.println(s);
        }

    }

    private static List<Side> readSides(InputStream stream) throws IOException {
        List<Side> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            for (int i = 0; i < 2; i++) {
                List<String> s1 = new ArrayList<>();
                List<String> s2 = new ArrayList<>();
                List<String> s3 = new ArrayList<>();

                for (int j = 0; j < 5; j++) {
                    String line = reader.readLine();
                    if (line == null) {
                        throw new IllegalStateException("Input file has invalid format");
                    }

                    s1.add(line.substring(0, 5));
                    s2.add(line.substring(5, 10));
                    s3.add(line.substring(10, 15));


                }
                result.addAll(Arrays.asList(new Side(s1), new Side(s2), new Side(s3)));
            }
        }
        return result;
    }
}
