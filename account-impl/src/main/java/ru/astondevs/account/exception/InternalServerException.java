package ru.astondevs.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое в случае ошибки на стороне сервера.
 * <p>
 * Это подкласс RuntimeException и может использоваться для обработки ситуаций, когда
 * запрос не выполнен в результате ошибки сервера.
 *
 * @author Alexander Azaronak
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException{

    /**
     * Создает новый объект исключения с заданным сообщением.
     *
     * @param message сообщение об ошибке
     */
    public InternalServerException(String message) {
        super(message);
    }
}