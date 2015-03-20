package com.cubes;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains method to solve happy cube task
 * In result match of normalized form faces will be in nex position
 * 1 2 4
 * 3
 * 5
 * 6
 */
public class Solution {

    public static List<Cube> cubeList = new ArrayList<>();

    private final Cube cube;

    public Solution(Cube cube) {
        this.cube = cube;
    }

    /**
     * Solves happy cube in brute force way.
     * Takes each facet one by one turns / mirrors them and verifies that they suit each other
     */
    public List<Facet> solve() {
        // First step of recursion
        Cube c = cube.deepCopy();
        List<Facet> matchedFacets = new ArrayList<>();
        matchedFacets.add(c.getCubeFacets().remove(0));

        List<Facet> result = new ArrayList<>();
        findNextFacet(c, matchedFacets, result);

        return result;
    }

    private boolean findNextFacet(Cube cube, List<Facet> currentMatch, List<Facet> result) {
        if (currentMatch.size() == 6) {
            if(result.isEmpty()) {
                result.addAll(currentMatch);
            }
            cubeList.add(cube);
            return true;
        }

        for (Facet facet : cube.getCubeFacets()) {
            FacetRule rule = FacetRule.getByAlreadyMatchedSideCount(currentMatch.size());
            for (int i = 0; i < 8; i++, facet.turn()) {
                if (rule.checkFacet(currentMatch, facet)) {
                    Cube clone = cube.deepCopy();
                    List<Facet> matchClone = Facet.deepClone(currentMatch);

                    clone.getCubeFacets().remove(facet);
                    matchClone.add(facet);

                    findNextFacet(clone, matchClone, result);
                }
            }
        }

        return false;
    }
}
