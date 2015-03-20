package com.cubes;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contains rules that each next facet suits it's position
 */
public enum FacetRule {

    FIRST(0) {
        @Override
        public boolean checkFacet(Cube cube, Facet firstFacet) {
            // First facet always acceptable
            return true;
        }
    },
    SECOND(1) {
        @Override
        public boolean checkFacet(Cube cube, Facet secondFacet) {
            return checkSides(cube.getFirstFacet().getSide(Side.RIGHT), secondFacet.getSide(Side.LEFT));
        }
    },
    THIRD(2) {
        @Override
        public boolean checkFacet(Cube cube, Facet thirdFacet) {
            return checkSides(cube.getSecondFacet().getSide(Side.BOTTOM), thirdFacet.getSide(Side.TOP)) &&
                    checkSides(cube.getFirstFacet().getSide(Side.BOTTOM), thirdFacet.getSide(Side.LEFT)) &&
                    checkCorner(cube.getSecondFacet().getSide(Side.LEFT)[4], cube.getFirstFacet().getSide(Side.RIGHT)[4], thirdFacet.getSide(Side.TOP)[0]);
        }
    },
    FOURTH(3) {
        @Override
        public boolean checkFacet(Cube cube, Facet forthFacet) {
            return checkSides(cube.getSecondFacet().getSide(Side.RIGHT), forthFacet.getSide(Side.LEFT)) &&
                    checkSides(cube.getThirdFacet().getSide(Side.RIGHT), forthFacet.getSide(Side.BOTTOM)) &&
                    checkCorner(cube.getSecondFacet().getSide(Side.BOTTOM)[0], cube.getThirdFacet().getSide(Side.TOP)[4], forthFacet.getSide(Side.BOTTOM)[0]);
        }
    },
    FIFTH(4) {
        @Override
        public boolean checkFacet(Cube cube, Facet fifthFacet) {
            return checkSides(cube.getThirdFacet().getSide(Side.BOTTOM), fifthFacet.getSide(Side.TOP)) &&
                    checkSides(cube.getFirstFacet().getSide(Side.LEFT), fifthFacet.getSide(Side.LEFT)) &&
                    checkSides(cube.getFourthFacet().getSide(Side.RIGHT), fifthFacet.getSide(Side.RIGHT)) &&
                    checkCorner(cube.getFirstFacet().getSide(Side.LEFT)[0], cube.getThirdFacet().getSide(Side.LEFT)[0], fifthFacet.getSide(Side.LEFT)[0]) &&
                    checkCorner(cube.getFourthFacet().getSide(Side.RIGHT)[4], cube.getThirdFacet().getSide(Side.RIGHT)[4], fifthFacet.getSide(Side.RIGHT)[0]);
        }
    },
    SIXTH(5) {
        @Override
        public boolean checkFacet(Cube cube, Facet sixthFacet) {
            return checkSides(cube.getFifthFacet().getSide(Side.BOTTOM), sixthFacet.getSide(Side.TOP)) &&
                    checkSides(cube.getFirstFacet().getSide(Side.TOP), sixthFacet.getSide(Side.LEFT)) &&
                    checkSides(cube.getFourthFacet().getSide(Side.TOP), sixthFacet.getSide(Side.RIGHT)) &&
                    checkSides(cube.getSecondFacet().getSide(Side.TOP), sixthFacet.getSide(Side.BOTTOM)) &&

                    checkCorner(cube.getFifthFacet().getSide(Side.BOTTOM)[4], sixthFacet.getSide(Side.TOP)[0], cube.getFirstFacet().getSide(Side.TOP)[0]) &&
                    checkCorner(cube.getFirstFacet().getSide(Side.TOP)[4], sixthFacet.getSide(Side.LEFT)[0], cube.getSecondFacet().getSide(Side.TOP)[0]) &&
                    checkCorner(cube.getSecondFacet().getSide(Side.TOP)[4], sixthFacet.getSide(Side.RIGHT)[4], cube.getFourthFacet().getSide(Side.TOP)[0]) &&
                    checkCorner(cube.getFourthFacet().getSide(Side.TOP)[4], sixthFacet.getSide(Side.TOP)[4], cube.getFifthFacet().getSide(Side.BOTTOM)[0]);
        }
    };

    private static final Map<Integer, FacetRule> byMatchedSideCount = new HashMap<>();
    static {
        for (FacetRule rule : values()) {
            byMatchedSideCount.put(rule.matchedSideCount, rule);
        }
    }
    private final int matchedSideCount;

    private FacetRule(int matchedSideCount) {
        this.matchedSideCount = matchedSideCount;
    }

    public static FacetRule getByAlreadyMatchedSideCount(int count) {
        return byMatchedSideCount.get(count);
    }

    /**
     * Method that verifies that facet one each next position is suitable
     */
    public abstract boolean checkFacet(Cube cube, Facet facetToCheck);

    /**
     * Checks that two sides suits each other
     */
    private static boolean checkSides(boolean[] first, boolean[] second) {
        int limit = first.length - 1;

        for (int i = 1; i < limit; i++) {
            if (first[i] == second[limit - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that corners of 3 facets suits each other
     */
    private static boolean checkCorner(boolean first, boolean second, boolean third) {
        return toInt(first) + toInt(second) + toInt(third) == 1;
    }

    private static int toInt(boolean a) {
        return a ? 1 : 0;
    }
}
