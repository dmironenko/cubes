package com.cubes;

import com.util.ArrayUtils;

import java.util.LinkedList;
import java.util.List;

import static com.cubes.Cube.FACETS_COUNT;
import static com.cubes.FacePermutation.CHAR_O;
import static com.cubes.FacePermutation.FACET_SIZE;
import static com.cubes.FacetSide.BOTTOM;
import static com.cubes.FacetSide.LEFT;
import static com.cubes.FacetSide.RIGHT;
import static com.cubes.FacetSide.TOP;

public class Facet {

    private final static int MAX_PERMUTATIONS = 8;
    private final static int SIDES_COUNT = 4;
    boolean[][] sides = new boolean[FACETS_COUNT][FACET_SIZE];

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

    public List<FacePermutation> getAllPermutations() {
        List<FacePermutation> result = new LinkedList<>();

        boolean[][] copy = deepCopy(sides);

        for (int index = 0; index < MAX_PERMUTATIONS; index++) {

            if (index == SIDES_COUNT) {
                mirror(copy);
            }


            result.add(new FacePermutation(this, deepCopy(copy)));
            turnRight(copy);
        }

        return result;
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

    boolean[][] deepCopy(boolean[][] sides) {

        boolean[][] sidesCopy = new boolean[SIDES_COUNT][FACET_SIZE];

        for (int index = 0; index < SIDES_COUNT; index++) {
            sidesCopy[index] = sides[index].clone();
        }

        return sidesCopy;
    }
}
