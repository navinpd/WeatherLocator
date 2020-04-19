package com.big.javalibrary.learning_algo.binary_tree;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TreeBasics {

    public static void main(String[] args) {
        Node node = new Node(1,
                new Node(2, null, null),
                new Node(3, new Node(4, null, null),
                        new Node(5, null, null)));
        levelOrderTraversal(node);

        Node node1 = new Node(1, new Node(3,
                new Node(4, null, null), null),
                new Node(3, null,
                        new Node(4, null, null)));
        System.out.println("This item is mirror: " + isMirror(node1.left, node1.right));
    }

    public static void inOrderTraversal(Node node) {
        System.out.println(node.value);
        if (node.left != null) {
            inOrderTraversal(node.left);
        }
        if (node.right != null) {
            inOrderTraversal(node.right);
        }
    }

    private static void preOrderTraversal(Node node) {
        if (node.left != null) {
            preOrderTraversal(node.left);
        }
        System.out.println(node.value);
        if (node.right != null) {
            preOrderTraversal(node.right);
        }
    }

    private static void postOrderTraversal(Node node) {
        if (node.left != null) {
            postOrderTraversal(node.left);
        }
        if (node.right != null) {
            postOrderTraversal(node.right);
        }
        System.out.println(node.value);
    }

    // Using Queue & Set also print the depth of tree
    private static void levelOrderTraversal(Node node) {
        if (node == null)
            return;

        Set<Node> levelCountSet = new HashSet<>();
        Queue<Node> queue = new ConcurrentLinkedDeque<>();
        queue.add(node);
        int levelCount = 0;

        while (queue.peek() != null || !levelCountSet.isEmpty()) {
            levelCount++;
            queue.addAll(levelCountSet);
            levelCountSet.clear();

            while (queue.peek() != null) {
                Node node1 = queue.poll();

                System.out.print(node1.value + " ");
                if (node1.right != null) {
                    levelCountSet.add(node1.right);
                }
                if (node1.left != null) {
                    levelCountSet.add(node1.left);
                }

            }
            System.out.println("");
        }
        System.out.println("Level count is: " + levelCount);
    }

    private static boolean isMirror(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else {
            if (node1 != null && node2 != null) {
                if (node1.value == node2.value) {
                    return isMirror(node1.left, node2.right)
                            && isMirror(node1.right, node2.left);

                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }


    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}
