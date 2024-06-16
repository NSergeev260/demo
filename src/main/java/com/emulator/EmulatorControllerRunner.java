package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class EmulatorControllerRunner {
    private static String cardId = "106fbd78-1f37-11ef-bd2a-0cd292f91adb";
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
        EmulatorControllerHibernate hibernate = new EmulatorControllerHibernate();
        EmulatorControllerAdmin admin = new EmulatorControllerAdmin();
        EmulatorControllerTerminal terminal = new EmulatorControllerTerminal();

        admin.getMockData(numberOfRecords);

        Random rnd = new Random();
        int probability = rnd.nextInt(100);

        if (probability < 90) {
            terminal.getPay(cardId, typeOfTransport, terminalId);    // 90%
        } else if (probability < 94) {
            terminal.getPut(cardId, money, terminalId);    // 4%
        } else if (probability < 97) {
            terminal.getBalance(cardId, terminalId);   // 3%
        } else if (probability < 98) {
            admin.getBlock(cardId, terminalId); // 2%
            admin.getUnblock(cardId, terminalId);
        } else if (probability < 99) {
            terminal.activateCard(cardType, terminalId);   // 1%
        } else {
            terminal.activateCard(cardType, terminalId);    // 1%
        }
//        hibernate.getCard(cardId);
//        hibernate.insertCard();
//        hibernate.getAllCards();
//        hibernate.deleteCard(cardId);
//        hibernate.updateCard(cardId, balance, isBlocked, documentId);
//        admin.getBlock(cardId, terminalId);
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
