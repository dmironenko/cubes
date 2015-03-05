package com.cubes;

import java.util.Arrays;
import java.util.List;

public class Side {
    private static final int SIDE_SIZE = 5;

    private final boolean sides[][] = new boolean[SIDE_SIZE][SIDE_SIZE];

    public Side(List<String> lines) {
        for (int i = 0; i < SIDE_SIZE; i++) {
            String line = lines.get(i);
            for (int j = 0; j < SIDE_SIZE; j++) {
                sides[i][j] = line.charAt(j) == 'o';
            }
        }
    }

    /**
     * Turns 90 degrees right cube side
     */
    public void turnRight() {
        for (int i = 0; i < SIDE_SIZE / 2; i++) {
            int last = SIDE_SIZE - i - 1;
            for (int j = i; j < last; j++) {
                boolean tmp = sides[i][j];
                sides[i][j] = sides[last - j][i];

                sides[last - j][i] = sides[last][last - j];
                sides[last][last - j] = sides[j][last];

                sides[j][last] = tmp;
            }
        }
    }

    public boolean[][] getSides() {
        return Arrays.copyOf(sides, sides.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(SIDE_SIZE * SIDE_SIZE);

        for (int i = 0; i < SIDE_SIZE; i++) {
            for (int j = 0; j < SIDE_SIZE; j++) {
                sb.append(sides[i][j] ? 'o' : ' ');
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return Arrays.deepEquals(sides, ((Side) o).sides);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(sides);
    }
}
