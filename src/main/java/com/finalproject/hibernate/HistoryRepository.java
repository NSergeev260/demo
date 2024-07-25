package com.finalproject.hibernate;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, UUID> {

    @Transactional
    @Modifying
    @Query("DELETE FROM HistoryEntity h WHERE h.cardId = :cardId")
    void deleteAllByCardId(@Param("cardId") UUID cardId);
}