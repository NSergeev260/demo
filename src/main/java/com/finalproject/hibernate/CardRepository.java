package com.finalproject.hibernate;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.UUID;

@Repository
interface CardRepository extends JpaRepository<CardEntity, UUID> {

    @Transactional
    @Modifying
    @Query("update CardEntity c set c.balance = ?2, c.isBlocked = ?3, c.documentId = ?4 where c.cardId = ?1")
    int updateCard(UUID cardId, BigDecimal balance, boolean isBlocked, String documentId);
}