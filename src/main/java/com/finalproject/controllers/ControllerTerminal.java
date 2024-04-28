package com.finalproject.controllers;

import com.finalproject.services.CardService;
import com.finalproject.card.ICard;
import java.math.BigDecimal;
import java.util.Optional;
import com.finalproject.services.PayingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@ComponentScan("com.finalproject")
public class ControllerTerminal {

    private CardService cardService;
    private PayingService payingService;

    @PostMapping("/pay")
    public String pay(String cardId, BigDecimal cost) {
        log.info("You pay successful: " + cost);
        String payMoney = String.valueOf(payingService.pay(String.valueOf(cardService.findCardById(cardId)), cost));
        return payMoney;
    }

    @PostMapping("/put")
    public String putMoney(String cardId, BigDecimal money) {
        log.info("You put: " + money);
        String putMoney = String.valueOf(payingService.putMoney(String.valueOf(cardService.findCardById(cardId)), money));
        return putMoney;
    }

//    @GetMapping("/balance/{cardId}")
//    public String getBalance(@PathVariable String cardId) {
    @GetMapping("/balance")
    public String getBalance(String cardId) {
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            BigDecimal balance = cardById.get().getBalance();
            log.info("Your balance is " + balance);
            return balance.toString();
        }
        return "No card with id " + cardId;
    }
}
