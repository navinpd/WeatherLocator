package com.big.javalibrary.learning_algo.linkedlist;

class ReverseLinkedList {

    public static void main(String[] args) {
        Node newNode = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, null)))));

        Node ansNode = getReversedList(newNode);

        while (ansNode != null) {
            System.out.println(ansNode.getNumber());
            ansNode = ansNode.getNext();
        }
    }

    private static Node getReversedList(Node node) {
        Node prevNode = null;
        Node currentNode = node;

        while (currentNode != null) {
            Node temp = currentNode.getNext();
            currentNode.setNext(prevNode);


            prevNode = currentNode;
            currentNode = temp;
        }

        return prevNode;
    }

}
