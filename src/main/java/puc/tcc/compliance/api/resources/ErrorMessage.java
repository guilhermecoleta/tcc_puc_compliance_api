package puc.tcc.compliance.api.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String field;
    private String message;
}
