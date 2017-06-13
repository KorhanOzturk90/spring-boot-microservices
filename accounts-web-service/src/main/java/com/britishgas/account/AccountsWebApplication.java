package com.britishgas.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class AccountsWebApplication {

  public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-SERVICE";

  public static void main(String[] args) {
    SpringApplication.run(AccountsWebApplication.class, args);
  }

  @LoadBalanced
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public AccountsService accountsService() {
    return new AccountsService(ACCOUNTS_SERVICE_URL);
  }

  @Bean
  public AccountsController accountsController() {
    return new AccountsController(accountsService());
  }
}

@RestController
class AccountsController {

  @Autowired
  protected AccountsService accountsService;

  protected Logger logger = Logger.getLogger(AccountsController.class.getName());

  public AccountsController(AccountsService accountsService) {
    this.accountsService = accountsService;
  }

  @RequestMapping(value = "/accounts/{accountNumber}", produces = "application/json; charset=UTF-8")
  public String byNumber(@PathVariable("accountNumber") String accountNumber) throws JsonProcessingException {

    String account = accountsService.getByNumber(accountNumber);
    logger.info("account byNumber() found: " + account);
    return new ObjectMapper().writeValueAsString("account");
  }
}
