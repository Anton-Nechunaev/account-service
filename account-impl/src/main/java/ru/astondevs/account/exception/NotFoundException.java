package ru.astondevs.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое при отсутствии запрошенного ресурса.
 * <p>
 * Это подкласс RuntimeException и может использоваться для обработки ситуаций, когда не удается найти запрошенный
 * ресурс или объект.
 *
 * @author Alexander Azaronak
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Создает новый объект исключения с заданным сообщением.
     *
     * @param message сообщение об ошибке
     */
    public NotFoundException(String message) {
        super(message);
    }
}