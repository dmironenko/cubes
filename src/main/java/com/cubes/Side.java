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
