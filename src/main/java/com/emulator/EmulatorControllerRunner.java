package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
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


    private static final int NUMBER_OF_REQUESTS = 2;

    public static void main(String[] args) throws Exception {
        EmulatorControllerAdmin admin = new EmulatorControllerAdmin();
        EmulatorControllerHibernate hibernate = new EmulatorControllerHibernate();
        EmulatorControllerTerminal terminal = new EmulatorControllerTerminal();
        Operations operations = new Operations();

        List<String> listCardId = new ArrayList<>(operations.getStartCollectionOfCard(NUMBER_OF_REQUESTS, terminalId));

        log.info("Collection of cardId: {}", listCardId);
        System.out.println("=======EMULATION ++++ GET COLLECTION OF CARD=======");
        System.out.println(listCardId);
        Random rnd = new Random();
        int rndMoney = rnd.nextInt(1000);

        int rndCard = rnd.nextInt(listCardId.size() + 1);
//        operations.getOperationProbability(100, cardId, money, cardType, typeOfTransport, terminalId);

        System.out.println("========");


//        terminal.activateCard(cardType, terminalId);
//        hibernate.getCard(cardId);
//        hibernate.getAllCards();

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