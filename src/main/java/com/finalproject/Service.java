package com.finalproject;

import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.UsageCard;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class Service {

    private String cardId;
    private BigDecimal balance;
    private boolean isCardActive;
    private static BigDecimal cutOffBankDept = new BigDecimal(100);
    DebitCard debitCard;
    CreditCard creditCard;

    public BigDecimal pay(UsageCard card, BigDecimal cost) {

        if (isCardActive) {
            setBalance(new BigDecimal(String.valueOf(getBalance())).subtract(cost));
            log.info("You pay successful: " + cost);
            log.info("Your balance is : " + getBalance());
            return balance;
        } else {
            log.info("Not enough for traveling. Put money on card, please");
        }
        return balance;
    }

    public BigDecimal putMoney(UsageCard card, BigDecimal money) {
        setBalance(new BigDecimal(String.valueOf(getBalance())).add(money));
        log.info("You put: " + money);
        log.info("Your balance is : " + getBalance());
        return getBalance();
    }

    public BigDecimal getBalance(UsageCard card) {
        log.info("Your balance is " + getBalance());
        return getBalance();
    }

    public boolean isActive(UsageCard card, BigDecimal cost) {
        List<UsageCard> typeOfCard = new ArrayList<>();
        typeOfCard.add(card);
        BigDecimal creditMoney = new BigDecimal(String.valueOf(getBalance())).add(cutOffBankDept);

        if (typeOfCard.equals(creditCard)) {
            if (creditMoney.compareTo(cost) < -1) {
                isCardActive = false;
            } else if (creditMoney.compareTo(cost) == 0) {
                isCardActive = false;
            } else {
                isCardActive = true;
            }
        } else if (typeOfCard.equals(debitCard)) {
            if ((getBalance().compareTo(cost) == 0) ||
                (getBalance().compareTo(cost) < 0)) {
                isCardActive = false;
            } else {
                isCardActive = true;
            }
        }
        return isCardActive;
    }
}
