package puc.tcc.compliance.api.resources.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponse {
    private Long id;
    private String name;
    @JsonProperty(value = "dat_updated")
    private LocalDateTime datUpdated;
    private Integer version;
    private String text;
}
