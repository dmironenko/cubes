package com.cubes;

import com.util.ArrayUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.cubes.Cube.FACETS_COUNT;
import static com.cubes.FacePermutation.CHAR_O;
import static com.cubes.FacePermutation.FACET_SIZE;

public class Facet {

    private final static int MAX_PERMUTATIONS = 8;
    private final static int SIDES_COUNT = 4;

    private final Set<FacePermutation> facePermutations = new HashSet<>();

    public Facet(List<String> lines) {
        boolean[][] sides = new boolean[FACETS_COUNT][FACET_SIZE];
        String line1 = lines.get(0);
        String line5 = lines.get(4);
        for (int i = 0; i < FACET_SIZE; i++) {
            String line = lines.get(i);
            sides[0][i] = line1.charAt(i) == CHAR_O;
            sides[1][i] = line.charAt(4) == CHAR_O;

            sides[2][FACET_SIZE - i - 1] = line5.charAt(i) == CHAR_O;
            sides[3][FACET_SIZE - i - 1] = line.charAt(0) == CHAR_O;
        }

        getAllPermutations(sides);
    }

    private void getAllPermutations(boolean[][] sides) {

        for (int index = 0; index < MAX_PERMUTATIONS; index++) {

            if (index == SIDES_COUNT) {
                mirror(sides);
            }

            facePermutations.add(new FacePermutation(this, deepCopy(sides)));
            turnRight(sides);
        }
    }

    private static void turnRight(boolean[][] sides) {
        boolean[] temp = sides[Side.TOP.getOrder()];
        sides[Side.TOP.getOrder()] = sides[Side.RIGHT.getOrder()];
        sides[Side.RIGHT.getOrder()] = sides[Side.BOTTOM.getOrder()];
        sides[Side.BOTTOM.getOrder()] = sides[Side.LEFT.getOrder()];
        sides[Side.LEFT.getOrder()] = temp;
    }

    private static void mirror(boolean[][] sides) {
        boolean[] temp = sides[Side.RIGHT.getOrder()];
        sides[Side.RIGHT.getOrder()] = sides[Side.LEFT.getOrder()];
        sides[Side.LEFT.getOrder()] = temp;

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

    public Set<FacePermutation> getFacePermutations() {
        return facePermutations;
    }
}
