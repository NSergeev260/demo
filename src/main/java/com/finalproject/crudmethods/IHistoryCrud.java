package com.finalproject.crudmethods;

import com.finalproject.card.ICard;
import com.finalproject.history.CardHistory;
import java.math.BigDecimal;
import java.util.List;

public interface IHistoryCrud {

    void insertHistory(ICard card, String operation, boolean result,
                       BigDecimal amount, String terminalId);

    List<CardHistory> getHistory();

    CardHistory getHistory(String cardID);

    boolean deleteHistory(String cardId);
}