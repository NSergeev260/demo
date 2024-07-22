package com.finalproject.hibernate;

import com.finalproject.card.ICard;
import com.finalproject.crudmethods.IHistoryCrud;
import com.finalproject.history.CardHistory;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public CardHistory getHistory(String cardId) {
        HistoryEntity historyEntity;
        CardHistory cardHistory = null;
        Optional<HistoryEntity> cardById = historyRepository.findById(UUID.fromString(cardId));

        if (cardById.isPresent()) {
            historyEntity = cardById.get();
            cardHistory = toCardHistory(historyEntity);
        }

        return cardHistory;
    }

    @Override
    public boolean deleteHistory(String cardId) {
        historyRepository.deleteAllByCardId(UUID.fromString(cardId));
        return true;
    }

    private CardHistory toCardHistory(HistoryEntity historyEntity) {
        CardHistory cardHistory = new CardHistory(String.valueOf(historyEntity.getCardId()),
            historyEntity.getOperation(),
            Boolean.parseBoolean(historyEntity.getResult()),
            historyEntity.getAmount(),
            historyEntity.getDateOfOperation(),
            historyEntity.getBalanceAfterOperation(),
            historyEntity.getTerminalId());
        return cardHistory;
    }
}