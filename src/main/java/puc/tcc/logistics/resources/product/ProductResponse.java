package puc.tcc.logistics.resources.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse implements Serializable {

    private Long id;
    private String description;
    private String name;
    private Long supplier;
}
