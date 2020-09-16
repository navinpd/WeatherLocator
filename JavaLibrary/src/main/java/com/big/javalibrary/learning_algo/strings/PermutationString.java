package com.big.javalibrary.learning_algo.strings;

class PermutationString {

    public static void main(String[] args) {
        getPermutation("VAN", "");
    }

    private static void getPermutation(String mainString, String answer) {
        if (mainString.length() == 0) {
            System.out.println(answer);
        }

        for (int i = 0; i < mainString.length(); i++) {
            char val = mainString.charAt(i);
            String catchMe = mainString.substring(0, i) + mainString.substring(i + 1);
            getPermutation(catchMe, answer + val);
        }
    }

}
