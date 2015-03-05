package com.cubes;

import com.util.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HappyCubeTest {

    private HappyCube happyCube;
    private List<Side> sides;

    @Before
    public void setUp() throws IOException {
        InputStream stream = HappyCubeTest.class.getClassLoader().getResourceAsStream("blue_cube.txt");

        sides = FileUtils.readSides(stream);
        happyCube = new HappyCube(sides);
    }

    @Test
    public void getAllPermutationTest() {
        List<List<Side>> allPermutations = happyCube.getAllPermutations();
        // P6 = 720
        assertEquals(720, allPermutations.size());

        for (int i = 0; i < allPermutations.size(); i++) {
            for (int j = i + 1; j < allPermutations.size(); j++) {
                assertFalse(allPermutations.get(i).equals(allPermutations.get(j)));
            }
        }
    }
}
