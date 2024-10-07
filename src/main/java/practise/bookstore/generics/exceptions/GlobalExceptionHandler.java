package practise.bookstore.generics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link GlobalExceptionHandler} class is a controller advice that handles exceptions occurring within the application.
 * <p>
 * It provides centralized exception handling for specific types of exceptions and defines how they should be handled.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the {@link NotFoundException} and returns an HTTP response with the corresponding status code and error message.
     *
     * @param ex the {@link NotFoundException} exception
     * @return the error message as a string
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link InvalidRequestMethodException} and returns an HTTP response with the corresponding status code and error message.
     *
     * @param ex the {@link InvalidRequestMethodException} exception
     * @return the error message as a string
     */
    @ExceptionHandler(InvalidRequestMethodException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public String handleInvalidRequestMethodException(InvalidRequestMethodException ex) {
        return ex.getMessage();
    }

    /**
     * Handles the {@link HttpMediaTypeNotSupportedException} and returns an HTTP response with the corresponding status code and error message.
     *
     * @param ex the {@link HttpMediaTypeNotSupportedException} exception
     * @return the error message as a string
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public String handleInvalidRequestMethodException(HttpMediaTypeNotSupportedException ex) {
        return ex.getMessage();
    }

}

