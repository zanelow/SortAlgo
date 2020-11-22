package zanelow.sortalgo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelperTest {

    @Test
    public void testCreateRandomArray()
    {
        Integer[] array = Helper.createRandomArray(10);

        assertEquals(10, array.length);
        for(Integer element : array) {
            assertTrue(element <= Short.MAX_VALUE);
            System.out.print(element + " ");
        }
    }
}