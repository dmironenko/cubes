package com.cubes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cubes class - represents collection of facets
 */
class Cube {

    public final static int FACETS_COUNT = 6;

    private List<FacePermutation> cube = new ArrayList<>();
    private final List<Facet> cubeFaces;

    public Cube(List<Facet> cubeFaces) {
        this.cubeFaces = new ArrayList<>(cubeFaces);
    }

    /**
     * Solution is stored next way
     * 1 2 4
     *   3
     *   5
     *   6
     */
    @Override
    public String toString() {
        List<String> emptyFacetLines = Arrays.asList("     ", "     ", "     ", "     ", "     ");

        StringBuilder sb = new StringBuilder();

        appendLines(cube.get(0).getLines(), cube.get(1).getLines(), cube.get(3).getLines(), sb);
        appendLines(emptyFacetLines, cube.get(2).getLines(), emptyFacetLines, sb);
        appendLines(emptyFacetLines, cube.get(4).getLines(), emptyFacetLines, sb);
        appendLines(emptyFacetLines, cube.get(5).getLines(), emptyFacetLines, sb);

        return sb.toString();
    }

    private void appendLines(List<String> lines1, List<String> lines2, List<String> lines4, StringBuilder sb) {
        for (int i = 0; i < lines1.size(); i++) {
            sb.append(lines1.get(i));
            sb.append(lines2.get(i));
            sb.append(lines4.get(i));
            sb.append(System.lineSeparator());
        }
    }

    /**
     * Makes deep copy of cube
     */
    Cube deepCopy() {
        Cube cubeClone = new Cube(cubeFaces);
        cubeClone.cube = new ArrayList<>(cube);

        return cubeClone;
    }

    @Override
    public boolean equals(Object obj) {

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Cube otherCube = (Cube) obj;

        return checkOppositeFaces(otherCube);
    }

    private boolean checkOppositeFaces(Cube thatCube) {

        for (int index = 0; index < FACETS_COUNT / 2; index++) {

            FacePermutation thisCubePermutation = cube.get(index);
            boolean equals = false;

            for (FacePermutation otherCubePermutation : thatCube.cube) {

                if (otherCubePermutation.getFacet().equals(thisCubePermutation.getFacet())) {

                    int thatCubePermutationIndex = thatCube.cube.indexOf(otherCubePermutation);
                    int thisCubeOppositePermutationIndex = getOppositeIndex(index);
                    int thatCubeOppositePermutationIndex = getOppositeIndex(thatCubePermutationIndex);

                    FacePermutation thisCubeOppositePermutation = cube.get(thisCubeOppositePermutationIndex);
                    FacePermutation thatCubeOppositePermutation = thatCube.cube.get(thatCubeOppositePermutationIndex);

                    if (thisCubeOppositePermutation.getFacet() == thatCubeOppositePermutation.getFacet()) {
                        equals = true;
                        break;
                    }

                }

            }

            if (!equals) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        if (cube.size() == FACETS_COUNT) {
            for (int index = 0; index < FACETS_COUNT / 2; index++) {
                hashcode += 7496 * cube.get(index).getFacet().hashCode() * cube.get(getOppositeIndex(index)).getFacet().hashCode();
            }
        }
        return hashcode;
    }

    private static int getOppositeIndex(int index) {
        return ((index + 3) % +6);
    }

    public List<FacePermutation> getCube() {
        return cube;
    }

    public List<Facet> getCubeFaces() {
        return cubeFaces;
    }

}