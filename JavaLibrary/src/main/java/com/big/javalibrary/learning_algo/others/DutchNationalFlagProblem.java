package com.big.javalibrary.learning_algo.others;

// Sorting a list with 3 unique numbers
public class DutchNationalFlagProblem {

    public static void main(String[] args) {
        int[] sortMe = new int[]{1, 2, 0, 2, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
        getSortedArray(sortMe);

        for (int a : sortMe) {
            System.out.print(a + " ");
        }
    }

    private static int[] getSortedArray(int[] sortArray) {
        int low = 0, mid = 0;
        int high = sortArray.length - 1;
        int temp = 0;
        while (mid <= high) {
            switch (sortArray[mid]) {
                case 0:
                    //swap low with mid, low++
                    temp = sortArray[low];
                    sortArray[low] = sortArray[mid];
                    sortArray[mid] = temp;
                    low++;
                    mid++;
                    break;

                case 1:
                    //Ideal case for mid so mid++ only
                    mid++;
                    break;

                case 2:
                    //swap mid with high and high--
                    temp = sortArray[mid];
                    sortArray[mid] = sortArray[high];
                    sortArray[high] = temp;
                    high--;
                    break;
            }
        }
        return sortArray;
    }


}
