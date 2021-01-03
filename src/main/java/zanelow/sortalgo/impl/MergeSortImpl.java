package zanelow.sortalgo.impl;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortImpl {

    public static class MergeSort {

        ArrayList<Integer> mergedList = new ArrayList<>();

        public MergeSort(Integer... intArray) {
            if(intArray.length > 1) {
                Pair<Integer[], Integer[]> pair = Pair.of(Arrays.copyOfRange(intArray, 0, intArray.length/2), Arrays.copyOfRange(intArray, intArray.length/2, intArray.length));

                MergeSort ms1 = new MergeSort(pair.getLeft());
                MergeSort ms2 = new MergeSort(pair.getRight());

                merge(ms1.mergedList, ms2.mergedList);
            }
            else
                mergedList.add(intArray[0]);

        }

        void merge(ArrayList<Integer> leftList, ArrayList<Integer> rightList) {
            int leftInt, rightInt, leftIndex = 0, rightIndex = 0;

            while (true) {
                if (leftIndex < leftList.size() && rightIndex < rightList.size()) {
                    if (leftList.get(leftIndex) <= rightList.get(rightIndex)) mergedList.add(leftList.get(leftIndex++));
                    else mergedList.add(rightList.get(rightIndex++));
                }
                else if (leftIndex == leftList.size() && rightIndex < rightList.size()) {
                    mergedList.addAll(rightList.subList(rightIndex, rightList.size()));
                    break;
                }
                else if (rightIndex == rightList.size() && leftIndex < leftList.size()) {
                    mergedList.addAll(leftList.subList(leftIndex, leftList.size()));
                    break;
                }
            }
        }

        public List<Integer> getMergedList() {
            return mergedList;
        }
    }

    // from Java 11 implementation - Legacy (just for reference)
    private static void mergeSort(Object[] src,
                                  Object[] dest,
                                  int low,
                                  int high,
                                  int off) {
        int length = high - low;

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        low  += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && ((Comparable)src[p]).compareTo(src[q])<=0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

}
