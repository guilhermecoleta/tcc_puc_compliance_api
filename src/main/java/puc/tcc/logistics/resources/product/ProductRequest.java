package puc.tcc.logistics.resources.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductRequest implements Serializable {
    private String description;
    private String name;
    private Long id;
    private Long supplier;
}
