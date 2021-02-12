package puc.tcc.logistics.resources.supply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyResponse implements Serializable {

    private Long id;
    private String description;
}
