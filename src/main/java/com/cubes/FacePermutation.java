package com.cubes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FacePermutation {

    public final static int FACET_SIZE = 5;
    public static final char CHAR_O = 'o';
    private static final char CHAR_SPACE = ' ';

    private final boolean[][] sides;
    private final Facet facet;

    FacePermutation(Facet facet, boolean[][] sides) {
        this.facet = facet;
        this.sides = sides;
    }

    @Override
    public boolean equals(Object obj) {

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        FacePermutation thatFacePermutation = (FacePermutation) obj;

        return Arrays.deepEquals(sides, thatFacePermutation.sides);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(sides);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(sides);
    }

    public boolean[] getSide(FacetSide side) {
        return sides[side.getOrder()];
    }

    public Facet getFacet() {
        return facet;
    }

    public List<String> getLines() {

        List<String> lines = new ArrayList<>(FACET_SIZE);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FACET_SIZE; i++) {
            sb.append(toChar(sides[0][i]));
        }

        lines.add(sb.toString());

        for (int i = 1; i < FACET_SIZE - 1; i++) {
            sb = new StringBuilder();

            sb.append(toChar(sides[3][FACET_SIZE - i - 1]));

            sb.append(CHAR_O).append(CHAR_O).append(CHAR_O);
            sb.append(toChar(sides[1][i]));
            lines.add(sb.toString());
        }

        sb = new StringBuilder();
        for (int i = 0; i < FACET_SIZE; i++) {
            sb.append(toChar(sides[2][FACET_SIZE - i - 1]));
        }

        lines.add(sb.toString());

        return lines;
    }

    char toChar(boolean c) {
        return c ? CHAR_O : CHAR_SPACE;
    }
}
