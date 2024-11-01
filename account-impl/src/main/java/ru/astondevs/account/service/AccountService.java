package ru.astondevs.account.service;

import java.util.List;
import ru.astondevs.account.dto.respones.AccountDetailsResponse;
import java.util.UUID;
import ru.astondevs.account.repository.projection.AccountsPayProjection;

/**
 * Сервис для работы со счётом
 *
 * @author Alexander Azaronak
 */
public interface AccountService {

    /**
     * Возвращает реквизиты счёта.
     *
     * @param accountId идентификатор счёта.
     */
    AccountDetailsResponse viewAccountDetails(UUID accountId);

    /**
     * Возвращает dto со списком счетов
     * @param clientId id клиента
     * @return
     */
    List<AccountsPayProjection> viewAccountsPay(UUID clientId);
}