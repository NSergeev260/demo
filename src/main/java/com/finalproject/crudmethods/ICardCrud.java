package com.finalproject.crudmethods;

import com.finalproject.card.ICard;

import java.math.BigDecimal;
import java.util.List;

public interface ICardCrud {

    boolean insertCard(ICard card);

    List<ICard> getCards();

    ICard getCard(String cardId);

    int updateCard(String cardId, BigDecimal balance, boolean isBlocked,
                   String documentId);

    boolean deleteCard(String cardId);

}