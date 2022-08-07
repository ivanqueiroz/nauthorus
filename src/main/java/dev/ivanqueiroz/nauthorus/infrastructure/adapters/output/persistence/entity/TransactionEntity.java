package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String currencyOrigin;
    private String currencyDestiny;
    private BigDecimal amount;
    private BigDecimal currencyRate;
    private BigDecimal result;
    private Date date;

}
