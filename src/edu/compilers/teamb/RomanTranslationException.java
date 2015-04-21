package edu.compilers.teamb;

/**
 * Created by Dereck Britton on 4/19/2015.
 * Custom exception class for the translator.  Used to specify the error message and the
 * module that generated it.
 */
public class RomanTranslationException extends Exception{

    private String affectedModule = "";
    public RomanTranslationException(String _module, String _message){
        super(_message);
        affectedModule = _module;
    }

    public String getAffectedModule(){
        return affectedModule;
    }
}
