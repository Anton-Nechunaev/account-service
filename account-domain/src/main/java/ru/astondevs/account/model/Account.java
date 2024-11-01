package ru.astondevs.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.astondevs.account.model.enums.AccountStatus;
import ru.astondevs.account.model.enums.AccountType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Счёт пользователя.
 *
 * @author Alexander Azaronak
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    private UUID accountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_details_id", unique = true, nullable = false)
    private AccountDetails accountDetails;

    @Column(nullable = false)
    private UUID clientId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal currentBalance;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime openDate;

    @Column
    private LocalDateTime closeDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @ColumnDefault("false")
    @Column(nullable = false)
    private Boolean isMaster;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "condition", referencedColumnName = "condition_id", unique = true, nullable = false)
    private Condition condition;

    @ManyToOne
    @JoinColumn(name = "currency", referencedColumnName = "currency_id", nullable = false)
    private Currency currency;
}
