package puc.tcc.logistics.resources.supplier;

import lombok.Data;

@Data
public class SupplierRequest {
    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private String phone;
}
