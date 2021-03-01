package puc.tcc.logistics.persistence.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@EqualsAndHashCode
@Data
public class ProductEntity implements Serializable {
    private static final String SEQ_PRODUCT_GEN = "SEQ_PRODUCT_GEN";
    private static final String SQ_PRODUCT = "SEQ_PRODUCT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_PRODUCT_GEN)
    @SequenceGenerator(name = SEQ_PRODUCT_GEN, sequenceName = SQ_PRODUCT, allocationSize = 1)
    private Long id;
    private String description;
    private String name;
    @Column(name = "file_path")
    private String filePath;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
}

