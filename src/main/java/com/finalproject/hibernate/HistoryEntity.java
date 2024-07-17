package com.finalproject.hibernate;

import com.finalproject.history.Operation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="cardHistory")
public class HistoryEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="cardId")
    private UUID cardId;
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
                         BigDecimal amount, String dateOfOperation,
                         BigDecimal balanceAfterOperation, String terminalId) {
        this.id = id;
        this.cardId = UUID.fromString(cardId);
        this.operation = operation;
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
        this.terminalId = terminalId;
    }

    public HistoryEntity(String cardId, String operation, boolean result,
                         BigDecimal amount, String dateOfOperation,
                         BigDecimal balanceAfterOperation,
                         String terminalId) {
        this.cardId = UUID.fromString(cardId);
        this.operation = Operation.valueOf(operation);
        this.result = result;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.balanceAfterOperation = balanceAfterOperation;
        this.terminalId = terminalId;
    }

    @Override
    public String toString() {
        return "History{" +
            "id=" + id +
            ", cardId='" + cardId  +
            ", operation=" + operation +
            ", result=" + result +
            ", amount=" + amount +
            ", dateOfOperation='" + dateOfOperation +
            ", balanceAfterOperation=" + balanceAfterOperation +
            ", terminalId='" + terminalId + '}';
    }
}
