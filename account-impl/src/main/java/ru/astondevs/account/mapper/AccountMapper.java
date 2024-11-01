package ru.astondevs.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.astondevs.account.dto.respones.AccountDetailsResponse;
import ru.astondevs.account.model.Account;

/**
 * Маппер для преобразования объектов Account в Dto и наоборот
 *
 * @author Alexander Azaronak
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    /**
     * Возвращает рекизиты счёта.
     *
     * @param account объект Account
     * @return AccountDetailsResponse
     */
    @Mapping(source = "currency.currencyType", target = "currencyType")
    AccountDetailsResponse toAccountDetailsResponse(Account account);
}