package com.big.javalibrary.learning_algo.strings;

//https://www.geeksforgeeks.org/print-subsequences-string/?ref=lbp
public class PrintAllSubsequence {

    public static void main(String[] args) {
        getAnswer("", "abc");
    }


    private static void getAnswer(String begin, String end) {

        if (end.length() == 0) {
            System.out.println(begin);
            return;
        }

        getAnswer(begin, end.substring(1));

        getAnswer(begin + end.charAt(0), end.substring(1));
    }

}
