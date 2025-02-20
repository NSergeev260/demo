package com.finalproject.history;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CardHistory {

    private int id;
    private UUID cardId;
    private Operation operation;
    private boolean result;
    private BigDecimal amount;
    private String dateOfOperation;
    private BigDecimal balanceAfterOperation;
    private String terminalId;

    public CardHistory(String cardId, Operation operation, boolean result,
                       BigDecimal amount, String dateOfOperation,
                       BigDecimal balanceAfterOperation, String terminalId) {
        this.cardId = UUID.fromString(cardId);
        this.operation = operation;
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
        this.terminalId = terminalId;
    }

    public CardHistory(String cardId, String operation, boolean result,
                       BigDecimal amount, String dateOfOperation,
                       BigDecimal balanceAfterOperation, String terminalId) {
        this.cardId = UUID.fromString(cardId);
        this.operation = Operation.valueOf(operation);
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
        this.terminalId = terminalId;
    }

    public CardHistory(int id, String cardId, String operation, boolean result,
                       BigDecimal amount, String dateOfOperation,
                       BigDecimal balanceAfterOperation, String terminalId) {
        this.id = id;
        this.cardId = UUID.fromString(cardId);
        this.operation = Operation.valueOf(operation);
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
        this.terminalId = terminalId;
    }
}