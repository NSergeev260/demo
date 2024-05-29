//package com.finalproject.hibernate;
//
//import com.finalproject.card.ICard;
//import com.finalproject.history.CardHistory;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//interface HistoryRepository extends JpaRepository<History, Integer> {
//
//    void insertHistory(ICard card, String operation, boolean result, BigDecimal amount, String terminalId);
//
//    List<CardHistory> getHistory();
//
//    CardHistory getHistory(String cardID);
//}