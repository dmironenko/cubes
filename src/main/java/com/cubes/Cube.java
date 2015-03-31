package com.cubes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cubes class - represents collection of facets
 */
class Cube {

    public final static int FACETS_COUNT = 6;

    private static final List<String> EMPTY_FACET_LINES = Arrays.asList("     ", "     ", "     ", "     ", "     ");

    private List<Facet> cube = new ArrayList<>();
    private final List<Facet> cubeFaces;

    public Cube(List<Facet> cubeFaces) {
        this.cubeFaces = new ArrayList<>(cubeFaces);
    }

    /**
     * Solution is stored next way
     * 1 2 4
     * 3
     * 5
     * 6
     */
    @Override
    public String toString() {


        StringBuilder sb = new StringBuilder();

        appendLines(cube.get(0).getLines(), cube.get(1).getLines(), cube.get(3).getLines(), sb);
        appendLines(EMPTY_FACET_LINES, cube.get(2).getLines(), EMPTY_FACET_LINES, sb);
        appendLines(EMPTY_FACET_LINES, cube.get(4).getLines(), EMPTY_FACET_LINES, sb);
        appendLines(EMPTY_FACET_LINES, cube.get(5).getLines(), EMPTY_FACET_LINES, sb);

        return sb.toString();
    }

    private void appendLines(List<String> lines1, List<String> lines2, List<String> lines4, StringBuilder sb) {
        for (int i = 0; i < lines1.size(); i++) {
            sb.append(lines1.get(i));
            sb.append(lines2.get(i));
            sb.append(lines4.get(i));
            sb.append(System.lineSeparator());
        }
    }

    /**
     * Makes deep copy of cube
     */
    Cube deepCopy() {
        Cube cubeClone = new Cube(cubeFaces);
        cubeClone.cube = new ArrayList<>(cube);

        return cubeClone;
    }

    public List<Facet> getCube() {
        return cube;
    }

    public List<Facet> getCubeFaces() {
        return cubeFaces;
    }

}