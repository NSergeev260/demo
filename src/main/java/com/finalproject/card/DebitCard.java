package com.finalproject.card;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Slf4j

public class DebitCard extends AbstractCard {

    public DebitCard() {
        cardId = UUID.randomUUID().toString();
        log.info("cardId: {}", cardId);
    }

    @Override
    public CardType getType() {
        return CardType.DEBIT;
    }
}
