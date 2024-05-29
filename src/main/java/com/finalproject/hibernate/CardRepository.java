package com.finalproject.hibernate;

import com.finalproject.card.ICard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CardRepository extends JpaRepository<CardEntity, String> {

//  CardEntity insert(CardEntity card);

//  List<ICard> getCards();
//
//  ICard getCard(String cardId);
//
//  @Modifying
//  @Query("update transportCard c set c.balance = ?2, c.isBlocked = ?3, c.documentId = ?4 where c.cardId = ?1")
//  boolean updateCard(String cardId, double balance, boolean isBlocked, String documentId);
//
//  boolean deleteCard(String cardId);
}