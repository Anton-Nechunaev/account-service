package ru.astondevs.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Lob;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Информация о счете.
 *
 * @author Alexander Azaronak
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID accountDetailsId;

    @Column(nullable = false, unique = true, length = 50)
    private String accountNumber;

    @ColumnDefault(value = "044525970")
    @Column(nullable = false, length = 9)
    private String bikBank;

    @ColumnDefault(value = "Active-Bank")
    @Column(nullable = false, length = 50)
    private String bankName;

    @ColumnDefault(value = "7702070117")
    @Column(nullable = false, length = 10)
    private String innBank;

    @ColumnDefault(value = "30101810400000000970")
    @Column(nullable = false, length = 20)
    private String corAccountBank;

    @ColumnDefault(value = "784253001")
    @Column(nullable = false, length = 9)
    private String kppBank;

    @ColumnDefault(value = "00032538")
    @Column(nullable = false, length = 8)
    private String okpoBank;

    @ColumnDefault(value = "1025600132195")
    @Column(nullable = false, length = 13)
    private String ogrnBank;

    @ColumnDefault(value = "ACTV RU MM ХХХ")
    @Column(nullable = false, length = 14)
    private String swiftCodeBank;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Lob
    @Column(length = 100)
    private String reasonBlocking;
}