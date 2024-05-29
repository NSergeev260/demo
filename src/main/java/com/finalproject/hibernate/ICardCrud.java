package com.finalproject.hibernate;

import com.finalproject.card.ICard;

import java.math.BigDecimal;
import java.util.List;

public interface ICardCrud {

    boolean insertCard(ICard card);

    List<ICard> getCards();

    ICard getCard(String cardId);

    boolean updateCard(BigDecimal balance, boolean isBlocked, String documentId, String cardId);

    boolean deleteCard(String cardId);


}