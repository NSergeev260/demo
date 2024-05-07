package com.finalproject.card;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "transportCard")
public abstract class AbstractCard implements ICard {

    @Id
    @Column(name = "cardId")
    protected String cardId;

    @Setter
    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "blocked")
    protected boolean blocked;

    public abstract CardType getType();

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }
}
