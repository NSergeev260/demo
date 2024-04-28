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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@ComponentScan("com.finalproject")
public class ControllerTerminal {

    private CardService cardService;
    private PayingService payingService;
    ICard card;

    @PostMapping("/pay")
    public String pay(BigDecimal cost) {
        log.info("You pay successful: " + cost);
        String payMoney = String.valueOf(payingService.pay(card.getCardId(), cost));
        return payMoney;
    }

    @PostMapping("/put")
    public String putMoney(BigDecimal money) {
        log.info("You put: " + money);
        String putMoney = String.valueOf(payingService.putMoney(card.getCardId(), money));
        return putMoney;
    }

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
