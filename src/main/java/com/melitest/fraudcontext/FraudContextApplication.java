package com.melitest.fraudcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FraudContextApplication {

  public static void main(String[] args) {
    SpringApplication.run(FraudContextApplication.class, args);
  }

}
