package com.cubes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cubes class - represents collection of facets
 */
public class Cube {

    private final List<Facet> cubeFacets;

    public Cube(List<Facet> cubeFacets) {
        this.cubeFacets = new ArrayList<>(cubeFacets);
    }

    public List<Facet> getCubeFacets() {
        return cubeFacets;
    }

    /**
     * Makes deep copy of cube
     */
    public Cube deepCopy() {
        return new Cube(Facet.deepClone(cubeFacets));
    }

    /**
     * Solution is stored next way
     * 1 2 4
     *   3
     *   5
     *   6
     */
    public String toNormalForm() {
        List<String> emptyFacetLines = Arrays.asList("     ", "     ", "     ", "     ", "     ");

        StringBuilder sb = new StringBuilder();

        appendLines(cubeFacets.get(0).getLines(), cubeFacets.get(1).getLines(), cubeFacets.get(3).getLines(), sb);
        appendLines(emptyFacetLines, cubeFacets.get(2).getLines(), emptyFacetLines, sb);
        appendLines(emptyFacetLines, cubeFacets.get(4).getLines(), emptyFacetLines, sb);
        appendLines(emptyFacetLines, cubeFacets.get(5).getLines(), emptyFacetLines, sb);

        return sb.toString();
    }

    private void appendLines(List<String> lines1, List<String> lines2, List<String> lines4, StringBuilder sb) {
        for(int i = 0 ; i < lines1.size(); i++) {
            sb.append(lines1.get(i));
            sb.append(lines2.get(i));
            sb.append(lines4.get(i));
            sb.append(System.lineSeparator());
        }
    }


}