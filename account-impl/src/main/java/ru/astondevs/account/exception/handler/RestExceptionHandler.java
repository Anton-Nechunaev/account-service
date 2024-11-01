package ru.astondevs.account.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.astondevs.account.exception.InternalServerException;
import ru.astondevs.account.exception.NotFoundException;
import ru.astondevs.account.exception.UnauthorizedException;

/**
 * Глобальный обработчик исключений для REST-контроллеров.
 * <p>
 * Обрабатывает исключения, возникающие в контроллерах, и возвращает соответствующие ответы клиенту.
 *
 * @author Pavel Kazhamiakin
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Обрабатывает исключение NotFoundException и возвращает HTTP-ответ с кодом 404 Not Found.
     *
     * @param e исключение NotFoundException
     * @return ответ клиенту с сообщением об ошибке и кодом состояния 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto handleNotFoundException(RuntimeException e) {
        return new ErrorResponseDto(e.getMessage());
    }

    /**
     * Обрабатывает исключение NotServerError и возвращает HTTP-ответ с кодом 500 Not Found.
     *
     * @param e исключение NotFoundException
     * @return ответ клиенту с сообщением об ошибке и кодом состояния 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public ErrorResponseDto handleInternalServerException(RuntimeException e) {
        return new ErrorResponseDto(e.getMessage());
    }

    /**
     * Обрабатывает исключение Unauthorized и возвращает HTTP-ответ с кодом 401 Not Found.
     *
     * @param e исключение NotFoundException
     * @return ответ клиенту с сообщением об ошибке и кодом состояния 401
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponseDto handleUnauthorized(RuntimeException e) {
        return new ErrorResponseDto(e.getMessage());
    }
}