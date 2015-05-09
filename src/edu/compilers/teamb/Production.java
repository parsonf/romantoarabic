package edu.compilers.teamb;


public class Production {
    private String head;
    private String body;
    private int[] initVals;

    /**
     * Constructor for a production
     * @param _head - the head of the production
     * @param _body - the body of the production (what it produces)
     */
    public Production(String _head, String _body) {
        this.head = _head;
        this.body = _body;
        this.initVals = new int[0];
    }

    /**
     * Constructor for a production with initial values
     * @param _head - the head of the production
     * @param _body - the body of the production (what it produces)
     * @param _initVals - the initial values assigned to the production
     */
    public Production(String _head, String _body, int[] _initVals) {
        this.head = _head;
        this.body = _body;
        this.initVals = _initVals;
    }

    /**
     * Get the initial values assigned to the production
     * @return - the array of initial values
     */
    public int[] getInitVals() {
        return initVals;
    }

    /**
     * Get the production's head
     * @return - the head of the production
     */
    public String getHead() {
        return head;
    }

    /**
     * Get the production's body
     * @return - the body of the production
     */
    public String getBody() {
        return body;
    }
}
