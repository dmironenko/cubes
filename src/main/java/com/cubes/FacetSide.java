package com.cubes;

/**
 * Enum contains order of facet sides
 */
enum FacetSide {
    TOP(0),
    RIGHT(1),
    BOTTOM(2),
    LEFT(3);

    private final int order;

    private FacetSide(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
