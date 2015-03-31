package com.cubes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cubes.Facet.FACET_SIZE;
import static com.cubes.FacetSide.BOTTOM;
import static com.cubes.FacetSide.LEFT;
import static com.cubes.FacetSide.RIGHT;
import static com.cubes.FacetSide.TOP;

/**
 * Class contains rules that each next facet suits it's position
 */
public enum FacetRule {

    FIRST(0) {
        @Override
        boolean checkFacet(List<Facet> matched, Facet secondFacet) {
            // First facet always acceptable
            return true;
        }
    },
    SECOND(1) {
        @Override
        boolean checkFacet(List<Facet> matched, Facet secondFacet) {
            return checkSides(getFirstFacet(matched).getSide(RIGHT), secondFacet.getSide(LEFT));
        }
    },
    THIRD(2) {
        @Override
        boolean checkFacet(List<Facet> matched, Facet thirdFacet) {
            return checkSides(getSecondFacet(matched).getSide(BOTTOM), thirdFacet.getSide(TOP))
                    && checkSides(getFirstFacet(matched).getSide(BOTTOM), thirdFacet.getSide(LEFT))

                    && checkCorner(getSecondFacet(matched).getSide(LEFT)[RIGHT_CORNER],
                    getFirstFacet(matched).getSide(RIGHT)[RIGHT_CORNER], thirdFacet.getSide(TOP)[LEFT_CORNER]);
        }
    },
    FOURTH(3) {
        @Override
        boolean checkFacet(List<Facet> matched, Facet forthFacet) {
            return checkSides(getSecondFacet(matched).getSide(RIGHT), forthFacet.getSide(LEFT))
                    && checkSides(getThirdFacet(matched).getSide(RIGHT), forthFacet.getSide(BOTTOM))

                    && checkCorner(getSecondFacet(matched).getSide(BOTTOM)[LEFT_CORNER],
                    getThirdFacet(matched).getSide(TOP)[RIGHT_CORNER], forthFacet.getSide(BOTTOM)[RIGHT_CORNER]);
        }
    },
    FIFTH(4) {
        @Override
        boolean checkFacet(List<Facet> matched, Facet fifthFacet) {
            return checkSides(getThirdFacet(matched).getSide(BOTTOM), fifthFacet.getSide(TOP))
                    && checkSides(getFirstFacet(matched).getSide(LEFT), fifthFacet.getSide(LEFT))
                    && checkSides(getFourthFacet(matched).getSide(RIGHT), fifthFacet.getSide(RIGHT))

                    && checkCorner(getFourthFacet(matched).getSide(RIGHT)[RIGHT_CORNER],
                    getThirdFacet(matched).getSide(RIGHT)[RIGHT_CORNER], fifthFacet.getSide(RIGHT)[LEFT_CORNER])
                    && checkCorner(getFirstFacet(matched).getSide(LEFT)[LEFT_CORNER],
                    getThirdFacet(matched).getSide(LEFT)[LEFT_CORNER], fifthFacet.getSide(LEFT)[RIGHT_CORNER]);
        }
    },
    SIXTH(5) {
        @Override
        boolean checkFacet(List<Facet> matched, Facet sixthFacet) {
            return checkSides(getFifthFacet(matched).getSide(BOTTOM), sixthFacet.getSide(TOP))
                    && checkSides(getFirstFacet(matched).getSide(TOP), sixthFacet.getSide(LEFT))
                    && checkSides(getFourthFacet(matched).getSide(TOP), sixthFacet.getSide(RIGHT))
                    && checkSides(getSecondFacet(matched).getSide(TOP), sixthFacet.getSide(BOTTOM))

                    && checkCorner(getFifthFacet(matched).getSide(BOTTOM)[RIGHT_CORNER],
                    getFirstFacet(matched).getSide(TOP)[LEFT_CORNER], sixthFacet.getSide(TOP)[LEFT_CORNER])
                    && checkCorner(getFirstFacet(matched).getSide(TOP)[RIGHT_CORNER],
                    getSecondFacet(matched).getSide(TOP)[LEFT_CORNER], sixthFacet.getSide(LEFT)[LEFT_CORNER])
                    && checkCorner(getSecondFacet(matched).getSide(TOP)[RIGHT_CORNER],
                    getFourthFacet(matched).getSide(TOP)[LEFT_CORNER], sixthFacet.getSide(RIGHT)[RIGHT_CORNER])
                    && checkCorner(getFifthFacet(matched).getSide(BOTTOM)[LEFT_CORNER],
                    getFourthFacet(matched).getSide(TOP)[RIGHT_CORNER], sixthFacet.getSide(TOP)[RIGHT_CORNER]);
        }
    };

    private final static int RIGHT_CORNER = 4;
    private final static int LEFT_CORNER = 0;

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

    /**
     * Factory method for getting rule by count of already matched facets
     */
    public static FacetRule byAlreadyMatchedSideCount(int count) {
        return byMatchedSideCount.get(count);
    }

    /**
     * Method that verifies that facet one each next position is suitable
     */
    abstract boolean checkFacet(List<Facet> matched, Facet facet);

    /**
     * Checks that two sides suits each other
     */
    private static boolean checkSides(boolean[] firstFacetBytes, boolean[] secondFacetBytes) {
        int limit = FACET_SIZE - 1;
        for (int i = 1; i < limit; i++) {

            if (firstFacetBytes[i] == secondFacetBytes[limit - i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that corners of 3 facets suits each other - just verifies that one of booleans is true
     */
    private static boolean checkCorner(boolean first, boolean second, boolean third) {
        return ((toInt(first) + toInt(second) + toInt(third) == 1));
    }

    private static int toInt(boolean corner) {
        return corner ? 1 : 0;
    }

    Facet getFirstFacet(List<Facet> alreadyMatched) {
        return alreadyMatched.get(0);
    }

    Facet getSecondFacet(List<Facet> alreadyMatched) {
        return alreadyMatched.get(1);
    }

    Facet getThirdFacet(List<Facet> alreadyMatched) {
        return alreadyMatched.get(2);
    }

    Facet getFourthFacet(List<Facet> alreadyMatched) {
        return alreadyMatched.get(3);
    }

    Facet getFifthFacet(List<Facet> alreadyMatched) {
        return alreadyMatched.get(4);
    }

}
