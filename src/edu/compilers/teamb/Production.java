package edu.compilers.teamb;


public class Production {
    private String head;
    private String body;
    private int[] initVals;

    public Production(String _head, String _body) {
        this.head = _head;
        this.body = _body;
        this.initVals = new int[0];
    }

    public Production(String _head, String _body, int[] _initVals) {
        this.head = _head;
        this.body = _body;
        this.initVals = _initVals;
    }


    public int[] getInitVals() {
        return initVals;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }
}
