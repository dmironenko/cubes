package com.cubes;

import java.util.ArrayList;
import java.util.List;

public class HappyCube {

    static final int ROTATION_COUNT = 3;
    static final int SIDES = 6;

    private final List<Side> sides;

    /**
     * 0
     * 123
     * 4
     * 5
     */

    public HappyCube(List<Side> sides) {
        this.sides = new ArrayList<>(sides);
    }

    private <T> List<List<T>> getAllPermutations(List<T> sides) {
        List<List<T>> result = new ArrayList<>();
        result.add(new ArrayList<T>());

        for (T side : sides) {
            List<List<T>> current = new ArrayList<>();

            for (List<T> l : result) {
                for (int j = 0; j < l.size() + 1; j++) {
                    List<T> temp = new ArrayList<>(l);
                    temp.add(j, side);
                    current.add(temp);
                }
            }

            result = current;
        }

        return result;
    }

    public List<List<Side>> getAllPermutations() {
        return getAllPermutations(sides);
    }

}
