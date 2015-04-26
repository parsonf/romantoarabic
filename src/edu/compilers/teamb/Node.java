package edu.compilers.teamb;


import java.util.ArrayList;

/**
 * Represents a node in a tree.
 */
public class Node {
    private Node parent;
    private Variable var;
    private ArrayList<Node> children;

    /**
     * Constructs a node.
     *
     * @param name the name of the node.
     */
    public Node(String name) {
        var = new Variable(name,0);
        children = new ArrayList<>();
    }

    /**
     * Constructs a node with a reference to the parent, sets the value of the node.
     *
     * @param _parent Reference to the parent node.
     * @param name The name of this node.
     * @param val The value of this node.
     */
    public Node(Node _parent, String name, int val) {
        this.parent = _parent;
        this.var = new Variable(name,val);
        children = new ArrayList<>();
    }

    /**
     * Returns the Variable for this node.
     *
     * @return the Variable for this node.
     */
    public Variable getVar() {
        return var;
    }

    /**
     * Returns the children for this node.
     *
     * @return the children for this node.
     */
    public ArrayList<Node> getChildren() {
        return children;
    }

    /**
     * Returns the parent of this node.
     *
     * @return the parent of this node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Adds a child to this node.
     *
     * @param child the child node to add.
     */
    public void addChild(String child) {
        children.add(new Node(this, child, 0));
    }


    /**
     * Adds a child node to this node with a value to set.
     *
     * @param child the child node to add.
     * @param val the value to set.
     */
    public void addChild(String child, int val) {
        children.add(new Node(this, child, val));
    }

    /**
     * Generates the String representation of this object.
     *
     * @return the String representation of this object.
     */
    @Override
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
}
