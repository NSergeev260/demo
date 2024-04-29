package com.finalproject.controllers;

import java.math.BigDecimal;
import com.finalproject.services.PayingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class ControllerTerminal {

    private PayingService payingService;

    @PostMapping("/pay")
    public String pay(String cardId, BigDecimal cost) {
        String payMoney = String.valueOf(payingService.payMoney(cardId, cost));
        return payMoney;
    }

    @PostMapping("/put")
    public String put(String cardId, BigDecimal money) {
            String putMoney = String.valueOf(payingService.putMoney(cardId, money));
            return putMoney;
    }

    @GetMapping("/balance/{cardId}")
    public String balanceOfMoney(@PathVariable String cardId) {
            String balance = String.valueOf(payingService.getBalance(cardId));
            return balance;
    }
}
