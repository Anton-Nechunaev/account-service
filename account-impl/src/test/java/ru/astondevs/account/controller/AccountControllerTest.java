package ru.astondevs.account.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.astondevs.account.util.ExceptionMessage.INTERNAL_SERVER_ERROR;
import static ru.astondevs.account.util.ExceptionMessage.UNAUTHORIZED;
import static ru.astondevs.account.util.ExceptionMessage.ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.astondevs.account.dto.respones.AccountDetailsResponse;
import ru.astondevs.account.exception.InternalServerException;
import ru.astondevs.account.exception.NotFoundException;
import ru.astondevs.account.exception.UnauthorizedException;
import ru.astondevs.account.repository.projection.AccountsPayProjection;
import ru.astondevs.account.service.AccountService;
import ru.astondevs.account.util.ConstantUtil;
import java.util.UUID;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    private static UUID accountId;
    private static UUID clientId;
    private static AccountDetailsResponse.AccountDetails accountDetails;
    private static AccountsPayProjection accountsProjection;
    private static AccountDetailsResponse accountDetailsResponseValid;

    @BeforeAll
    public static void initDto() {
        accountId = UUID.randomUUID();
        clientId = UUID.randomUUID();
        accountDetails = AccountDetailsResponse.AccountDetails.builder()
            .bikBank(ConstantUtil.BIK_BANK)
            .innBank(ConstantUtil.INN_BANK)
            .accountNumber(ConstantUtil.ACCOUNT_NUMBER)
            .corAccountBank(ConstantUtil.COR_ACCOUNT_BANK)
            .kppBank(ConstantUtil.KPP_BANK)
            .bankName(ConstantUtil.BANK_NAME)
            .build();
        accountDetailsResponseValid = AccountDetailsResponse.builder()
            .accountId(accountId)
            .clientId(clientId)
            .accountType(ConstantUtil.ACCOUNT_TYPE.getDescription())
            .currencyType(ConstantUtil.CURRENCY_TYPE.getDescription())
            .accountDetails(accountDetails)
            .build();
        accountsProjection = AccountsPayProjection.builder()
            .accountId(accountId)
            .accountNumber(ConstantUtil.ACCOUNT_NUMBER)
            .balance(ConstantUtil.ACCOUNT_BALANCE)
            .currency(ConstantUtil.CURRENCY_TYPE)
            .build();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return 200 and account details by account id")
    void viewAccountDetailsSuccessfully() {
        String expect = objectMapper.writeValueAsString(accountDetailsResponseValid);

        when(accountService.viewAccountDetails(accountId)).thenReturn(accountDetailsResponseValid);

        MvcResult mvcResult = mockMvc.perform(get(ConstantUtil.URL_VIEW_ACCOUNT_DETAILS)
                .param("accountId", String.valueOf(accountId)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        String actual = mvcResult.getResponse().getContentAsString(UTF_8);

        assertEquals(expect, actual);
        verify(accountService, times(1)).viewAccountDetails(accountId);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return 401 unauthorized")
    void viewAccountDetailsUnauthorized() {
        String description = UNAUTHORIZED.getDescription();

        doThrow(new UnauthorizedException(description))
            .when(accountService).viewAccountDetails(accountId);

        mockMvc.perform(get(ConstantUtil.URL_VIEW_ACCOUNT_DETAILS)
                .param("accountId", String.valueOf(accountId)))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.errorMessage").value(description))
            .andReturn();

        verify(accountService, times(1)).viewAccountDetails(accountId);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return 404 not fount exception")
    void viewAccountDetailsNotFoundException() {
        String description = ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID.getDescription();

        when(accountService.viewAccountDetails(accountId)).thenThrow(
            new NotFoundException(description));

        mockMvc.perform(get(ConstantUtil.URL_VIEW_ACCOUNT_DETAILS)
                .param("accountId", String.valueOf(accountId)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errorMessage").value(description))
            .andReturn();

        verify(accountService, times(1)).viewAccountDetails(accountId);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return 500 Internal server error")
    void viewAccountDetailsInternalError() {
        String description = INTERNAL_SERVER_ERROR.getDescription();

        doThrow(new InternalServerException(description))
            .when(accountService).viewAccountDetails(accountId);

        mockMvc.perform(get(ConstantUtil.URL_VIEW_ACCOUNT_DETAILS)
                .param("accountId", String.valueOf(accountId)))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.errorMessage").value(description))
            .andReturn();

        verify(accountService, times(1)).viewAccountDetails(accountId);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return 200 and list of accounts")
    void viewAccountsSuccessfully() {
        when(accountService.viewAccountsPay(clientId)).thenReturn(List.of(accountsProjection));

        MvcResult mvcResult = mockMvc.perform(get(ConstantUtil.URL_VIEW_ACCOUNTS_PAY)
                .param("clientId", String.valueOf(clientId)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<AccountsPayProjection> actual
            = Arrays.asList(mapper.readValue(responseBody, AccountsPayProjection[].class));

        assertEquals(List.of(accountsProjection), actual);
        verify(accountService, times(1)).viewAccountsPay(clientId);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return 401 unauthorized")
    void viewAccountsPayUnauthorized() {
        String description = UNAUTHORIZED.getDescription();

        doThrow(new UnauthorizedException(description))
            .when(accountService).viewAccountsPay(clientId);

        mockMvc.perform(get(ConstantUtil.URL_VIEW_ACCOUNTS_PAY)
                .param("clientId", String.valueOf(clientId)))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.errorMessage").value(description))
            .andReturn();

        verify(accountService, times(1)).viewAccountsPay(clientId);
    }

    @Test
    @SneakyThrows
    @DisplayName("It is necessary to return 404 undisclosed exceptions instead of receiving accountPay")
    void viewAccountsPayNotFoundException() {
        String description = ACCOUNT_NOT_FOUND_BY_ACCOUNT_ID.getDescription();

        when(accountService.viewAccountsPay(clientId)).thenThrow(
            new NotFoundException(description));

        mockMvc.perform(get(ConstantUtil.URL_VIEW_ACCOUNTS_PAY)
                .param("clientId", String.valueOf(clientId)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errorMessage").value(description))
            .andReturn();

        verify(accountService, times(1)).viewAccountsPay(clientId);
    }

    @Test
    @SneakyThrows
    @DisplayName("Internal server error when trying to get accountPay")
    void viewAccountsPayIsInternalServerError() {
        String description = INTERNAL_SERVER_ERROR.getDescription();

        when(accountService.viewAccountsPay(clientId)).thenThrow(
            new InternalServerException(description));

        mockMvc.perform(get(ConstantUtil.URL_VIEW_ACCOUNTS_PAY)
                .param("clientId", String.valueOf(clientId)))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.errorMessage").value(description))
            .andReturn();

        verify(accountService, times(1)).viewAccountsPay(clientId);
    }
}