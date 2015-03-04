package com.cubes;

import java.util.List;

/**
 * @author Dmytro Myronenko dmironenko@cybervisiontech.com
 *         Date: 3/4/15.
 */
public class Side {
    static final int SIDE_SIZE = 5;

    int cube[][] = new int[SIDE_SIZE][SIDE_SIZE];

    public Side(List<String> lines) {
        for (int i = 0; i < SIDE_SIZE; i++) {
            String line = lines.get(i);
            for (int j = 0; j < SIDE_SIZE; j++) {
                cube[i][j] = line.charAt(j) == 'o' ? 1 : 0;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(SIDE_SIZE * SIDE_SIZE);

        for (int i = 0; i < SIDE_SIZE; i++) {
            for (int j = 0; j < SIDE_SIZE; j++) {
                sb.append(cube[i][j] == 0 ? ' ' : 'o');
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

}
