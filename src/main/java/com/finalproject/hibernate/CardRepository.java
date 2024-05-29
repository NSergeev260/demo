package com.finalproject.hibernate;

import com.finalproject.card.ICard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
interface CardRepository extends JpaRepository<CardEntity, UUID> {

  @Modifying
  @Query("update transport_card c set c.balance = ?2, c.is_blocked = ?3, c.document_id = ?4 where c.card_id = ?1")
  boolean updateCard(String cardId, BigDecimal balance, boolean isBlocked, String documentId);
}