package com.big.javalibrary.learning_algo.implementation;

public class Implementation {
    public static void main(String[] args) {
        Local<String > local = new Local(2);
        local.addItem("4");
        local.addItem("5");
        local.addItem("7");
        local.addItem("3");
//        local.addItem(3);
        local.removeItem("3");
        local.addItem("9");
        local.addItem("13");

        System.out.println(local.isAvailable("3"));
        System.out.println();
        Object[] allItems = local.getItems();
        for (Object item:allItems) {
            System.out.print(item + " ");
        }
    }
}
