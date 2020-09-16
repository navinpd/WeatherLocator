package com.big.javalibrary.learning_algo;


import java.util.ArrayList;
import java.util.List;

class TestClass {

    public static void main(String[] args) {
        List<String > item = new ArrayList<>();
        item.add("Y");
        item.add("Y");
        item.add("Y");
        item.add("N");
        item.add("Y");
        item.add("Y");
        item.add("Y");
        item.add("Y");
        item.add("Y");
        item.add("Y");

        getCount(1, item);
    }

    public static void getCount(int M, List<String > data) {
        String allPresent = "";
        int maxCount =0, currentCount = 0;
        boolean continueE = false;
        for (int i = 0; i< data.size(); i++) {
            allPresent = allPresent + "Y";
        }

        for (int i =0;i < data.size(); i++) {
            if(!data.get(i).equals(allPresent)){
                currentCount = 0;
                continueE = false;
            } else {
                if(continueE) {
                    currentCount++;
                    if(currentCount >= maxCount) {
                        maxCount = currentCount;
                    }

                } else {
                    continueE = true;
                    currentCount = 1;
                    if(maxCount == 0) {
                        maxCount = 1;
                    }
                }
            }
        }
        System.out.println(maxCount);
    }
}
