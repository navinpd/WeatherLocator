package com.big.javalibrary.learning_algo.androidinterview;

import java.util.HashSet;
import java.util.Set;

class NannyLambert {

    public static void main(String[] args) {
        long lastItem = getCount(1692);
        if(lastItem != -1) {
            System.out.println(lastItem);
        } else {
            System.out.println("INSOMNIAC");
        }
    }

    private static long getCount(long item) {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        Set<String> allIntSet = new HashSet<>();

        int multiplier = 0;
        long updatedCount = item;
        for (int i = 0; i < String.valueOf(item).length(); i++) {
            String value = Character.toString(String.valueOf(item).charAt(i));
            allIntSet.add(value);
        }
        while (allIntSet.size() != 10) {
            if(endTime - startTime > 20000) {
                break;
            }
            updatedCount = item * ++multiplier;

            for (int i = 0; i < String.valueOf(updatedCount).length(); i++) {
                String value = Character.toString(String.valueOf(updatedCount).charAt(i));
                allIntSet.add(value);
            }
            endTime = System.currentTimeMillis();
        }

        if (updatedCount == item && allIntSet.size() != 10) {
            return -1;
        } else {
            return updatedCount;
        }
    }

}
