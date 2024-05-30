package com.finalproject.hibernate;

import com.finalproject.history.Operation;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="cardHistory")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="cardId")
    private String cardId;
    @Column(name="operation")
    private Operation operation;
    @Column(name="result")
    private boolean result;
    @Column(name="amount")
    private BigDecimal amount;
    @Column(name="dateOfOperation")
    private String dateOfOperation;
    @Column(name="balanceAfterOperation")
    private BigDecimal balanceAfterOperation;
    @Column(name="terminalId")
    private String terminalId;

    public HistoryEntity() {
    }

    public HistoryEntity(int id, String cardId, Operation operation, boolean result,
                         BigDecimal amount, String dateOfOperation, BigDecimal balanceAfterOperation,
                         String terminalId) {
        this.id = id;
        this.cardId = cardId;
        this.operation = operation;
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
        this.terminalId = terminalId;
    }

    public HistoryEntity(String cardId, String operation, boolean result,
                         BigDecimal amount, String dateOfOperation, BigDecimal balanceAfterOperation,
                         String terminalId) {
        this.cardId = cardId;
        this.operation = Operation.valueOf(operation);
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
        this.terminalId = terminalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(String dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public BigDecimal getBalanceAfterOperation() {
        return balanceAfterOperation;
    }

    public void setBalanceAfterOperation(BigDecimal balanceAfterOperation) {
        this.balanceAfterOperation = balanceAfterOperation;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String toString() {
        return "History{" +
            "id=" + id +
            ", cardId='" + cardId + '\'' +
            ", operation=" + operation +
            ", result=" + result +
            ", amount=" + amount +
            ", dateOfOperation='" + dateOfOperation + '\'' +
            ", balanceAfterOperation=" + balanceAfterOperation +
            ", terminalId='" + terminalId + '\'' +
            '}';
    }
}
