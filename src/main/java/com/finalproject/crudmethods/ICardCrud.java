package com.finalproject.crudmethods;

import com.finalproject.card.ICard;
import java.util.List;

public interface ICardCrud {

    boolean insertCard(ICard card);

    List<ICard> getCards();

    ICard getCard(String cardId);

    int updateCard(ICard card);

    boolean deleteCard(String cardId);

}