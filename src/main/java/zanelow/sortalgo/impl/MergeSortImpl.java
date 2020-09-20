package zanelow.sortalgo.impl;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

            // turn on for debug
            /*System.out.print("Left = ");
            leftList.forEach(x -> System.out.print(x + " "));

            System.out.print(", Right = ");
            rightList.forEach(x -> System.out.print(x + " "));
            System.out.println();*/

            while (true) {
                try {
                    leftInt = leftList.get(leftIndex);
                    rightInt = rightList.get(rightIndex);

                    if (leftInt <= rightInt) {
                        mergedList.add(leftInt);
                        leftIndex++;
                    }
                    else {
                        mergedList.add(rightInt);
                        rightIndex++;
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    if(leftIndex >= leftList.size() && rightIndex >= rightList.size())
                        break;

                    if (leftIndex >= leftList.size())
                        mergedList.addAll(rightList.subList(rightIndex, rightList.size()));
                    else
                        mergedList.addAll(leftList.subList(leftIndex, leftList.size()));

                    break;
                }
            }
        }

        List<Integer> getMergedList() {
            return mergedList;
        }
    }

    // from Java 11 implementation - Legacy
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


    public static void main(String... args) {
        Integer[] intArray = {9,6,7,8,4,6,3,2,11,116,15,55,24,32,14,36,21};

        System.out.print("Before sort: "); Arrays.stream(intArray).forEach(x -> System.out.print(x + " ")); System.out.println();
        long start = System.currentTimeMillis();
        MergeSort ms = new MergeSort(intArray);
        long diff = System.currentTimeMillis() - start;

        ms.mergedList.forEach(x -> System.out.print(x + " "));
        System.out.println("\nArray Size = " + intArray.length + ", Merge List size = " + ms.mergedList.size());
        System.out.println("time taken = " + diff + " ms");


        // control
        System.out.print("Before sort: "); Arrays.stream(intArray).forEach(x -> System.out.print(x + " ")); System.out.println();
        start = System.currentTimeMillis();
        Arrays.sort(intArray);
        diff = System.currentTimeMillis() - start;

        Stream.of(intArray).forEach(x -> System.out.print(x + " "));
        System.out.println("\n(control) time taken = " + diff + " ms");
    }

}
