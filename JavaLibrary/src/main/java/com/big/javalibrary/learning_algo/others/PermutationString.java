package com.big.javalibrary.learning_algo.others;

import java.util.ArrayList;

public class PermutationString {
    private static ArrayList<String> result = new ArrayList<String>();

    public static void main(String[] args) {
        getPermutations("", "abc");
        System.out.print(result);
    }

    private static void getPermutations(String prefix, String suffix) {
        if (suffix.length() == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < suffix.length(); i++) {
                getPermutations(suffix.charAt(i)+prefix,
                        suffix.substring(0, i) + suffix.substring(i + 1, suffix.length()));
            }
        }
    }

}
