package com.finalproject.hibernate;

import com.finalproject.card.ICard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface CardRepository extends JpaRepository<CardEntity, UUID> {

  @Modifying
  @Query("update CardEntity c set c.balance = ?2, c.is_blocked = ?3, c.document_id = ?4 where c.card_id = ?1")
  boolean updateFieldsCard(String cardId, double balance, boolean isBlocked, String documentId);

  boolean updateCard(ICard card);
}