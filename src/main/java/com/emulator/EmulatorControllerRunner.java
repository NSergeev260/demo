package com.emulator;

import com.finalproject.MockData;
import com.finalproject.card.ICard;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class EmulatorControllerRunner {
    private static String cardId = "179ac9cd-e542-4538-9720-15c0007d63a0";
    private static String balance = "2000";
    private static String isBlocked = "false";
    private static String documentId = "NULL";
    private static String terminalId = "SPATULA";
    private static String numberOfRecords = "10";
    private static String typeOfTransport = "SUBWAY";
    private static String money = "10000";
    private static String cardType = "CREDIT";


    private static final int NUMBER_OF_REQUESTS = 10;

    public static void main(String[] args) throws Exception {
        EmulatorControllerAdmin admin = new EmulatorControllerAdmin();
        EmulatorControllerHibernate hibernate = new EmulatorControllerHibernate();
        EmulatorControllerTerminal terminal = new EmulatorControllerTerminal();
        Operation operation = new Operation();

//        terminal.activateCard(cardType, terminalId);
        StringBuilder str = new StringBuilder();
        String st = terminal.activateCard("CREDIT", "Yellow");
        str.append(terminal.activateCard("CREDIT", "Yellow"));
        System.out.println("==================================================");
        System.out.println(st);
        List<String> cards = new ArrayList<>();


//        for (int i = 0; i < NUMBER_OF_REQUESTS; i++) {
//            cards.add(terminal.activateCard("Credit", "Yellow"));
//        }
//        System.out.println(cards);
//        hibernate.getCard(cardId);
        hibernate.getAllCards();
//            operation.getOperationProbability(100, cardId, money, cardType, typeOfTransport, terminalId);

//        admin.getBlock(cardId, terminalId);
//        hibernate.insertCard();
//        hibernate.deleteCard(cardId);
//        hibernate.updateCard(cardId, balance, isBlocked, documentId);
//        admin.getUnblock(cardId, terminalId);
//        admin.getStatusBlocked(cardId);
//        admin.getMockData(numberOfRecords);
//        terminal.getPay(cardId, typeOfTransport, terminalId);
//        terminal.getPut(cardId, money, terminalId);
//        terminal.getBalance(cardId, terminalId);
//        terminal.getInfo(cardId);
//        terminal.activateCard(cardType, terminalId);
    }
}