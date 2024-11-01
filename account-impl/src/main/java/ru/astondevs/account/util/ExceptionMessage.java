package ru.astondevs.account.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Перечисление ExceptionMessage предоставляет сообщения об исключениях, используемые в приложении
 * для обработки различных ошибок.
 *
 * @author Alexander Azaronak
 */
@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID("Карта с account id %s не найдена." ),
    UNAUTHORIZED("Вы не авторизированы."),
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера."),
    ACCOUNTS_NOT_FOUND_BY_CLIENT_ID("Нет доступных счетов по client id  %s.");

    private final String description;
}