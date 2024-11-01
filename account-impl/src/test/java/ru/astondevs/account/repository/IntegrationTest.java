package ru.astondevs.account.repository;

import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@DirtiesContext
@Testcontainers
@ActiveProfiles("test")
@Sql(scripts = "/test.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class IntegrationTest {

    @Container
    @ServiceConnection(type = JdbcConnectionDetails.class)
    public static final PostgreSQLContainer<?> postgreSqlContainer =
        new PostgreSQLContainer<>("postgres:16.3-alpine")
            .withUrlParam("stringtype", "unspecified");
}
