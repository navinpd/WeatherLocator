package com.big.javalibrary.learning_algo.others;

import java.util.Scanner;

public class SampleQuestion1 {
    public static String CaesarCipher(String str, int num) {
        String punctuation = ".?!,;:-_{[(\'\" …";
        String solution = "";
        if (num == 0) {
            return str;
        } else {
            for (int i = 0; i < str.length(); i++) {

                char character = str.charAt(i);
                char solChar;
                if (punctuation.contains(String.valueOf(character))) {
                    solChar = character;
                } else {
                    int ascii = (int) character;
                    ascii += num;
                    int diff =0;
                    if(ascii > 122) {
                        diff = ascii - 122;
                        ascii = 96 + diff;
                    } else if (ascii > 90 && ascii < 97) {
                        diff = ascii - 90;
                        ascii = 64 + diff;
                    }
                    solChar = (char) ascii;
                }
                solution = solution + solChar;
            }
        }
        return solution;
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(CaesarCipher(s.nextLine(), s.nextInt()));
    }

}
