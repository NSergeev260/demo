package com.finalproject.history;

import com.finalproject.card.ICard;
import com.finalproject.jdbc.CrudMethodsCard;
import com.finalproject.services.CardService;
import com.finalproject.services.PayingService;
import com.finalproject.transport.Transport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CardHistory {

    private int id;
    private String cardId;
    private String operation;
    private boolean result;
    private BigDecimal amount;
    private String dateOfOperation;
    private BigDecimal balanceAfterOperation;
    private ICard card;
    private PayingService payingService;
    private Transport transport;

    public CardHistory(ICard card, String cardId, String operation, boolean result, BigDecimal amount,
                       String dateOfOperation, BigDecimal balanceAfterOperation) {
        this.cardId = card.getCardId();
        this.operation = String.valueOf(History.valueOf(operation));
        this.result = PayingService.result;
        this.amount = Transport.valueOf(String.valueOf(transport.getTripCost())).getTripCost();
        this.dateOfOperation = String.valueOf(LocalDateTime.now());
        this.balanceAfterOperation = new BigDecimal(payingService.getBalance(card.getCardId(), terminalId));
    }

    public CardHistory(String cardId, String operation, boolean result, BigDecimal amount,
                       String dateOfOperation, BigDecimal balanceAfterOperation) {
        this.cardId = ICard.getCardId();
        this.operation = String.valueOf(History.valueOf(operation));
        this.result = PayingService.result;
        this.amount = Transport.valueOf(String.valueOf(transport.getTripCost())).getTripCost();
        this.dateOfOperation = String.valueOf(LocalDateTime.now());
        this.balanceAfterOperation = new BigDecimal(payingService.getBalance(card.getCardId(), terminalId));
    }

    public CardHistory(ICard card, String cardId, String operation) {
        this.cardId = card.getCardId();
        this.operation = String.valueOf(History.valueOf(operation));
        this.dateOfOperation = String.valueOf(LocalDateTime.now());

    }
}
