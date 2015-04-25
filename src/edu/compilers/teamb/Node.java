package edu.compilers.teamb;


import java.util.ArrayList;

public class Node {

    private Node parent;
    private Variable var;
    private ArrayList<Node> children;


    public Node() {
        var = new Variable("",0);
        children = new ArrayList<>();
    }

    public Node(String name) {
        var = new Variable(name,0);
        children = new ArrayList<>();
    }

    public Node(String name, int var) {
        this.var = new Variable(name,var);
        children = new ArrayList<>();
    }

    public Node(Node _parent, String name) {
        this.parent = _parent;
        var = new Variable(name,0);
        children = new ArrayList<>();
    }

    public Node(Node _parent, String name, int var) {
        this.parent = _parent;
        this.var = new Variable(name,var);
        children = new ArrayList<>();
    }

    public Variable getVar() {
        return var;
    }

    public void setVar(Variable var) {
        this.var = var;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public String toString() {
        String s = var.getName();

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

    public void addChild(String child) {
        children.add(new Node(this, child, 0));
    }


    public void addChild(String child, int val) {
        children.add(new Node(this, child, val));
    }
}
