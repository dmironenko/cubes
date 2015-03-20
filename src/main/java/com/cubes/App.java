package com.cubes;

import com.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        InputStream stream = App.class.getClassLoader().getResourceAsStream("blue_cube.txt");

        List<Facet> sides = FileUtils.readSides(stream);

        Cube cube = new Cube(sides);

        cube.solve();
        System.out.println(Cube.cubeList.size());
    }
}
