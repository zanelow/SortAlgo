package zanelow.sortalgo.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortImplTest {

    @Test
    void testSameSizeAfterSort() {
        Integer[] intArray = new Integer[] {1,2,3,4,5};
        MergeSortImpl.MergeSort ms = new MergeSortImpl.MergeSort(intArray);
        assertEquals(intArray.length, ms.getMergedList().size());
    }

}