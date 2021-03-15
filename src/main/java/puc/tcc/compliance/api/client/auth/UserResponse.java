package puc.tcc.compliance.api.client.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String created;
    private String email;
    @JsonProperty("last_update")
    private String lastUpdate;
    private String name;
}
