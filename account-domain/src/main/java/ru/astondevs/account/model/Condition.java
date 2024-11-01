package ru.astondevs.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import ru.astondevs.account.model.enums.Period;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Условия счёта.
 *
 * @author Alexander Azaronak
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "condition_id")
    private UUID conditionId;

    @Column(length = 50, nullable = false)
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Period period;

    @Column(precision = 3, scale = 2, nullable = false)
    private BigDecimal percent;

    @Column(name = "payoff", nullable = false)
    private Boolean payOff;
}