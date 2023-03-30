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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    List<String> details = new ArrayList<>();
    // handleResourceNotFoundException : triggers when there is no resource with the specified ID in BDD
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<List<String>> handleNotFoundException(Exception ex) {
        details.clear();
        details.add(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(details);
    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        details.clear();
       // List<String> details ;
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField()+ " : " +error.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(details);
                        /*ResponseEntityBuilder.builder()
                        .timestamp(LocalDateTime.now())
                        .message("Erreur dans la requête")
                        .status(HttpStatus.BAD_REQUEST)
                        .errors(details).build());*/
    }

    // handleConstraintViolationException : triggers when @Validated fails
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException ex) {
        details.clear();
        details.add(ex.getSQLException().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(details);


    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<String>> handleJsonMalFormattedException(HttpMessageNotReadableException ex) {
        details.clear();
        details.add(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(details);
    }
    // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<List<String>> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex) {


        details.clear();
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
    public ResponseEntity<List<String>> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex) {
        details.clear();
        details.add(ex.getParameterName() + " parameter is missing");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(details);
    }
    // handleMethodArgumentTypeMismatch : triggers when a parameter's type does not match
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<List<String>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        details.clear();
        details.add(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(details);
    }
    // handleNoHandlerFoundException : triggers when the handler method is invalid
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<List<String>> handleNoHandlerFoundException(
            NoHandlerFoundException ex) {
        details.clear();
        details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(details);

    }
}
