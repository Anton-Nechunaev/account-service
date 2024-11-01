package ru.astondevs.account.util;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import ru.astondevs.account.model.enums.AccountType;
import ru.astondevs.account.model.enums.CurrencyType;

@UtilityClass
public class ConstantUtil {
    public final String URL_VIEW_ACCOUNT_DETAILS = "/auth/account/client-accounts/account-details";
    public final String URL_VIEW_ACCOUNTS_PAY = "/auth/account/client-accounts/pay-accounts";
    public final String ACCOUNT_NUMBER = "3214344rgerg231231ffwefewFWE";
    public final String BIK_BANK = "044525970";
    public final String COR_ACCOUNT_BANK = "30101810400000000604";
    public final String BANK_NAME = "Active bank";
    public final String INN_BANK = "7707083893";
    public final String KPP_BANK = "770701001";
    public final CurrencyType CURRENCY_TYPE = CurrencyType.RUB;
    public final AccountType ACCOUNT_TYPE = AccountType.CURRENT;
    public final BigDecimal ACCOUNT_BALANCE = BigDecimal.valueOf(100);
public final UUID VALID_CLIENT_ID = UUID.fromString("57663b11-4f13-4927-ad86-6b5097bbaf9f");
}