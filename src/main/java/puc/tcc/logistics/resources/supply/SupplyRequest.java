package puc.tcc.logistics.resources.supply;

import lombok.Data;

import java.io.Serializable;

@Data
public class SupplyRequest implements Serializable {
    private String description;
    private String name;
    private Long id;
}
