package com.finalproject.history;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CardHistory {

    private int id;
    private String cardId;
    private Operation operation;
    private boolean result;
    private BigDecimal amount;
    private String dateOfOperation;
    private BigDecimal balanceAfterOperation;

    public CardHistory(String cardId, Operation operation, boolean result, BigDecimal amount,
                       String dateOfOperation, BigDecimal balanceAfterOperation) {
        this.cardId = cardId;
        this.operation = operation;
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
    }

    public CardHistory(String cardId, String operation, boolean result, BigDecimal amount,
                       String dateOfOperation, BigDecimal balanceAfterOperation) {
        this.cardId = cardId;
        this.operation = Operation.valueOf(operation);
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
    }

}
