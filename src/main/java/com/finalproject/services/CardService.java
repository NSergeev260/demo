package com.finalproject.services;

import com.finalproject.card.ICard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CardService {

    private final List<ICard> cards = new ArrayList<>();

    public void addCard(ICard card) {
        cards.add(card);
    }

    public Optional<ICard> findCardById(String cardId) {
        return cards.stream()
            .filter(x -> x.getCardId().equals(cardId))
            .findFirst();
    }

    public String block(String cardId) {
        Optional<ICard> cardById = findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.block();
            log.info("Blocking card transactions: {}, Time: {}", cardId, LocalDateTime.now());
            return "true";
        }
        log.info("Check cardId, please");
        return "Check cardId, please";
    }

    public String unblock(String cardId) {
        Optional<ICard> cardById = findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.unblock();
            log.info("Unblocking card transactions: {}, Time: {}", cardId, LocalDateTime.now());
            return "false";
        }
        log.info("Check cardId, please");
        return "Check cardId, please";
    }

    public String isBlocked(String cardId) {
        Optional<ICard> cardById = findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            log.info("Is card blocked? {}, Time: {}", cardId, LocalDateTime.now());
            return String.valueOf(card.isBlocked());
        }
        log.info("Check cardId, please");
        return "Check cardId, please";
    }
}
