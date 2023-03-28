package tn.fmass.exceptions;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import tn.fmass.models.ResponseEntityBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handleResourceNotFoundException : triggers when there is no resource with the specified ID in BDD
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception ex) {


        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseEntityBuilder> handleArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> details ;
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName()+ " : " +error.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseEntityBuilder.builder()
                        .timestamp(LocalDateTime.now())
                        .message("Erreur dans la requête")
                        .status(HttpStatus.BAD_REQUEST)
                        .errors(details).build());
    }

    // handleConstraintViolationException : triggers when @Validated fails
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getSQLException().getMessage());


    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonMalFormattedException(HttpMessageNotReadableException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
    // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<List<String>> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex) {

        List<String> details = new ArrayList<>();


        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        details.add(builder.toString());

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(details);

    }
    // handleMissingServletRequestParameter : triggers when there are missing parameters
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<List<String>> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getParameterName() + " parameter is missing");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(details);
    }
    // handleMethodArgumentTypeMismatch : triggers when a parameter's type does not match
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(details);
    }
    // handleNoHandlerFoundException : triggers when the handler method is invalid
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex) {

        List<String> details = new ArrayList<>();
        details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(details);

    }
}
