package ru.astondevs.account.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionType {
    REPLENISH("Пополнение"),
    WITHDRAWAL("Перевод"),
    TRANSFER("Трансфер");

    private final String description;
}