package com.finalproject.controllers;

import com.finalproject.transport.Transport;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ControllerAdmin {

//    public void getCardId() {
//        cardID = UUID.randomUUID().toString();
//    }
//
//    @PostMapping("/pay")
//    public String pay(String cardId, String terminalId, Transport transport) {
//        log.info("You pay successful: " + cost);
//        return cost;
//    }
//
//    @PostMapping("/put")
//    public BigDecimal putMoney(BigDecimal money) {
//        log.info("You put: " + money);
//        return money;
//    }
//
//    @GetMapping("/balance")
//    public BigDecimal getBalance() {
//        log.info("Your balance is " + balance);
//        return balance;
//    }
}
