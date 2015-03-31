package com.cubes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Cubes class - represents collection of facets
 */
class Cube {

    public final static int FACETS_COUNT = 6;

    private static final List<String> EMPTY_FACET_LINES = Arrays.asList("     ", "     ", "     ", "     ", "     ");

    private final List<Facet> faces;

    public Cube(List<Facet> faces) {
        this.faces = new ArrayList<>(faces);
    }

    /**
     * Solution is stored next way
     * 1 2 4
     *   3
     *   5
     *   6
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        appendLines(faces.get(0).getLineForPrint(), faces.get(1).getLineForPrint(), faces.get(3).getLineForPrint(), sb);
        appendLines(EMPTY_FACET_LINES, faces.get(2).getLineForPrint(), EMPTY_FACET_LINES, sb);
        appendLines(EMPTY_FACET_LINES, faces.get(4).getLineForPrint(), EMPTY_FACET_LINES, sb);
        appendLines(EMPTY_FACET_LINES, faces.get(5).getLineForPrint(), EMPTY_FACET_LINES, sb);

        return sb.toString();
    }

    private void appendLines(List<String> lines1, List<String> lines2, List<String> lines3, StringBuilder sb) {
        for (int i = 0; i < lines1.size(); i++) {
            sb.append(lines1.get(i));
            sb.append(lines2.get(i));
            sb.append(lines3.get(i));
            sb.append(System.lineSeparator());
        }
    }

    public List<Facet> getFaces() {
        return Collections.unmodifiableList(faces);
    }
}