package com.cubes;

/**
 * This class contains method to solve happy cube task
 * In result match of normalized form faces will be in nex position
 * 1 2 4
 *   3
 *   5
 *   6
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
        // First step of recursion
        Cube copy = cube.deepCopy();

        return findNextFacet(copy);
    }

    private Cube findNextFacet(Cube cube) {

        for (Facet facet : cube.getCubeFaces()) {

            for (Facet permutation : facet.getAllPermutations()) {

                FacetRule facetRule = FacetRule.byAlreadyMatchedSideCount(cube.getCube().size());
                if (facetRule.checkFacet(cube, permutation)) {
                    Cube cubeCopy = cube.deepCopy();
                    cubeCopy.getCube().add(permutation);
                    cubeCopy.getCubeFaces().remove(facet);

                    Cube nextFacet = findNextFacet(cubeCopy);
                    if (nextFacet != null) {
                        return nextFacet;
                    }
                }
            }
        }

        if (cube.getCube().size() == Cube.FACETS_COUNT) {
            return cube;
        }

        return null;
    }
}
