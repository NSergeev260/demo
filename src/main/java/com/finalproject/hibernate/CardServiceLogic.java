package com.finalproject.hibernate;

import com.finalproject.card.ICard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceLogic implements CardServiceHibernate {
    @Autowired
    CardRepository cardRepository;

    @Override
    public boolean insertCard(ICard card) {
        cardRepository.save(card);
        return true;
    }

    @Override
    public List<ICard> getCards() {
        return cardRepository.findAll();
    }

    @Override
    public ICard getCard(String cardId) {
        Optional<ICard> card = cardRepository.findById(cardId);
        if (card.isPresent()) {
            return card.get();
        } else {
            return null;
        }
    }

//    @Override
//    public boolean updateCard(String cardId) {
//        cardRepository.findById(cardId);
//
//        return cardRepository.updateCard();
//    }

    @Override
    public boolean deleteCard(String cardId) {
        cardRepository.deleteById(cardId);
        return true;
    }
}