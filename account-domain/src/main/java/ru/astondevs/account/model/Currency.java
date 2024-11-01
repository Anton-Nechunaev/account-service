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
import org.hibernate.annotations.CreationTimestamp;
import ru.astondevs.account.model.enums.Code;
import ru.astondevs.account.model.enums.CurrencyType;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Валюта счёта.
 *
 * @author Alexander Azaronak
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "currency_id")
    private UUID currencyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Code code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyType currencyType;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
}