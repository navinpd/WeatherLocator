package com.big.javalibrary.learning_algo.androidinterview;

class OptimumCoins {

    public static void main(String[] args) {
        getCoins(12.87);
    }

    private static void getCoins(double doubleAmount) {
        doubleAmount = doubleAmount * 100;
        int amount = (int) doubleAmount;

        if (amount > 100) {
            System.out.println("$1.00 " + amount / 100);
            amount = amount - 100 * (amount / 100);
        }
        if (amount > 50) {
            System.out.println("$0.50 " + amount / 50);
            amount = amount - 50 * (amount / 50);
        }
        if (amount > 20) {
            System.out.println("$0.20 " + amount / 20);
            amount = amount - 20 * (amount / 20);
        }
        if (amount > 10) {
            System.out.println("$0.10 " + amount / 10);
            amount = amount - 10 * (amount / 10);
        }
        if (amount > 5) {
            System.out.println("$0.05 " + amount / 5);
            amount = amount - 5 * (amount / 5);
        }
        if (amount > 1) {
            System.out.println("$0.01 " + amount);
        }

    }

}
