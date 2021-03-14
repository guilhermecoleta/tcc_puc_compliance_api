package puc.tcc.logistics.resources.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest implements Serializable {
    private Long id;
    @Length(max = 500, message = "O campo deve ter no máximo 500 caracteres")
    @NotBlank(message = "O campo descrição deve ser preenchido")
    private String description;
    @Length(max = 500, message = "O campo deve ter no máximo 500 caracteres")
    @NotBlank(message = "O campo nome deve ser preenchido")
    private String name;
    private Long supplier;
}
