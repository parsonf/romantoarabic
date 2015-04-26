package edu.compilers.teamb;

/**
 * Created by Dereck Britton on 4/19/2015.
 * Custom exception class for the translator.  Used to specify the error message and the
 * module that generated it.
 */
public class RomanTranslationException extends Exception {
    private String affectedModule = "";

    /**
     * Creates a custom exception.
     *
     * @param _module The affected module.
     * @param _message The message that was provided from the exception.
     */
    public RomanTranslationException(String _module, String _message){
        super(_message);
        affectedModule = _module;
    }

    /**
     * Returns the affected module.
     *
     * @return the affected module.
     */
    public String getAffectedModule(){
        return affectedModule;
    }
}
