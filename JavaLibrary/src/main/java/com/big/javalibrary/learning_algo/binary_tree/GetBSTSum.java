package com.big.javalibrary.learning_algo.binary_tree;

//n5 = Node(5)
//n4 = Node(4)
//n3 = Node(3)
//n2 = Node(2, n4, n5)
//n1 = Node(1, n2, n3)
//
//#      1
//#    /   \
//#   2     3
//#  / \
//# 4   5
//  \
//   8
//print(bst_numbers_sum(n1))
//# 262
//# Explanation: 1248 + 125 + 13 = 1386
public class GetBSTSum {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(8);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.right = node6;
        System.out.println(getSummation(node1, 0));

    }

    private static int getSummation(Node node, int value) {
        if (node == null) {
            return 0;
        }
        value = 10 * value + node.data;

        if (node.right == null && node.left == null) {
            return value;
        }
        return getSummation(node.left, value) + getSummation(node.right, value);
    }


    static class Node {
        int data;
        GetBSTSum.Node left;

        GetBSTSum.Node right;

        Node(int value) {
            this.data = value;
        }

    }
}
