package puc.tcc.compliance.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ComplianceApiException extends Exception {

    private final HttpStatus statusCode;
    private final String field;
    private final String message;

}
