package com.cubes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Cubes class - represents collection of facets
 * This class contains method to solve happy cube task
 * In result match of normalized form faces will be in nex position
 * 1 2 4
 * 3
 * 5
 * 6
 */

public class Cube {
    public static List<Cube> cubeList = new ArrayList<>();

    public List<Facet> getCubeFacets() {
        return cubeFacets;
    }

    private final List<Facet> cubeFacets;
    private final List<Facet> matchedFacets;

    private Cube() {
        cubeFacets = new LinkedList<>();
        matchedFacets = new ArrayList<>();
    }

    public Cube(List<Facet> cubeFacets) {
        this.cubeFacets = new ArrayList<>(cubeFacets);
        this.matchedFacets = new LinkedList<>();
    }

    public List<Facet> solve() {
        matchedFacets.add(cubeFacets.remove(0));

        findNextFace(this);

        return matchedFacets;
    }

    private void findNextFace(Cube cube) {
        if (cube.matchedFacets.size() == 6) {
            cubeList.add(cube);
            return;
        }

        for (Facet facet : cube.cubeFacets) {
            FacetRule rule = FacetRule.getByAlreadyMatchedSideCount(cube.matchedFacets.size());
            for (int i = 0; i < 8; i++, facet.turn()) {
                if (rule.checkFacet(cube, facet)) {
                    Cube clone = cube.deepCopy();
                    clone.cubeFacets.remove(facet);
                    clone.matchedFacets.add(facet);
                    findNextFace(clone);
                }
            }
        }
    }

    Facet getFirstFacet() {
        return matchedFacets.get(0);
    }

    Facet getSecondFacet() {
        return matchedFacets.get(1);
    }

    Facet getThirdFacet() {
        return matchedFacets.get(2);
    }

    Facet getFourthFacet() {
        return matchedFacets.get(3);
    }

    Facet getFifthFacet() {
        return matchedFacets.get(4);
    }

    /**
     * Makes deep copy of cube
     */
    public Cube deepCopy() {
        Cube clone = new Cube();

        for (Facet facet : matchedFacets) {
            clone.matchedFacets.add(facet.deepCopy());
        }

        for (Facet facet : cubeFacets) {
            clone.cubeFacets.add(facet.deepCopy());
        }

        return clone;
    }
}