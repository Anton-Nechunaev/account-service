package ru.astondevs.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;
import ru.astondevs.account.dto.respones.AccountDetailsResponse;
import ru.astondevs.account.exception.handler.ErrorResponseDto;
import ru.astondevs.account.repository.projection.AccountsPayProjection;
import ru.astondevs.account.service.AccountService;
import java.util.UUID;

/**
 * Контроллер для работы со счётом.
 * <p>
 * Предоставляет конечные точки для получения информации о счёте.
 *
 * @author Alexander Azaronak
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("auth/account")
@Tag(name = "Счёт", description = "Предоставляет конечные точки для работы со счетами")
public class AccountController {

    private final AccountService accountService;

    /**
     * Просмотр реквизитов счёта.
     */
    @GetMapping("/client-accounts/account-details")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "AS - 4.2 Просмотр реквизитов счета")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Реквизиты счёта"),
        @ApiResponse(responseCode = "401", description = "Проблема с авторизацией",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
        @ApiResponse(responseCode = "404", description = "Некорректный запрос",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
        @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
    })
    public AccountDetailsResponse viewAccountDetails(@RequestParam UUID accountId) {
        return accountService.viewAccountDetails(accountId);
    }

    /**
     * Просмотр все счетов клиента.
     */
    @GetMapping("/client-accounts/pay-accounts")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "AS - 4.4 Просмотр списка доступных счетов")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список доступных счетов"),
        @ApiResponse(responseCode = "401", description = "Проблема с авторизацией",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
        @ApiResponse(responseCode = "404", description = "Нет доступных счетов",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
        @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
    })
    public List<AccountsPayProjection> viewAccountsPay(@RequestParam UUID clientId) {
        return accountService.viewAccountsPay(clientId);
    }
}