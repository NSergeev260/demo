package com.finalproject.controllers;

import java.math.BigDecimal;

import com.finalproject.card.CardType;
import com.finalproject.card.ICard;
import com.finalproject.services.PayingService;
import com.finalproject.transport.Transport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class ControllerTerminal {

    private PayingService payingService;

    @PostMapping("/pay")
    public String pay(String cardId, Transport typeOfTransport, String terminalId) {
        return payingService.payMoney(cardId, typeOfTransport, terminalId);
    }

    @PostMapping("/put")
    public String put(String cardId, BigDecimal money, String terminalId) {
        return payingService.putMoney(cardId, money, terminalId);
    }

    @GetMapping("/balance/{cardId}")
    public String balanceOfMoney(@PathVariable String cardId, String terminalId) {
        return payingService.getBalance(cardId, terminalId);
    }

    @GetMapping("/getInfo")
    public ICard getInfo(String cardId) {
        return payingService.getCardInfo(cardId);
    }

    @PostMapping("/activate")
    public String activateCard(CardType cardType, String terminalId) {
        return payingService.insertNewCard(cardType, terminalId);
    }
}
