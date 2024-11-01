package ru.astondevs.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое при отсутствии авторизации.
 * <p>
 * Это подкласс RuntimeException и может использоваться для обработки ситуаций, когда пользователь не авторизован.
 *
 * @author Alexander Azaronak
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{
    /**
     * Создает новый объект исключения с заданным сообщением.
     *
     * @param message сообщение об ошибке
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}