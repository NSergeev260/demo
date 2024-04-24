package com.finalproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Controller
public class ControllerTerminal {

    private String cost;
    private String cardId;
    private BigDecimal balance;

    public void getCardId() {
        cardId = UUID.randomUUID().toString() + "C";
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
        log.info("Your balance is " + getBalance());
        return balance;
    }
}
