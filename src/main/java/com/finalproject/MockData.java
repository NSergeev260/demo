package com.finalproject;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.jdbc.CrudMethodsCardJDBC;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MockData {

    private final Random rnd = new Random();
    private CrudMethodsCardJDBC crudMethodsCard;

    public void generateMockData(long numberOfRecords) {

        for (int i = 0; i < numberOfRecords; i++) {
            ICard card = getRandomCard();
            crudMethodsCard.insertCard(card);
        }
    }

    public ICard getRandomCard() {
        int value = rnd.nextInt(2);
        ICard card;
        BigDecimal balance = new BigDecimal(rnd.nextInt(5000));
        boolean blocked = rnd.nextBoolean();

        if (value == 0) {
            card = new CreditCard(balance, blocked,UUID.randomUUID().toString());
        } else {
            card = new DebitCard(balance, blocked);
        }

        return card;
    }
}
