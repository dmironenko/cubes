package com.cubes;

import com.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Facet {
    public static final char CHAR_O = 'o';
    public static final char CHAR_SPACE = ' ';

    public static final int FACET_SIZE = 5;
    public static final int FACET_COUNT = 4;

    private final boolean[][] sides = new boolean[FACET_COUNT][FACET_SIZE];
    private int flag = 0;

    /**
     * Stores only sides in order of @{Side} enum
     */
    public Facet(List<String> lines) {
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

    Facet() {
    }

    public void turn() {
        mirrorIfRequired();

        boolean[] temp = sides[0];
        sides[0] = sides[1];
        sides[1] = sides[2];
        sides[2] = sides[3];
        sides[3] = temp;
    }

    private void mirrorIfRequired() {
        if (flag % 4 == 0) mirror();
        flag += 1;
    }

    private void mirror() {
        for (boolean[] side : sides) {
            ArrayUtils.reverse(side);
        }
    }

    public boolean[] getSide(Side side) {
        return sides[side.getOrder()];
    }

    @Override
    public String toString() {
        return Arrays.deepToString(sides);
    }

    Facet deepCopy() {
        Facet facet = new Facet();
        facet.flag = flag;

        for (int i = 0; i < sides.length; i++) {
            facet.sides[i] = Arrays.copyOf(sides[i], sides[i].length);
        }

        return facet;
    }

    static List<Facet> deepClone(List<Facet> facets) {
        List<Facet> clone = new ArrayList<>(facets.size());

        for (Facet facet : facets) {
            clone.add(facet.deepCopy());
        }

        return clone;
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
