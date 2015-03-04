package com.cubes;

import java.util.List;

/**
 * @author Dmytro Myronenko dmironenko@cybervisiontech.com
 *         Date: 3/4/15.
 */
public class Side {
    static final int SIDE_SIZE = 5;

    boolean side[][] = new boolean[SIDE_SIZE][SIDE_SIZE];

    public Side(List<String> lines) {
        for (int i = 0; i < SIDE_SIZE; i++) {
            String line = lines.get(i);
            for (int j = 0; j < SIDE_SIZE; j++) {
                side[i][j] = line.charAt(j) == 'o';
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
                boolean tmp = side[i][j];
                side[i][j] = side[last - j][i];

                side[last][last - j] = side[j][last];
                side[last - j][i] = side[last][last - j];

                side[j][last] = tmp;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(SIDE_SIZE * SIDE_SIZE);

        for (int i = 0; i < SIDE_SIZE; i++) {
            for (int j = 0; j < SIDE_SIZE; j++) {
                sb.append(side[i][j] ? 'o' : ' ');
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
