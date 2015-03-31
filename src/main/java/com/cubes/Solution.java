package com.cubes;

import java.util.HashSet;
import java.util.Set;

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
    public Set<Cube> solve() {
        // First step of recursion
        Cube copy = cube.deepCopy();

        Set<Cube> solution = new HashSet<>();
        findNextFacet(solution, copy);

        return solution;
    }

    private static void findNextFacet(Set<Cube> solution, Cube cube) {

        for (Facet facet : cube.getCubeFaces()) {

            FacetRule facetRule = FacetRule.byAlreadyMatchedSideCount(cube.getCube().size());

            for (FacePermutation permutation : facet.getFacePermutations()) {

                if (facetRule.checkFacet(cube, permutation)) {

                    Cube cubeCopy = cube.deepCopy();
                    cubeCopy.getCube().add(permutation);
                    cubeCopy.getCubeFaces().remove(facet);

                    findNextFacet(solution, cubeCopy);
                }
            }
        }

        if (cube.getCube().size() == Cube.FACETS_COUNT) {
            solution.add(cube);
        }
    }
}
