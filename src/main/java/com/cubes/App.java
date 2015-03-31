package com.cubes;

import com.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws IOException {

        Cube cube;

        try (InputStream stream = getInputStream(args)) {
            cube = new Cube(FileUtils.readFacets(stream));
        }

        Solution s = new Solution(cube);

        for (Cube c : s.solve())
            System.out.println(c);

    }

    private static InputStream getInputStream(String[] args) throws IOException {
        InputStream stream;

        if (args == null || args.length == 0) {
            stream = App.class.getClassLoader().getResourceAsStream("blue_cube.txt");
        } else {
            // Assuming args[0] contains path to file with facet
            stream = Files.newInputStream(Paths.get(args[0]));
        }
        return stream;
    }
}
