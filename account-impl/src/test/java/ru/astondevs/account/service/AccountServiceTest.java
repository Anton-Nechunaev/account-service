package ru.astondevs.account.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Collections;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.astondevs.account.dto.respones.AccountDetailsResponse;
import ru.astondevs.account.exception.NotFoundException;
import ru.astondevs.account.mapper.AccountMapper;
import ru.astondevs.account.model.Account;
import ru.astondevs.account.repository.AccountRepository;
import ru.astondevs.account.repository.projection.AccountsPayProjection;
import ru.astondevs.account.service.impl.AccountServiceImpl;
import ru.astondevs.account.util.ConstantUtil;
import ru.astondevs.account.util.ExceptionMessage;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper accountMapper;
    @InjectMocks
    private AccountServiceImpl accountService;

    private static UUID accountId;
    private static UUID clientId;
    private static AccountDetailsResponse accountDetailsResponseValid;
    private static Account account;
    private static AccountDetailsResponse.AccountDetails accountDetails;
    private static AccountsPayProjection accountsProjection;

    @BeforeAll
    public static void initDto() {
        accountId = UUID.randomUUID();
        clientId = UUID.randomUUID();
        accountDetails = AccountDetailsResponse.AccountDetails.builder()
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
            .currencyType(ConstantUtil.CURRENCY_TYPE.getDescription())
            .accountDetails(accountDetails)
            .build();
        account = Account.builder()
            .accountId(accountId)
            .clientId(clientId)
            .build();
        accountsProjection = AccountsPayProjection.builder()
            .accountId(accountId)
            .accountNumber(ConstantUtil.ACCOUNT_NUMBER)
            .balance(ConstantUtil.ACCOUNT_BALANCE)
            .currency(ConstantUtil.CURRENCY_TYPE)
            .build();
    }

    @Test
    @SneakyThrows
    void viewAccountDetailsSuccessfully() {
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountMapper.toAccountDetailsResponse(account)).thenReturn(
            accountDetailsResponseValid);

        AccountDetailsResponse result = accountService.viewAccountDetails(accountId);

        assertNotNull(result);
        assertEquals(accountDetailsResponseValid, result);
        verify(accountRepository).findById(accountId);
        verify(accountMapper).toAccountDetailsResponse(account);
    }

    @Test
    @SneakyThrows
    void viewAccountDetailsNotFoundException() {
        String description = ExceptionMessage.ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID.getDescription();

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            accountService.viewAccountDetails(accountId);
        });

        assertEquals(String.format(description, accountId), exception.getMessage());
        verify(accountRepository).findById(accountId);
        verifyNoMoreInteractions(accountMapper);
    }

    @Test
    @SneakyThrows
    @DisplayName("Successfully obtaining a list of projection")
    void viewAccountsPaySuccessfully() {
        when(accountRepository.findAccountsByClientId(clientId))
            .thenReturn(List.of(accountsProjection));

        List<AccountsPayProjection> result = accountRepository.findAccountsByClientId(clientId);

        assertEquals(List.of(accountsProjection), result);
        verify(accountRepository, times(1)).findAccountsByClientId(clientId);
    }

    @Test
    @SneakyThrows
    @DisplayName("An exception was not found when trying to get a list of projections")
    void viewAccountsPayNotFoundException() {
        UUID invalidClientId = UUID.randomUUID(); // Создаем случайный клиентский ID
        when(accountRepository.findAccountsByClientId(invalidClientId))
            .thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> accountService.viewAccountsPay(invalidClientId));
    }
}