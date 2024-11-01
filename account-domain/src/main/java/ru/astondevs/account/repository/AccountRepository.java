package ru.astondevs.account.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.astondevs.account.model.Account;

import java.util.UUID;
import ru.astondevs.account.repository.projection.AccountsPayProjection;

public interface AccountRepository extends JpaRepository<Account, UUID> {



    @Query("""
        SELECT new ru.astondevs.account.repository.projection.AccountsPayProjection
         (a.accountId, ad.accountNumber, a.currentBalance, c.currencyType )
               FROM Account a
               JOIN a.accountDetails ad
               JOIN a.currency c
               WHERE a.clientId = :clientId
        """)
    List<AccountsPayProjection> findAccountsByClientId(UUID clientId);
}