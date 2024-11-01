package ru.astondevs.account.service.impl;

import static ru.astondevs.account.util.ExceptionMessage.ACCOUNTS_NOT_FOUND_BY_CLIENT_ID;
import static ru.astondevs.account.util.ExceptionMessage.ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astondevs.account.dto.respones.AccountDetailsResponse;
import ru.astondevs.account.exception.NotFoundException;
import ru.astondevs.account.mapper.AccountMapper;
import ru.astondevs.account.model.Account;
import ru.astondevs.account.repository.AccountRepository;
import ru.astondevs.account.repository.projection.AccountsPayProjection;
import ru.astondevs.account.service.AccountService;

/**
 * Реализация сервиса для работы со счётом.
 *
 * @author Alexande Azaronak
 */
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    /**
     * Возвращает реквизиты счёта.
     *
     * @param accountId идентификатор счёта.
     */
    @Override
    @Transactional(readOnly = true)
    public AccountDetailsResponse viewAccountDetails(UUID accountId) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(
                () -> new NotFoundException(ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID.getDescription()
                    .formatted(accountId)));

        return accountMapper.toAccountDetailsResponse(account);
    }

    /**
     * Возвращает список проекций, которые хранят информацию об id счёта, номер счёта, текущий
     * баланс, тип валюты.
     *
     * @param clientId id клиента
     * @return лист проекций.
     */
    @Override
    @Transactional
    public List<AccountsPayProjection> viewAccountsPay(UUID clientId) {
        List<AccountsPayProjection> projectionList = accountRepository.findAccountsByClientId(
            clientId);

        if (projectionList == null || projectionList.isEmpty()) {
            throw new NotFoundException(ACCOUNTS_NOT_FOUND_BY_CLIENT_ID.getDescription()
                .formatted(clientId));
        }

        return projectionList;
    }
}