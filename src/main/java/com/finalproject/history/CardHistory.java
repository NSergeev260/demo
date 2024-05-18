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

    private PayingService payingService;

    public CardHistory(ICard card, String operation, Transport typeOfTransport, PayingService balance) {
        this.cardId = card.getCardId();
        this.operation = String.valueOf(History.valueOf(operation));
        this.result = CrudMethodsCard.result;
        this.amount = Transport.valueOf(String.valueOf(typeOfTransport)).getTripCost();
        this.dateOfOperation = String.valueOf(LocalDateTime.now());
        this.balanceAfterOperation = new BigDecimal(payingService.getBalance(card.getCardId(), "1"));
    }

    public CardHistory(int id, String cardId, String operation, boolean result, BigDecimal amount,
                       String dateOfOperation, BigDecimal balanceAfterOperation) {
        this.id = id;
        this.cardId = cardId;
        this.operation = operation;
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
    }
}
