package com.finalproject.controllers;

import com.finalproject.MockData;
import com.finalproject.card.ICard;
import com.finalproject.hibernate.CrudMethodCardHibernate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@AllArgsConstructor
public class ControllerHibernate {

    private CrudMethodCardHibernate crudMethodCardHibernate;
    private MockData mockData;

    @PostMapping("/insertTest")
    public String insert() {
        ICard card = mockData.getRandomCard();
        crudMethodCardHibernate.insertCard(card);
        log.info("Card was created: {}", card);
        return "Card was created" + card;
    }

    @GetMapping("/getCards")
    public String getCards() {
        crudMethodCardHibernate.getCards();
        log.info("Сards have been received: {}", crudMethodCardHibernate.getCards().toString());
        return "Сards have been received";
    }

    @GetMapping("/getCard")
    public String getCard(String cardId) {
        crudMethodCardHibernate.getCard(cardId);
        log.info("The card with id {} has been received", cardId);
        return "The card has been received: " + cardId;
    }

    @PostMapping("/update")
    public String updateCard(String cardId, BigDecimal balance, boolean isBlocked, String documentId) {
        crudMethodCardHibernate.updateCard(cardId, balance, isBlocked, documentId);
        log.info("Card with id {} was updated", cardId);
        return "Card was updated: " + cardId;
    }

    @PostMapping("/delete")
    public String deleteCard(String cardId) {
        crudMethodCardHibernate.deleteCard(cardId);
        log.info("Card with id {} was deleted", cardId);
        return "Card was deleted: " + cardId;
    }
}