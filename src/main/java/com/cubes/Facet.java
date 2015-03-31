package com.cubes;

import com.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static com.cubes.FacetSide.BOTTOM;
import static com.cubes.FacetSide.LEFT;
import static com.cubes.FacetSide.RIGHT;
import static com.cubes.FacetSide.TOP;

public class Facet {

    public static final int FACET_SIZE = 5;

    private final static int MAX_PERMUTATIONS = 8;
    private final static int SIDES_COUNT = 4;

    private static final char CHAR_SPACE = ' ';
    private static final char CHAR_O = 'o';

    private final boolean[][] sides;

    private Facet(boolean[][] sides) {
        this.sides = deepCopy(sides);
    }

    public Facet(List<String> lines) {
        sides = new boolean[SIDES_COUNT][FACET_SIZE];
        String line1 = lines.get(0);
        String line5 = lines.get(4);
        for (int i = 0; i < FACET_SIZE; i++) {
            String line = lines.get(i);
            sides[0][i] = line1.charAt(i) == CHAR_O;
            sides[1][i] = line.charAt(4) == CHAR_O;

            sides[2][FACET_SIZE - i - 1] = line5.charAt(i) == CHAR_O;
            sides[3][FACET_SIZE - i - 1] = line.charAt(0) == CHAR_O;
        }
    }

    /**
     * Return all possible permutation of current facet
     */
    public List<Facet> getAllPermutations() {
        List<Facet> result = new ArrayList<>(MAX_PERMUTATIONS);

        boolean[][] copy = deepCopy(sides);

        for (int i = 0; i < MAX_PERMUTATIONS; i++) {
            if (i == SIDES_COUNT) {
                mirror(copy);
            }

            result.add(new Facet(copy));

            turnRight(copy);
        }

        return result;
    }

    public boolean[] getSide(FacetSide side) {
        return sides[side.getOrder()];
    }

    private boolean[][] deepCopy(boolean[][] sides) {

        boolean[][] copy = new boolean[SIDES_COUNT][FACET_SIZE];

        for (int i = 0; i < SIDES_COUNT; i++) {
            copy[i] = sides[i].clone();
        }

        return copy;
    }

    /**
     * Gets lines of facet for printing
     */
    public List<String> getLineForPrint() {
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

    private char toChar(boolean c) {
        return c ? CHAR_O : CHAR_SPACE;
    }

    private static void turnRight(boolean[][] sides) {
        boolean[] temp = sides[TOP.getOrder()];
        sides[TOP.getOrder()] = sides[RIGHT.getOrder()];
        sides[RIGHT.getOrder()] = sides[BOTTOM.getOrder()];
        sides[BOTTOM.getOrder()] = sides[LEFT.getOrder()];
        sides[LEFT.getOrder()] = temp;
    }

    private static void mirror(boolean[][] sides) {
        boolean[] temp = sides[RIGHT.getOrder()];
        sides[RIGHT.getOrder()] = sides[LEFT.getOrder()];
        sides[LEFT.getOrder()] = temp;

        for (boolean[] side : sides) {
            ArrayUtils.reverse(side);
        }

    }

}
