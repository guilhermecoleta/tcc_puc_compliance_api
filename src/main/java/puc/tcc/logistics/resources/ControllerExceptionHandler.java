package puc.tcc.logistics.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import puc.tcc.logistics.exception.LogisticsException;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> validationException(MethodArgumentNotValidException ex, WebRequest request) {

        var error = ex.getBindingResult().getAllErrors().get(0);
        String field = ((FieldError) error).getField();
        String message = error.getDefaultMessage();

        return getErrorMessageResponseEntity(HttpStatus.BAD_REQUEST, field, message);
    }

    private ResponseEntity<ErrorMessage> getErrorMessageResponseEntity(HttpStatus statusCode, String field, String message) {
        ErrorMessage errorMessage = new ErrorMessage(
                statusCode.value(),
                new Date(),
                field,
                message);
        return new ResponseEntity<>(errorMessage, statusCode);
    }


    @ExceptionHandler(LogisticsException.class)
    public ResponseEntity<ErrorMessage> logisticsException(LogisticsException ex, WebRequest request) {
        return getErrorMessageResponseEntity(ex.getStatusCode(), ex.getField(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        log.error("error={}", ex.getMessage());
        return getErrorMessageResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR", "Ocorreu um erro");
    }
}