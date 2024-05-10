//package com.finalproject;
//
//import com.finalproject.card.CardDAO;
//import com.finalproject.card.CreditCard;
//import com.finalproject.card.ICard;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.math.BigDecimal;
//
//@SpringBootApplication
//public class SpringBootHibernateProjectApplicationRunner implements CommandLineRunner {
//
//    private CardDAO cardDAO;
//    public static void main(String[] args) {
//        SpringApplication.run(SpringBootHibernateProjectApplicationRunner.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        ICard card = getCard();
//        cardDAO.createCard(card);
//    }
//
//    private ICard getCard() {
//        CreditCard card = new CreditCard("10", "10");
//        card.setBalance(new BigDecimal(10));
//        return card;
//    }
//}
