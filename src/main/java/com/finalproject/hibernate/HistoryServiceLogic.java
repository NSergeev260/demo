//package com.finalproject.hibernate;
//
//import com.finalproject.card.ICard;
//import com.finalproject.history.CardHistory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class HistoryServiceLogic implements HistoryServiceHibernate {
//    @Autowired
//    HistoryRepository historyRepository;
//
//    @Override
//    public void insertHistory(ICard card, String operation, boolean result, BigDecimal amount, String terminalId) {
//        History history = new History(int id, String cardId, operation, result, amount, terminalId);
//        historyRepository.save(History);
//    }
//
//    @Override
//    public List<CardHistory> getHistory() {
//        return historyRepository.findAll();
//    }
//
//    @Override
//    public CardHistory getHistory(String cardID) {
//        return historyRepository.findById(cardID);
//    }
//}