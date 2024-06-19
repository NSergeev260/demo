package com.emulator;

import java.net.URISyntaxException;
import java.util.Random;

public class Operation {
    private EmulatorControllerHibernate hibernate;
    private EmulatorControllerAdmin admin;
    private EmulatorControllerTerminal terminal;
    private Random rnd = new Random();

    public void getOperationProbability(int bound, String cardId, String money,
                                        String cardType, String typeOfTransport,
                                        String terminalId) throws URISyntaxException {

        hibernate = new EmulatorControllerHibernate();
        admin = new EmulatorControllerAdmin();
        terminal = new EmulatorControllerTerminal();

        int probability = rnd.nextInt(bound);
        if (probability < 90) {
            terminal.getPay(cardId, typeOfTransport, terminalId);    // 90%
        } else if (probability < 94) {
            terminal.getPut(cardId, money, terminalId);    // 4%
        } else if (probability < 97) {
            terminal.getBalance(cardId, terminalId);   // 3%
        } else if (probability < 99) {

            if (Boolean.parseBoolean(admin.getStatusBlocked(cardId))) {
                admin.getBlock(cardId, terminalId); // 1%
            } else {
                admin.getUnblock(cardId, terminalId); // 1%
            }

        } else {
            terminal.activateCard(cardType, terminalId);    // 1%
        }
    }
}
