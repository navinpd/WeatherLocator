package com.big.javalibrary.learning_algo.androidinterview;

class AntiClockWiseRotateArray {

    public static void main(String[] args) {
        int x = 2;
        int y = 3;
        int count = 0;
        int[][] qArray = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                qArray[i][j] = count++;
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(" " + qArray[i][j]);
            }
            System.out.println("");
        }

        getRotatedArray(qArray);

    }

    private static int[][] getRotatedArray(int[][] mainArray) {
        int x = mainArray.length;
        int y = mainArray[0].length;
        int[][] answerArray = new int[y][x];

        for (int i = 0; i < mainArray[0].length; i++) {
            for (int j = mainArray.length - 1; j >= 0; j--) {
                answerArray[mainArray.length - i][j] = mainArray[j][i];
            }
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(" " + answerArray[i][j]);
            }
            System.out.println("");
        }

        return answerArray;
    }

}
