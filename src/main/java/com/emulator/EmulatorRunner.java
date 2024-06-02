//package com.emulator;
//
//import com.finalproject.MockData;
//import com.finalproject.card.ICard;
//import com.finalproject.config.ICardCrudFactory;
//import com.finalproject.services.CardService;
//import com.finalproject.services.PayingService;
//
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class EmulatorRunner {
//
//    public static void main(String[] args) throws NoSuchFieldException {
//        List<ICard> cards = new ArrayList<>();
//        ICardCrudFactory iCardCrudFactory = new ICardCrudFactory();
////        CardService cardService = new CardService(iCardCrudFactory);
////        PayingService payingService = new PayingService(cardService, iCardCrudFactory);
//        MockData mockData = new MockData(iCardCrudFactory);
//
//        for (int i = 0; i < 100; i++) {
//            cards.add(mockData.getRandomCard());
//        }
//
//        System.out.println(cards);
//
//    }
//}
