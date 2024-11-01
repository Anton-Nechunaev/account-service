package ru.astondevs.account.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Перечисление, представляющее различные типы счёта.
 * <p>
 * Включает четыре типа: текущий, сберегательный, расчетный, депозитный.
 * <p>
 * Каждый элемент перечисления имеет свое описание, которое указывает на тип счёта.
 *
 * @author Alexander Azarornak
 */
@AllArgsConstructor
@Getter
public enum AccountType {
    CURRENT("Текущий счет"),
    SAVING("Сберегательный счет"),
    CHЕCKING("Расчетный счет"),
    DEPOSIT("Депозитный счет");

    private final String description;
}