package com.util;

// thanks apache commons
public final class ArrayUtils {
    private ArrayUtils() {
        throw new AssertionError("instantiating utility class");
    }

    public static void reverse(boolean[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        boolean tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }
}
