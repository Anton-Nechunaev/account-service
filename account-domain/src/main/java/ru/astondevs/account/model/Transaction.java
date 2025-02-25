package ru.astondevs.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EnumType;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.astondevs.account.model.enums.TransactionType;
import ru.astondevs.account.model.enums.TransferStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Транзакция по счёту.
 *
 * @author Alexander Azaronak
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    private Account accountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal sum;

    @Lob
    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column(length = 50, nullable = false)
    private String location;

    @Column(length = 16, nullable = false)
    private String fromAccount;

    @Column(length = 16, nullable = false)
    private String toAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransferStatus transferStatus;
}