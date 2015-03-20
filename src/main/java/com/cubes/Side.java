package com.cubes;

/**
 * Enum contains side of cube with defined order in Cube
 */
public enum Side {
    TOP(0),
    RIGHT(1),
    BOTTOM(2),
    LEFT(3);

    private final int order;

    private Side(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
