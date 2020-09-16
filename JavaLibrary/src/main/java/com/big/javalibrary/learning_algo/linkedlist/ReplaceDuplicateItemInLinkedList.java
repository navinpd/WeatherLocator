package com.big.javalibrary.learning_algo.linkedlist;

class ReplaceDuplicateItemInLinkedList {

    public static void main(String[] args) {
        Node node1 = new Node(1)
                .setNext(new Node(1)
                        .setNext(new Node(2)
                                .setNext(new Node(2)
                                        .setNext(new Node(5)))));

        Node answer = getNonRepeating(node1);
        while (answer != null) {
            System.out.println(answer.getNumber());
            answer = answer.getNext();
        }
    }

    public static Node getNonRepeating(Node node) {
        Node answerNode = node;
        while (node.getNext() != null) {
            if (node.getNumber() == node.getNext().getNumber()) {
                node.setNext(node.getNext().getNext());
            }
            node = node.getNext();
        }
        return answerNode;
    }


}
