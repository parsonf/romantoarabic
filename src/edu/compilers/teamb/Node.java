package edu.compilers.teamb;


import java.util.ArrayList;

public class Node {
    private String value;
    private ArrayList<Node> children;

    public Node() {
        value = "";
    }

    public Node(String s) {
        value = s;
    }
}
