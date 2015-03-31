package com.cubes;

import java.util.LinkedList;
import java.util.List;

/**
 * This class contains method to solve happy cube task
 * In result match of normalized form faces will be in next position
 * 1 2 4
 * 3
 * 5
 * 6
 */
class Solution {

    private final Cube cube;

    public Solution(Cube cube) {
        this.cube = cube;
    }

    /**
     * Solves happy cube in brute force way.
     */
    public Cube solve() {
        return findNextFacet(cube.getFaces(), new LinkedList<Facet>());
    }

    /**
     * Recursively searches for matching facets by getting all permutations and trying to match it to already matched
     */
    private Cube findNextFacet(List<Facet> allFaces, List<Facet> matched) {

        for (Facet facet : allFaces) {
            for (Facet permutation : facet.getAllPermutations()) {
                FacetRule facetRule = FacetRule.byAlreadyMatchedSideCount(matched.size());

                // Check if any of facet permutation matches its position
                if (facetRule.checkFacet(matched, permutation)) {
                    List<Facet> matchedCopy = new LinkedList<>(matched);
                    List<Facet> allFacesCopy = new LinkedList<>(allFaces);

                    matchedCopy.add(permutation);
                    allFacesCopy.remove(facet);

                    Cube nextFacet = findNextFacet(allFacesCopy, matchedCopy);
                    if (nextFacet != null) {
                        return nextFacet;
                    }
                }
            }
        }

        if (matched.size() == Cube.FACETS_COUNT) {
            return new Cube(matched);
        }

        return null;
    }
}
