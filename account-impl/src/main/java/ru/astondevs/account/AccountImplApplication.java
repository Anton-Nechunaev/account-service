package ru.astondevs.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Микросервис для работы со счетами пользователей "active-bank"
 *
 * @author Alexander Azaronak
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AccountImplApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountImplApplication.class, args);
    }
}