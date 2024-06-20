package com.emulator;

import com.finalproject.transport.Transport;
import lombok.extern.slf4j.Slf4j;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Operations {
    private EmulatorControllerHibernate hibernate;
    private EmulatorControllerAdmin admin;
    private EmulatorControllerTerminal terminal;
    private Random rnd = new Random();
    private static final int RANDOM_BOUND = 101;

    public void getOperationProbability(String cardId, String money,
                                        String cardType, Transport typeOfTransport,
                                        String terminalId) throws URISyntaxException {

        hibernate = new EmulatorControllerHibernate();
        admin = new EmulatorControllerAdmin();
        terminal = new EmulatorControllerTerminal();

        int probability = rnd.nextInt(RANDOM_BOUND);
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

    public List<String> getStartCollectionOfCard(int numberOfCardId, String terminalId)
        throws URISyntaxException {

        terminal = new EmulatorControllerTerminal();
        Random rnd = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        List<String> cards = new ArrayList<>();
        String card;
        String[] cardFields;

        for (int i = 0; i < numberOfCardId; i++) {
            int rndCardType = rnd.nextInt(2);

            if (rndCardType == 0) {
                card = terminal.activateCard("CREDIT", terminalId);
            } else {
                card = terminal.activateCard("DEBIT", terminalId);
            }

            cardFields = card.split(":");
            stringBuilder.append(cardFields[1]);
            stringBuilder.deleteCharAt(0);
            stringBuilder.delete(36, 47);
            cards.add(String.valueOf(stringBuilder));
            stringBuilder.delete(0, stringBuilder.length());
        }
        return cards;
    }
}
