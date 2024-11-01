package ru.astondevs.account.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Перечисление, представляющее различные коды валют.
 * <p>
 * Включает два кода валюты: код рубля для проведения международных операций, код рубля для проведения платежей внутри страны.
 * <p>
 * Каждый элемент перечисления имеет свое описание, которое указывает на код валюты.
 *
 * @author Alexander Azarornak
 */
@AllArgsConstructor
@Getter
public enum Code {
    RUB(810),
    OKB(643);

    private final Integer description;
}