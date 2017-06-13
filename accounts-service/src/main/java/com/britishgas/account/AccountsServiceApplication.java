package com.britishgas.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountsServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsServiceApplication.class, args);
  }
}

@RestController
class AccountsRestController {

  @RequestMapping("/accounts/{accountNumber}")
  public String byNumber(@PathVariable("accountNumber") String accountNumber) {
    return "Account " + accountNumber + " information is returned";
  }
}
