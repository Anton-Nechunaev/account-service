package ru.astondevs.account.dto.respones;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.UUID;

/**
 * DTO которое содержит реквизиты счёта.
 *
 * @author Alexander Azaronak
 */
@Builder
@Schema(description = "Реквизита счёта.")
public record AccountDetailsResponse(
    @Schema(description = "Идентификатор счёта", example = "311e4567-e89b-12d3-a456-426614174015")
    UUID accountId,

    @Schema(description = "Тип счёта", example = "CURRENT")
    String accountType,

    @Schema(description = "Тип валюты", example = "RUB")
    String currencyType,

    @Schema(description = "Идентификатор клиента", example = "222e4567-e89b-12d3-a456-426614174001")
    UUID clientId,

    @JsonUnwrapped
    AccountDetails accountDetails
) {
    @Builder
    public record AccountDetails(
        @Schema(description = "Номер счёта", example = "3214344rgerg231231ffwefewFWE")
        String accountNumber,
        @Schema(description = "Банковский идентификационный код", example = "044525970")
        String bikBank,
        @Schema(description = "Корреспондентский счет банка", example = "30101810400000000970")
        String corAccountBank,
        @Schema(description = "Название банка", example = "Active-Bank")
        String bankName,
        @Schema(description = "ИНН банка", example = "7702070117")
        String innBank,
        @Schema(description = "Код причины постановки", example = "784253001")
        String kppBank
    ) { }
}
