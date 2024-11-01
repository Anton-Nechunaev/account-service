package ru.astondevs.account.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Перечисление, представляющее различные статусы счёта.
 * <p>
 * Включает три статуса: активный, замороженный, закрытый.
 * <p>
 * Каждый элемент перечисления имеет свое описание, которое указывает на статус счёта.
 *
 * @author Alexander Azarornak
 */
@AllArgsConstructor
@Getter
public enum AccountStatus {
    ACTIVE("Активный"),
    CLOSED("Замороженный"),
    FROZEN("Закрытый");

    private final String description;
}