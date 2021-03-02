package puc.tcc.logistics.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class LogisticsException extends Exception {

    private HttpStatus statusCode;
    private String field;
    private String message;

}
