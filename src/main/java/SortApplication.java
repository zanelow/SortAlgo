import zanelow.sortalgo.impl.MergeSortImpl;
import zanelow.sortalgo.util.Helper;

import java.util.Arrays;
import java.util.stream.Stream;

public class SortApplication {
    public static void main(String... args) {
        //Integer[] intArray = {9,6,7,8,4,6,3,2,11,116,15,55,24,32,14,36,21};
        Integer[] intArray = Helper.createRandomArray(35);

        System.out.print("Before sort: "); Arrays.stream(intArray).forEach(x -> System.out.print(x + " ")); System.out.println();
        long start = System.currentTimeMillis();
        MergeSortImpl.MergeSort ms = new MergeSortImpl.MergeSort(intArray);
        long diff = System.currentTimeMillis() - start;

        System.out.print("After sort: "); ms.getMergedList().forEach(x -> System.out.print(x + " ")); System.out.println();
        System.out.println("time taken = " + diff + " ms");


        // control
        System.out.print("Before sort: "); Arrays.stream(intArray).forEach(x -> System.out.print(x + " ")); System.out.println();
        start = System.currentTimeMillis();
        Arrays.sort(intArray);
        diff = System.currentTimeMillis() - start;

        System.out.print("After sort: "); Stream.of(intArray).forEach(x -> System.out.print(x + " "));
        System.out.println("\n(control) time taken = " + diff + " ms");
    }
}
