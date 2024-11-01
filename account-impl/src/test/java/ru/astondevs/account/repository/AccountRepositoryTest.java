package ru.astondevs.account.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.astondevs.account.repository.projection.AccountsPayProjection;
import ru.astondevs.account.util.ConstantUtil;

public class AccountRepositoryTest extends IntegrationTest {

    @Autowired
    AccountRepository accountRepository;

    private static UUID clientId;

    @BeforeAll
    public static void init() {
        clientId = ConstantUtil.VALID_CLIENT_ID;
    }

    @Test
    @DisplayName("Successful verification of the customer's accounts")
    void accountPayIsFound() {
        List<AccountsPayProjection> list = accountRepository.findAccountsByClientId(clientId);

        assertNotNull(list);
        assertFalse(list.isEmpty());
    }
    @Test
    @DisplayName("Checking for the absence of accounts from a non-existent client")
    void accountPayIsNotFound() {
        clientId=UUID.randomUUID();
        List<AccountsPayProjection> list = accountRepository.findAccountsByClientId(clientId);

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }
}
