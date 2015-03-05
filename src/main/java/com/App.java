package com;

import com.cubes.Side;
import com.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        InputStream stream = App.class.getClassLoader().getResourceAsStream("blue_cube.txt");

        List<Side> sides = FileUtils.readSides(stream);

        for (Side s : sides) {
            System.out.println(s);
        }

    }
}
