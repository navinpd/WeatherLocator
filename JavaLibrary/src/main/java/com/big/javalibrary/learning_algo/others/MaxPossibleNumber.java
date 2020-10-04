package com.big.javalibrary.learning_algo.others;

/**
 * maximum possible value obtained by inserting 5
 */
public class MaxPossibleNumber {

    public static void main(String[] args) {
        System.out.println(solution(-297));
    }

    private static int solution(int n) {
        // write your code in Java SE 8
        int solution = 0;
        int value = Math.abs(n);
        int firstDigit = -1, secondDigit = -1, thirdDigit = -1, fourthDigit = -1;
        if (value < 10) {
            firstDigit = value;

        } else if (value < 100) {
            firstDigit = value % 10;
            secondDigit = (value - firstDigit) / 10;

        } else if (value < 1000) {
            firstDigit = value % 10;
            secondDigit = (value - firstDigit) % 100 / 10;
            thirdDigit = (value - firstDigit - secondDigit * 10) / 100;

        } else if (value <= 8000) {
            firstDigit = value % 10;
            secondDigit = (value - firstDigit) % 100 / 10;
            thirdDigit = (value - firstDigit - secondDigit * 10) % 1000 / 100;
            fourthDigit = (value - firstDigit - secondDigit * 10 - thirdDigit * 100) / 1000;
        }


        if (n >= 8000 || n <= -8000) {
            //Remove undesired range
            return -1;
        } else if (n >= 0) {

            if (n == 0) { // 0 => 50
                return 50;
            } else if (n <= 5) { // 4 => 54
                return 5 * 10 + n;
            } else if (n > 10 && n <= 55) { // 49 => 549
                return n += 5 * 100;
            } else if (n > 100 && n <= 555) { // 499 => 5499
                return n += 5 * 1000;
            } else if (n > 1000 && n <= 5555) { // 5434 => 55434
                return n += 5 * 10000;
            } else if (n > 5555) {
                if (thirdDigit < 5) {
                    return fourthDigit * 10000 + 5 * 1000 + thirdDigit * 100 + secondDigit * 10 + firstDigit;
                } else if (secondDigit < 5) {
                    return fourthDigit * 10000 + thirdDigit * 1000 + 5 * 100 + secondDigit * 10 + firstDigit;
                } else if (firstDigit < 5) {
                    return fourthDigit * 10000 + thirdDigit * 1000 + secondDigit * 100 + 5 * 100 + firstDigit;
                } else {
                    return fourthDigit * 10000 + thirdDigit * 1000 + secondDigit * 100 + firstDigit * 10 + 5;
                }

            } else if (n > 555) {
                if (secondDigit < 5) {
                    return thirdDigit * 1000 + 5 * 100 + secondDigit * 10 + firstDigit;
                } else if (firstDigit < 5) {
                    return thirdDigit * 1000 + secondDigit * 100 + 5 * 10 + firstDigit;
                } else {
                    return thirdDigit * 1000 + secondDigit * 100 + firstDigit * 10 + 5;
                }

            } else if (n > 55) {
                if (firstDigit > 5) { // 69 => 695
                    return secondDigit * 100 + firstDigit * 10 + 5;
                } else { // 73 => 753
                    return secondDigit * 100 + 50 + firstDigit;
                }

            } else { // (n > 5)  9 => 95
                return n * 10 + 5;
            }

        } else { // All negative numbers

            if (value >= 5555) {
                solution = 5 * 10000 + value;

            } else if (value < 1000 && value >= 555) {
                solution = value + 5 * 1000;

            } else if (value < 100 && value >= 55) {
                solution = value + 5 * 100;

            } else if (value < 10 && value >= 5) {
                solution = value + 5 * 10;

            } else if (value >= 1000) {
                if (thirdDigit > 5) {
                    solution =  fourthDigit * 10000 + 5 * 1000 + thirdDigit * 100 + secondDigit * 10 + firstDigit;
                } else if (secondDigit > 5) {
                    solution =  fourthDigit * 10000 + thirdDigit * 1000 + 5 * 100 + secondDigit * 10 + firstDigit;
                } else if (firstDigit > 5) {
                    solution =  fourthDigit * 10000 + thirdDigit * 1000 + secondDigit * 100 + 5 * 100 + firstDigit;
                } else {
                    solution =  fourthDigit * 10000 + thirdDigit * 1000 + secondDigit * 100 + firstDigit * 10 + 5;
                }

            } else if (value >= 100) {
                if (secondDigit > 5) {// 294
                    solution =  thirdDigit * 1000 + 5 * 100 + secondDigit * 10 + firstDigit;

                } else if (firstDigit > 5) { // 249
                    solution =  thirdDigit * 1000 + secondDigit * 100 + 5 * 10 + firstDigit;

                } else { //234 => 2345
                    solution =  thirdDigit * 1000 + secondDigit * 100 + firstDigit * 10 + 5;
                }

            } else if (value >= 10) {
                if (firstDigit > 5) { // 39 => 395
                    solution =  secondDigit * 100 + 50 + firstDigit;
                } else { // 22  => 225
                    solution =  secondDigit * 100 + firstDigit * 10 + 5;
                }

            } else { //if (value < 5) if (value < 5)
                solution = value * 10 + 5;
            }

            return solution * -1;
        }

    }
}
