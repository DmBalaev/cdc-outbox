package dm.bl.creditservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = CreditApplicationEntity.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationEntity {
    public static final String TABLE_NAME = "credit_application";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID clientId;
    private BigDecimal amount;
    private Integer term;
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
    private LocalDate creationAt;
    private LocalDate updateAt;
}
