package com.big.javalibrary.learning_algo.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Graph {
    private int value;
    private List<List<Integer>> adj;

    public Graph(int value) {
        this.value = value;
        adj = new LinkedList<>();

        for (int i = 0; i< value; i++) {
            adj.add(new LinkedList<Integer>());
        }
    }

//    View Stubbing
//    Algorithm
//    Android design
//    Architecture of photo sharing application
//    HR negotiations
//
//    Graph construction by fatory method.
//        module me singleton
//    Construction injection
//    provide @component prepares a graph
//    Dagger uses BFS to check cyclic dependency
//    Objects ka graph banata
//    Then you define kaha inject krna hy
//    Abstract factory pattern me dagger

    public void addAdj(int start, int to) {
        adj.get(start).add(to);
    }

}
