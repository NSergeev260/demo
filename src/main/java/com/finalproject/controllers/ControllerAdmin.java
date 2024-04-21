package com.finalproject.controllers;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@Log4j2
@Controller
public class ControllerAdmin {

    private String cost;
    private String cardID;
    private BigDecimal balance;

    public void getCardId() {
        cardID = UUID.randomUUID().toString();
    }

    @PostMapping("/pay")
    public String pay() {
        log.info("You pay successful: " + cost);
        return cost;
    }

    @PostMapping("/put")
    public BigDecimal putMoney(BigDecimal money) {
        log.info("You put: " + money);
        return money;
    }

    @GetMapping("/balance")
    public BigDecimal getBalance() {
        log.info("Your balance is " + balance);
        return balance;
    }
}
