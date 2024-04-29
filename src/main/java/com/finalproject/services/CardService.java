package com.finalproject.services;

import com.finalproject.card.ICard;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CardService {

    private PayingService payingService;
    private final List<ICard> cards = new ArrayList<>();

    public void addCard(ICard card) {
        cards.add(card);
    }

    public Optional<ICard> findCardById(String cardId) {
        return cards.stream()
            .filter(x -> x.getCardId().equals(cardId))
            .findFirst();
    }
}
