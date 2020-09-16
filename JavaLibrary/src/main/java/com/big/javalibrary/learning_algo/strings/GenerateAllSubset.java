package com.big.javalibrary.learning_algo.strings;

import java.util.HashSet;
import java.util.Set;

// https://www.geeksforgeeks.org/program-print-substrings-given-string/?ref=lbp
public class GenerateAllSubset {

    public static void main(String[] args) {
//        getAllSubString("ABCD");
    }

    private static void getAllSubString(String string) {
        for (int i = 0; i < string.length(); i++) {
            for (int j = i + 1; j <= string.length(); j++) {
                System.out.print(" " + string.substring(i, j));
            }
        }
    }
}
