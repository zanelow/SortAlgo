package zanelow.sortalgo.util;

import java.util.Random;

public final class Helper {

    private Helper() {}

    public static Integer[] createRandomArray(final int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();

        for(int i=0; i<array.length; i++) {
            // we don't need a big size to play around
            array[i] = random.nextInt(Byte.MAX_VALUE);
        }

        return array;
    }
}
