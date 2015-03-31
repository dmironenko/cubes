package com.cubes;

import java.util.HashMap;
import java.util.Map;

import static com.cubes.FacePermutation.FACET_SIZE;
import static com.cubes.Side.BOTTOM;
import static com.cubes.Side.LEFT;
import static com.cubes.Side.RIGHT;
import static com.cubes.Side.TOP;

public enum FacetRule {

    FIRST(0) {
        @Override
        boolean checkFacet(Cube cube, FacePermutation secondFacet) {
            // First facet always acceptable
            return true;
        }
    },
    SECOND(1) {
        @Override
        boolean checkFacet(Cube cube, FacePermutation secondFacet) {

            return checkSides(getFirstFacet(cube).getSide(RIGHT), secondFacet.getSide(LEFT));
        }
    },
    THIRD(2) {
        @Override
        boolean checkFacet(Cube cube, FacePermutation thirdFacet) {

            return checkSides(getSecondFacet(cube).getSide(BOTTOM), thirdFacet.getSide(TOP))
                    && checkSides(getFirstFacet(cube).getSide(BOTTOM), thirdFacet.getSide(LEFT))

                    && checkCorner(getSecondFacet(cube).getSide(LEFT)[RIGHT_CORNER],
                    getFirstFacet(cube).getSide(RIGHT)[RIGHT_CORNER], thirdFacet.getSide(TOP)[LEFT_CORNER]);
        }
    },
    FOURTH(3) {
        @Override
        boolean checkFacet(Cube cube, FacePermutation forthFacet) {

            return checkSides(getSecondFacet(cube).getSide(RIGHT), forthFacet.getSide(LEFT))
                    && checkSides(getThirdFacet(cube).getSide(RIGHT), forthFacet.getSide(BOTTOM))

                    && checkCorner(getSecondFacet(cube).getSide(BOTTOM)[LEFT_CORNER],
                    getThirdFacet(cube).getSide(TOP)[RIGHT_CORNER], forthFacet.getSide(BOTTOM)[RIGHT_CORNER]);
        }
    },
    FIFTH(4) {
        @Override
        boolean checkFacet(Cube cube, FacePermutation fifthFacet) {

            return checkSides(getThirdFacet(cube).getSide(BOTTOM), fifthFacet.getSide(TOP))
                    && checkSides(getFirstFacet(cube).getSide(LEFT), fifthFacet.getSide(LEFT))
                    && checkSides(getFourthFacet(cube).getSide(RIGHT), fifthFacet.getSide(RIGHT))

                    && checkCorner(getFourthFacet(cube).getSide(RIGHT)[RIGHT_CORNER],
                    getThirdFacet(cube).getSide(RIGHT)[RIGHT_CORNER], fifthFacet.getSide(RIGHT)[LEFT_CORNER])
                    && checkCorner(getFirstFacet(cube).getSide(LEFT)[LEFT_CORNER],
                    getThirdFacet(cube).getSide(LEFT)[LEFT_CORNER], fifthFacet.getSide(LEFT)[RIGHT_CORNER]);
        }
    },
    SIXTH(5) {
        @Override
        boolean checkFacet(Cube cube, FacePermutation sixthFacet) {

            return checkSides(getFifthFacet(cube).getSide(BOTTOM), sixthFacet.getSide(TOP))
                    && checkSides(getFirstFacet(cube).getSide(TOP), sixthFacet.getSide(LEFT))
                    && checkSides(getFourthFacet(cube).getSide(TOP), sixthFacet.getSide(RIGHT))
                    && checkSides(getSecondFacet(cube).getSide(TOP), sixthFacet.getSide(BOTTOM))

                    && checkCorner(getFifthFacet(cube).getSide(BOTTOM)[RIGHT_CORNER],
                    getFirstFacet(cube).getSide(TOP)[LEFT_CORNER], sixthFacet.getSide(TOP)[LEFT_CORNER])
                    && checkCorner(getFirstFacet(cube).getSide(TOP)[RIGHT_CORNER],
                    getSecondFacet(cube).getSide(TOP)[LEFT_CORNER], sixthFacet.getSide(LEFT)[LEFT_CORNER])
                    && checkCorner(getSecondFacet(cube).getSide(TOP)[RIGHT_CORNER],
                    getFourthFacet(cube).getSide(TOP)[LEFT_CORNER], sixthFacet.getSide(RIGHT)[RIGHT_CORNER])
                    && checkCorner(getFifthFacet(cube).getSide(BOTTOM)[LEFT_CORNER],
                    getFourthFacet(cube).getSide(TOP)[RIGHT_CORNER], sixthFacet.getSide(TOP)[RIGHT_CORNER]);
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

    public static FacetRule byAlreadyMatchedSideCount(int count) {
        return byMatchedSideCount.get(count);
    }

    /**
     * Method that verifies that facet one each next position is suitable
     */
    abstract boolean checkFacet(Cube cube, FacePermutation facet);

    /**
     * Checks that two sides suits each other
     */
    private static boolean checkSides(boolean[] firstFacetBytes, boolean[] secondFacetBytes) {

        int limit = FACET_SIZE - 1;

        for (int index = 1; index < limit; index++) {

            if (firstFacetBytes[index] == secondFacetBytes[limit - index]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that corners of 3 facets suits each other
     */
    private static boolean checkCorner(boolean first, boolean second, boolean third) {
        return ((toInt(first) + toInt(second) + toInt(third) == 1));
    }

    private static int toInt(boolean corner) {
        return corner ? 1 : 0;
    }

    FacePermutation getFirstFacet(Cube cube) {
        return cube.getCube().get(0);
    }

    FacePermutation getSecondFacet(Cube cube) {
        return cube.getCube().get(1);
    }

    FacePermutation getThirdFacet(Cube cube) {
        return cube.getCube().get(2);
    }

    FacePermutation getFourthFacet(Cube cube) {
        return cube.getCube().get(3);
    }

    FacePermutation getFifthFacet(Cube cube) {
        return cube.getCube().get(4);
    }

}
