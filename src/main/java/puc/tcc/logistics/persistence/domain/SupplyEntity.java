package puc.tcc.logistics.persistence.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "supply")
@EqualsAndHashCode
@Data
public class SupplyEntity implements Serializable {
    private static final String SEQ_SUPPLY_GEN = "SEQ_SUPPLY_GEN";
    private static final String SQ_SUPPLY = "SEQ_SUPPLY";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_SUPPLY_GEN)
    @SequenceGenerator(name = SEQ_SUPPLY_GEN, sequenceName = SQ_SUPPLY, allocationSize = 1)
    private Long id;
    private String description;
}

