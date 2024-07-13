package com.finalproject.hibernate;

import com.finalproject.card.ICard;
import com.finalproject.crudmethods.IHistoryCrud;
import com.finalproject.history.CardHistory;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class CrudMethodsHistoryHibernate implements IHistoryCrud {

    private final HistoryRepository historyRepository;

    public CrudMethodsHistoryHibernate(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void insertHistory(ICard card, String operation, boolean result,
                              BigDecimal amount, String terminalId) {
        HistoryEntity historyEntity = new HistoryEntity(card.getCardId(),
            operation, result, amount,
            String.valueOf(LocalDateTime.now()),
            card.getBalance(), terminalId);
        historyRepository.save(historyEntity);
    }

    @Override
    public List<CardHistory> getHistory() {
        List<HistoryEntity> historyEntityList = historyRepository.findAll();
       List<CardHistory> cardHistoryList = historyEntityList.stream()
           .map(this::toCardHistory)
           .toList();
        return cardHistoryList;
    }

    @Override
    public CardHistory getHistory(String cardID) {
        HistoryEntity historyEntity;
        CardHistory cardHistory = null;
        Optional<HistoryEntity> cardById = historyRepository.findById(Integer.valueOf(cardID));

        if (cardById.isPresent()) {
            historyEntity = cardById.get();
            cardHistory = toCardHistory(historyEntity);
        }

        return cardHistory;
    }

    private CardHistory toCardHistory(HistoryEntity historyEntity) {
        CardHistory cardHistory = new CardHistory(historyEntity.getCardId(),
            historyEntity.getOperation(),
            historyEntity.isResult(),
            historyEntity.getAmount(),
            historyEntity.getDateOfOperation(),
            historyEntity.getBalanceAfterOperation(),
            historyEntity.getTerminalId());
        return cardHistory;
    }
}