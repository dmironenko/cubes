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

        System.out.println(cube.toNormalForm());

        Solution s = new Solution(cube);
        List<Facet> solution = s.solve();
        Cube c = new Cube(solution);

        System.out.println(s.cubeList.size());
        System.out.println(c.toNormalForm());
    }
}
