package com.cubes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class contains rules that each next facet suits it's position
 */
public enum FacetRule {

    FIRST(0) {
        @Override
        public boolean checkFacet(List<Facet> currentMatch, Facet firstFacet) {
            // First facet always acceptable
            return true;
        }
    },
    SECOND(1) {
        @Override
        public boolean checkFacet(List<Facet> currentMatch, Facet secondFacet) {
            return checkSides(getFirstFacet(currentMatch).getSide(Side.RIGHT), secondFacet.getSide(Side.LEFT));
        }
    },
    THIRD(2) {
        @Override
        public boolean checkFacet(List<Facet> currentMatch, Facet thirdFacet) {
            return checkSides(getSecondFacet(currentMatch).getSide(Side.BOTTOM), thirdFacet.getSide(Side.TOP)) &&
                    checkSides(getFirstFacet(currentMatch).getSide(Side.BOTTOM), thirdFacet.getSide(Side.LEFT)) &&
                    checkCorner(getSecondFacet(currentMatch).getSide(Side.LEFT)[4], getFirstFacet(currentMatch).getSide(Side.RIGHT)[4], thirdFacet.getSide(Side.TOP)[0]);
        }
    },
    FOURTH(3) {
        @Override
        public boolean checkFacet(List<Facet> currentMatch, Facet forthFacet) {
            return checkSides(getSecondFacet(currentMatch).getSide(Side.RIGHT), forthFacet.getSide(Side.LEFT)) &&
                    checkSides(getThirdFacet(currentMatch).getSide(Side.RIGHT), forthFacet.getSide(Side.BOTTOM)) &&
                    checkCorner(getSecondFacet(currentMatch).getSide(Side.BOTTOM)[0], getThirdFacet(currentMatch).getSide(Side.TOP)[4], forthFacet.getSide(Side.BOTTOM)[0]);
        }
    },
    FIFTH(4) {
        @Override
        public boolean checkFacet(List<Facet> currentMatch, Facet fifthFacet) {
            return checkSides(getThirdFacet(currentMatch).getSide(Side.BOTTOM), fifthFacet.getSide(Side.TOP)) &&
                    checkSides(getFirstFacet(currentMatch).getSide(Side.LEFT), fifthFacet.getSide(Side.LEFT)) &&
                    checkSides(getFourthFacet(currentMatch).getSide(Side.RIGHT), fifthFacet.getSide(Side.RIGHT)) &&
                    checkCorner(getFirstFacet(currentMatch).getSide(Side.LEFT)[0], getThirdFacet(currentMatch).getSide(Side.LEFT)[0], fifthFacet.getSide(Side.LEFT)[0]) &&
                    checkCorner(getFourthFacet(currentMatch).getSide(Side.RIGHT)[4], getThirdFacet(currentMatch).getSide(Side.RIGHT)[4], fifthFacet.getSide(Side.RIGHT)[0]);
        }
    },
    SIXTH(5) {
        @Override
        public boolean checkFacet(List<Facet> currentMatch, Facet sixthFacet) {
            return checkSides(getFifthFacet(currentMatch).getSide(Side.BOTTOM), sixthFacet.getSide(Side.TOP)) &&
                    checkSides(getFirstFacet(currentMatch).getSide(Side.TOP), sixthFacet.getSide(Side.LEFT)) &&
                    checkSides(getFourthFacet(currentMatch).getSide(Side.TOP), sixthFacet.getSide(Side.RIGHT)) &&
                    checkSides(getSecondFacet(currentMatch).getSide(Side.TOP), sixthFacet.getSide(Side.BOTTOM)) &&

                    checkCorner(getFifthFacet(currentMatch).getSide(Side.BOTTOM)[4], sixthFacet.getSide(Side.TOP)[0], getFirstFacet(currentMatch).getSide(Side.TOP)[0]) &&
                    checkCorner(getFirstFacet(currentMatch).getSide(Side.TOP)[4], sixthFacet.getSide(Side.LEFT)[0], getSecondFacet(currentMatch).getSide(Side.TOP)[0]) &&
                    checkCorner(getSecondFacet(currentMatch).getSide(Side.TOP)[4], sixthFacet.getSide(Side.RIGHT)[4], getFourthFacet(currentMatch).getSide(Side.TOP)[0]) &&
                    checkCorner(getFourthFacet(currentMatch).getSide(Side.TOP)[4], sixthFacet.getSide(Side.TOP)[4], getFifthFacet(currentMatch).getSide(Side.BOTTOM)[0]);
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
    public abstract boolean checkFacet(List<Facet> currentMatch, Facet facetToCheck);

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

    Facet getFirstFacet(List<Facet> currentMatch) {
        return currentMatch.get(0);
    }

    Facet getSecondFacet(List<Facet> currentMatch) {
        return currentMatch.get(1);
    }

    Facet getThirdFacet(List<Facet> currentMatch) {
        return currentMatch.get(2);
    }

    Facet getFourthFacet(List<Facet> currentMatch) {
        return currentMatch.get(3);
    }

    Facet getFifthFacet(List<Facet> currentMatch) {
        return currentMatch.get(4);
    }
}
