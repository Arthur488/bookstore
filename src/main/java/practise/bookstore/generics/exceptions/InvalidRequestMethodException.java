package practise.bookstore.generics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class InvalidRequestMethodException extends RuntimeException {

    public InvalidRequestMethodException() {
        super("POST Method not allowed here!");
    }

    public InvalidRequestMethodException(String message) {
        super(message);
    }

}
