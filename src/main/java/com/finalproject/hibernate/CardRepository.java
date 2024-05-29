package com.finalproject.hibernate;

import com.finalproject.card.ICard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
interface CardRepository extends JpaRepository<CardEntity, UUID> {

//  @Modifying
//  @Query(value = "update CardEntity set balance = balance, is_blocked = isBlocked, document_id = document_id where card_id = cardId", nativeQuery = true)
//  boolean updateCard(@Param("balance") BigDecimal balance, @Param("is_blocked") boolean isBlocked,
//                     @Param("document_id") String documentId, String cardId);

//  boolean updateCard(BigDecimal balance, boolean isBlocked, String documentId, String cardId);
}