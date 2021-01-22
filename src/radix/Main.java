package radix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] intArray = {3, 673, 106, 45, 2,23 ,1000, 0};

        intArray = RadixSort.ordenarArray(intArray);

        System.out.println(Arrays.toString(intArray));

    }
}
