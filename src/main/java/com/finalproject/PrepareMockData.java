package com.finalproject;

import com.finalproject.card.CreditCard;
import java.math.BigDecimal;
import com.finalproject.services.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PrepareMockData {

    private CardService cardService;

    @EventListener(ContextRefreshedEvent.class)
    void prepareData() {
        CreditCard card = new CreditCard("1", "UUID");
        card.setBalance(new BigDecimal("50"));
        cardService.addCard(card);

        log.info("Card balance: {}", card.getBalance());
        log.info("Type of card: {}", card.getType());
        log.info("Status card: {}", card.isBlocked());
    }
}
