package com.cubes;

/**
 * Enum contains order of facet sides
 */
enum Side {
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
