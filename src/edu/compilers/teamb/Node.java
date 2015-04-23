package edu.compilers.teamb;


import java.util.ArrayList;

public class Node {
    private String value;
    private ArrayList<Node> children;


    public Node() {
        value = "";
        children = new ArrayList<>();
    }

    public Node(String s) {
        value = s;
        children = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void addChild(String child) {
        children.add(new Node(child));
    }

    public String toString() {
        String s = value;

        if (!children.isEmpty()) {
            s += "(";
            int i = 0;
            for (Node child : children) {
                s += child;
                i++;
                if (i < children.size()) {
                    s += ",";
                }
            }
            s += ")";
        }

        return s;
    }
}
