package com.big.javalibrary;


//Check if BST is valid or not
//				8
//		6	/		\ 10
//	 3/	  \7		9/	\12
//
public class BinarySearchTree {

    static class Node {
        Node left;
        Node right;
        int value = 0;

        Node(int val, Node left, Node right) {
            this.value = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(19,
                new Node(12,
                        new Node(9, null, null),
                        new Node(14, null, null)),
                new Node(22,
                        null,
                        new Node(25, null, null)));
        System.out.println(isValidTree(node, 0, 50));
    }

    private static boolean isValidTree(Node node, int lowerBound, int upperBoud) {
        if (node == null)
            return true;
        int val = node.value;

        if (val >= upperBoud || val <= lowerBound) {
            return false;

        }

        return isValidTree(node.left, lowerBound, val) // check Left

                &&

                isValidTree(node.right, val, upperBoud); // check Right
    }
}