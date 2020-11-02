package pl.coderslab.charity.exceptions;

public class IdsAreNotTheSameException extends RuntimeException {
    public IdsAreNotTheSameException(String message){
        super(message);
    }
}