package edu.compilers.teamb;


public class Variable {
    private String name;
    private int val;

    public Variable(String _name, int _val) {
        this.name = _name;
        this.val = _val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
