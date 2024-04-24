package com.finalproject;

import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.UsageCard;

import java.util.ArrayList;
import java.util.List;

public class CardService {

    Service service;
    CreditCard creditCard;
    DebitCard debitCard;
    List<UsageCard> creditCardList;
    List<UsageCard> debitCardList;

    public List<UsageCard> addToListCreditCard() {
        creditCardList.add(new CreditCard());
        return creditCardList;
    }

    public List<UsageCard> addToListDebitCard() {
        debitCardList.add(new DebitCard());
        return debitCardList;
    }

    public List<UsageCard> findCardById(UsageCard card, String cardId) {
        List<UsageCard> listOfCardId = new ArrayList<>();
        listOfCardId.stream()
            .filter(x -> x.getCardId().contains(cardId))
            .forEach(System.out::println);
        return listOfCardId;
    }

    public boolean isBlockedCard(UsageCard card) {
        service.setCardActive(false);
        return service.isCardActive();
    }
}
