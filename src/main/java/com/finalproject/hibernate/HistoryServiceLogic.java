//package com.finalproject.hibernate;
//
//import com.finalproject.card.ICard;
//import com.finalproject.history.CardHistory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
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
//        CardHistory history = new CardHistory(card.getCardId(), operation, result, amount,
//            String.valueOf(LocalDateTime.now()), card.getBalance(), terminalId);
//        historyRepository.save(History);
//    }
//
//    @Override
//    public List<CardHistory> getHistory() {
//        List<CardHistory> historyList = new ArrayList<>();
//        for (CardHistory cardHistory: historyList) {
//            historyList.add();
//        }
//        historyList.addAll(historyRepository.findAll());
//        return historyList;
//    }
//
//    @Override
//    public CardHistory getHistory(String cardID) {
//        Optional<History> card = historyRepository.findById(Integer.valueOf(cardID));
//        if (card.isPresent()) {
//            History cardHistory = card.get();
//            return History;
//        } else {
//            return null;
//        }
//    }
//}