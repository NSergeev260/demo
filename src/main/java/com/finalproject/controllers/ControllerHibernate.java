package com.finalproject.controllers;

import com.finalproject.MockData;
import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.hibernate.CrudMethodsCardHibernate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class ControllerHibernate {

    private CrudMethodsCardHibernate crudMethodsCardHibernate;
    private MockData mockData;

    @PostMapping("/insertTest")
    public String insert() {
        ICard card = mockData.getRandomCard();
        crudMethodsCardHibernate.insertCard(card);
        log.info("Card was created: {}", card);
        return "Card was created" + card;
    }

    @GetMapping("/getCards")
    public List<String> getCards() {
        List<String> cardsId = new ArrayList<>();

        for (int i = 0; i < crudMethodsCardHibernate.getCards().size(); i++) {
            cardsId.add(crudMethodsCardHibernate.getCards().get(i).getCardId());
        }
        log.info("Сards ID have been received: {}", cardsId);
        return cardsId;
    }

    @GetMapping("/getCard")
    public String getCard(String cardId) {
        crudMethodsCardHibernate.getCard(cardId);
        log.info("The card with id {} has been received", cardId);
        return "The card has been received: " + cardId;
    }

    @PostMapping("/update")
    public String updateCard(String cardId, BigDecimal balance, CardType cardType,
                             boolean isBlocked, String documentId) {
        ICard card;

        if (cardType == CardType.CREDIT) {
            card = new CreditCard(cardId, balance, isBlocked, documentId);
        } else {
            card = new DebitCard(cardId, balance, isBlocked);
        }

        crudMethodsCardHibernate.updateCard(card);
        log.info("Card was updated {}", card);
        return "Card was updated: " + card;
    }

    @PostMapping("/delete")
    public String deleteCard(String cardId) {
        crudMethodsCardHibernate.deleteCard(cardId);
        log.info("Card with id {} was deleted", cardId);
        return "Card was deleted: " + cardId;
    }
}