package ru.astondevs.account.repository.projection;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.astondevs.account.model.enums.CurrencyType;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsPayProjection {
    UUID accountId;
    String accountNumber;
    BigDecimal balance;
    CurrencyType currency;
}
