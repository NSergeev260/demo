package com.finalproject.hibernate;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.crudmethods.ICardCrud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class CrudMethodsCardHibernate implements ICardCrud {

    private final CardRepository cardRepository;

    public CrudMethodsCardHibernate(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

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
        List<ICard> cards = cardsList.stream()
            .map(this::toICard)
            .toList();
        return cards;
    }

    @Override
    public ICard getCard(String cardId) {
        CardEntity cardEntity;
        ICard card = null;
        Optional<CardEntity> cardById = cardRepository.findById(UUID.fromString(cardId));

        if (cardById.isPresent()) {
            cardEntity = cardById.get();
            card = toICard(cardEntity);
        }

        return card;
    }

    @Override
    public int updateCard(String cardId, BigDecimal balance, boolean isBlocked, String documentId) {
        return cardRepository.updateCard(UUID.fromString(cardId),
            balance, isBlocked, documentId);
    }

    @Override
    public boolean deleteCard(String cardId) {
        cardRepository.deleteById(UUID.fromString(cardId));
        return true;
    }

    private ICard toICard(CardEntity cardEntity) {
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
}