package mk.finki.ukim.mk.lab1.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String username){
        super(String.format("User with username: %s already exists!",username));
    }
}
