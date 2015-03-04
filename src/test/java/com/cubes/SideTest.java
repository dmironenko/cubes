package com.cubes;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SideTest {

    private static final List<String> LEFT_ARROW = Arrays.asList(
            "  o  ",
            " oooo",
            "oooo ",
            " oooo",
            "  o  ");
    private static final List<String> TOP_ARROW = Arrays.asList(
            "  o  ",
            " ooo ",
            "ooooo",
            " ooo ",
            " o o ");
    private static final List<String> RIGHT_ARROW = Arrays.asList(
            "  o  ",
            "oooo ",
            " oooo",
            "oooo ",
            "  o  ");
    private static final List<String> BOTTOM_ARROW = Arrays.asList(" o o ",
            " ooo ",
            "ooooo",
            " ooo ",
            "  o  ");

    private Side side;

    private Side topArrow;
    private Side rightArrow;
    private Side bottomArrow;
    private Side leftArrow;

    @Before
    public void setUp() {
        side = new Side(LEFT_ARROW);
        leftArrow = new Side(LEFT_ARROW);

        topArrow = new Side(TOP_ARROW);
        rightArrow = new Side(RIGHT_ARROW);
        bottomArrow = new Side(BOTTOM_ARROW);
    }

    @Test
    public void createTest() {
        boolean expected[][] = {
                {false, false, true, false, false},
                {false, true, true, true, true},
                {true, true, true, true, false},
                {false, true, true, true, true},
                {false, false, true, false, false}
        };
        assertTrue(Arrays.deepEquals(side.getSides(), expected));
    }

    @Test
    public void turnRight() {
        side.turnRight();
        assertTrue(Arrays.deepEquals(side.getSides(), topArrow.getSides()));
        side.turnRight();
        assertTrue(Arrays.deepEquals(side.getSides(), rightArrow.getSides()));
        side.turnRight();
        assertTrue(Arrays.deepEquals(side.getSides(), bottomArrow.getSides()));
        side.turnRight();
        assertTrue(Arrays.deepEquals(side.getSides(), leftArrow.getSides()));
    }
}
