package com.finalproject.hibernate;

import com.finalproject.card.ICard;
import java.util.List;
public interface CardServiceHibernate {

    boolean insertCard(ICard card);

    List<ICard> getCards();

    ICard getCard(String cardId);

    boolean updateCard(ICard card);

    boolean deleteCard(String cardId);


}