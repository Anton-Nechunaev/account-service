package ru.astondevs.account.exception.handler;

/**
 * DTO для представления сообщения об ошибке.
 * <p>
 * Используется для передачи сообщений об ошибках от сервисов или контроллеров.
 *
 * @author Alexander Azaronak
 */
public record ErrorResponseDto(String errorMessage) {}