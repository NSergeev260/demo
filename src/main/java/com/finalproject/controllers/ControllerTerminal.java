package com.finalproject.controllers;

import com.finalproject.services.CardService;
import com.finalproject.card.ICard;

import java.math.BigDecimal;
import java.util.Optional;

import com.finalproject.services.PayingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

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
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if(cardById.isPresent()) {
            ICard card = cardById.get();
            card.setBalance(payingService.putMoney(money));
//            String putMoney = String.valueOf(payingService.putMoney(money));
            log.info("You put: " + money);
            return card.toString();
        }
        return "Wrong card Id " + cardId;
    }

    @GetMapping("/balance/{cardId}")
    public String getBalance(@PathVariable String cardId) {
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            BigDecimal balance = cardById.get().getBalance();
            log.info("Your balance is " + balance);
            return balance.toString();
        }
        return "No card with id " + cardId;
    }
}
