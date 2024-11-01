package ru.astondevs.account.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.astondevs.account.dto.respones.AccountDetailsResponse;
import ru.astondevs.account.model.Account;
import ru.astondevs.account.model.AccountDetails;
import ru.astondevs.account.model.Currency;
import ru.astondevs.account.util.ConstantUtil;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AccountMapperTest {

    @InjectMocks
    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    private static UUID accountId;
    private static UUID clientId;
    private static AccountDetailsResponse accountDetailsResponseValid;
    private static Account accountValid;
    private static Account accountInValid;
    private static AccountDetails accountDetails;
    private static Currency currency;
    private static AccountDetailsResponse.AccountDetails accountDetailsDto;
    private static AccountDetails accountDetailsInvalid;

    @BeforeAll
    public static void initDto() {
        accountId = UUID.randomUUID();
        clientId = UUID.randomUUID();
        accountDetailsDto = AccountDetailsResponse.AccountDetails.builder()
            .bikBank(ConstantUtil.BIK_BANK)
            .innBank(ConstantUtil.INN_BANK)
            .accountNumber(ConstantUtil.ACCOUNT_NUMBER)
            .corAccountBank(ConstantUtil.COR_ACCOUNT_BANK)
            .kppBank(ConstantUtil.KPP_BANK)
            .bankName(ConstantUtil.BANK_NAME)
            .build();
        accountDetailsResponseValid = AccountDetailsResponse.builder()
            .accountId(accountId)
            .clientId(clientId)
            .accountType(ConstantUtil.ACCOUNT_TYPE.getDescription())
            .accountDetails(accountDetailsDto)
            .build();
        currency = Currency.builder()
            .currencyType(ConstantUtil.CURRENCY_TYPE)
            .build();
        accountDetails = AccountDetails.builder()
            .bikBank(ConstantUtil.BIK_BANK)
            .innBank(ConstantUtil.INN_BANK)
            .accountNumber(ConstantUtil.ACCOUNT_NUMBER)
            .corAccountBank(ConstantUtil.COR_ACCOUNT_BANK)
            .kppBank(ConstantUtil.KPP_BANK)
            .bankName(ConstantUtil.BANK_NAME)
            .build();
        accountValid = Account.builder()
            .accountId(accountId)
            .clientId(clientId)
            .currency(currency)
            .accountDetails(accountDetails)
            .build();
        accountDetailsInvalid = AccountDetails.builder()
            .accountDetailsId(UUID.randomUUID())
            .build();
        accountInValid = Account.builder()
            .accountDetails(accountDetailsInvalid)
            .build();
    }

    @Test
    @DisplayName("Should return CreditResponseDto object if data is valid.")
    void toAccountDetailsResponse() {
        AccountDetailsResponse response = accountMapper.toAccountDetailsResponse(accountValid);

        AccountDetails details = accountValid.getAccountDetails();

        Assertions.assertAll(
            () -> Assertions.assertEquals(accountValid.getCurrency().getCurrencyType().name(), response.currencyType()),
            () -> Assertions.assertEquals(details.getAccountNumber(), response.accountDetails().accountNumber()),
            () -> Assertions.assertEquals(details.getBikBank(), response.accountDetails().bikBank()),
            () -> Assertions.assertEquals(details.getCorAccountBank(), response.accountDetails().corAccountBank()),
            () -> Assertions.assertEquals(details.getBankName(), response.accountDetails().bankName()),
            () -> Assertions.assertEquals(details.getInnBank(), response.accountDetails().innBank()),
            () -> Assertions.assertEquals(details.getKppBank(), response.accountDetails().kppBank())
        );
    }

    @Test
    @DisplayName("Should return CreditResponseDto with null variable.")
    void toAccountDetailsResponseNull() {
        AccountDetailsResponse response = accountMapper.toAccountDetailsResponse(accountInValid);

        Assertions.assertAll(
            () -> Assertions.assertNull(response.currencyType()),
            () -> Assertions.assertNull(response.accountDetails().accountNumber()),
            () -> Assertions.assertNull(response.accountDetails().bikBank()),
            () -> Assertions.assertNull(response.accountDetails().corAccountBank()),
            () -> Assertions.assertNull(response.accountDetails().bankName()),
            () -> Assertions.assertNull(response.accountDetails().innBank()),
            () -> Assertions.assertNull(response.accountDetails().kppBank())
        );
    }
}