package com.finalproject.controllers;

import com.finalproject.MockData;
import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.hibernate.CrudMethodsCardHibernate;
import com.finalproject.services.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class ControllerAdmin {

    private CardService cardService;
    private MockData mockData;

    @PostMapping("/block")
    public String block(String cardId, String terminalId) {
        return cardService.block(cardId, terminalId);
    }

    @PostMapping("/unblock")
    public String unblock(String cardId, String terminalId) {
        return cardService.unblock(cardId, terminalId);
    }

    @GetMapping("/isBlocked")
    public String isBlocked(String cardId) {
        return cardService.isBlocked(cardId);
    }

    @PostMapping("/generateMockData")
    public String generate(long numberOfRecords) {
        mockData.generateMockData(numberOfRecords);
        return "Cards was generated: " + numberOfRecords;
    }

    @GetMapping("/getCard")
    public String getCard(String cardId) {
        return String.valueOf(cardService.findCardById(cardId));
    }

    @GetMapping("/getCards")
    public List<String> getCards() {
        return cardService.getAllCards();
    }

    @PostMapping("/update")
    public String updateCard(String cardId, BigDecimal balance,
                             boolean isBlocked, String documentId, String terminalId) {

        return String.valueOf(cardService.updateCard(cardId, balance,
            isBlocked, documentId, terminalId));
    }

    @PostMapping("/delete")
    public String deleteCard(String cardId) {
        return String.valueOf(cardService.deleteCard(cardId));
    }
}
