package com.finalproject.hibernate;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
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
        CardEntity cardEntity = new CardEntity(card.getCardId(), card.getBalance(),
            card.getType(), card.isBlocked(), null);

        if (card.getType().equals(CardType.CREDIT)) {
            cardEntity.setDocumentId(((CreditCard) card).getDocumentId());
        }
        cardRepository.save(cardEntity);
        return true;
    }

    @Override
    public List<ICard> getCards() {
        List<CardEntity> cardsList = cardRepository.findAll();
        List<ICard> cards = cardsList.stream().map(x -> toICard(x)).toList();
        return cards;
    }

    public ICard toICard(CardEntity cardEntity) {
        ICard card;
        if (cardEntity.getCardType().equals(CardType.CREDIT)) {
            card = new CreditCard(cardEntity.getCardId(), cardEntity.getBalance(),
                cardEntity.isBlocked(), cardEntity.getDocumentId());
        } else {
            card = new DebitCard(cardEntity.getCardId(), cardEntity.getBalance(),
                cardEntity.isBlocked());
        }
        return card;
    }

//    @Override
//    public ICard getCard(String cardId) {
//        Optional<ICard> card = cardRepository.findById(cardId);
//        if (card.isPresent()) {
//            return card.get();
//        } else {
//            return null;
//        }
//    }

//    public boolean chooseCardForUpdate(String cardId) {
//        Optional<ICard> card = cardRepository.findById(cardId);
//        if (card.isPresent()) {
//            ICard newCard = card.get();
//            cardRepository.updateCard(cardId, newCard.setBalance(), isBlocked, documentId);
//            return true;
//        } else {
//            return false;
//        }
//    }

//    @Override
//    public boolean deleteCard(String cardId) {
//        cardRepository.deleteById(cardId);
//        return true;
//    }
}