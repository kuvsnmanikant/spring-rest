package com.firstspringboot.learningspring.boot.exception;

// in this  class we will handle specific exception and global exceptions
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.firstspringboot.learningspring.boot.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionnHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
        ResourceNotFoundException exception,
        WebRequest webRequest
    ){
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(
        BlogAPIException exception,
        WebRequest webRequest
    ){
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)), exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
        Exception exception,
        WebRequest webRequest
    ){
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)   // handling the validate exception
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        }); 
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(
        DataIntegrityViolationException exception,
        WebRequest webRequest
    ){
        return new ResponseEntity<>(new ErrorDetails(new Date(), "we have a duplicate entry (Title)", webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(
        AccessDeniedException exception,
        WebRequest webRequest
    ){
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUsernameNotFoundException(
        UsernameNotFoundException exception,
        WebRequest webRequest
    ){
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }
}
