package com.big.javalibrary.learning_algo.linkedlist;

public class Node {
    private int number;
    private Node next;

    public Node(int number) {
        this.number = number;
    }

    public Node(int number, Node node) {
        this.number = number;
        this.next = node;
    }

    public boolean hasNext() {
        return next != null;
    }

    public int getNumber() {
        return number;
    }

    public Node getNext() {
        return next;
    }

    public Node setNext(Node next) {
        this.next = next;
        return this;
    }
}