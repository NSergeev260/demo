package com.finalproject.controllers;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@Log4j2
@RestController
@Getter
public class ControllerAdmin {

    private String cardID;
    private boolean cutOffBankDept;
    private BigDecimal balance;
    Enum TransportCost;

    public void cardID() {
        cardID = UUID.randomUUID().toString();
    }


    public BigDecimal pay(BigDecimal cost) {
        balance = new BigDecimal(String.valueOf(getBalance())).subtract(cost);
        log.info("You pay successful: " + cost);
        log.info("Your balance is : " + displayBalance());
        return balance;
    }


    public BigDecimal putMoney(BigDecimal money) {
        balance = new BigDecimal(String.valueOf(getBalance())).add(money);
        log.info("You put: " + money);
        log.info("Your balance is : " + displayBalance());
        return balance;
    }


    public BigDecimal displayBalance() {
        log.info("Your balance is " + getBalance());
        return getBalance();
    }
}
