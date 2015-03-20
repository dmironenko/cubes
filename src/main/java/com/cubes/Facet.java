package com.cubes;

import com.util.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class Facet {
    private final int FACET_SIZE = 5;
    private final int FACET_COUNT = 4;

    private boolean[][] sides = new boolean[FACET_COUNT][FACET_SIZE];
    private int flag = 0;

    public Facet(List<String> lines) {
        String line1 = lines.get(0);
        for (int i = 0; i < FACET_SIZE; i++) {
            sides[0][i] = line1.charAt(i) == 'o';
        }

        for (int i = 0; i < FACET_SIZE; i++) {
            String line = lines.get(i);
            sides[1][i] = line.charAt(4) == 'o';
        }

        String line5 = lines.get(4);
        for (int i = FACET_SIZE - 1; i >= 0; i--) {
            sides[2][FACET_SIZE - i - 1] = line5.charAt(i) == 'o';
        }

        for (int i = FACET_SIZE - 1; i >= 0; i--) {
            String line = lines.get(i);
            sides[3][FACET_SIZE - i - 1] = line.charAt(0) == 'o';
        }
    }

    Facet(boolean[][] sides) {
        this.sides = sides;
    }

    public void turn() {
        roundCheck();

        boolean[] temp = sides[0];
        sides[0] = sides[1];
        sides[1] = sides[2];
        sides[2] = sides[3];
        sides[3] = temp;
    }

    private void roundCheck() {
        if (flag % 4 == 0) turnAround();
        flag += 1;
    }

    private void turnAround() {
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
        Facet facet = new Facet(new boolean[4][5]);
        facet.flag = flag;

        for (int i = 0; i < sides.length; i++) {
            facet.sides[i] = Arrays.copyOf(sides[i], sides[i].length);
        }

        return facet;
    }
}
