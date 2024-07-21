package com.emulator;

import com.finalproject.config.CrudFactory;
import com.finalproject.transport.Transport;
import lombok.extern.slf4j.Slf4j;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public class Operations extends CrudFactory {

    private static EmulatorControllerAdmin admin = new EmulatorControllerAdmin();
    private static EmulatorControllerTerminal terminal = new EmulatorControllerTerminal();
    private Random rnd = new Random();
    private static final int RANDOM_BOUND = 101;

    public void getOperationProbability(String cardId, String money,
                                        String cardType, Transport typeOfTransport,
                                        String terminalId) throws URISyntaxException {

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

    public void getCardOperation(List<String> cards, String terminalId)
        throws URISyntaxException {

        int rndCard = rnd.nextInt(cards.size());
        int rndMoney = rnd.nextInt(1001);
        int rndTypeOfTransport = rnd.nextInt(Transport.values().length);
        int rndCardType = rnd.nextInt(2);

        if (rndCardType == 1) {
            getOperationProbability(cards.get(rndCard), String.valueOf(rndMoney),
                "CREDIT", Transport.values()[rndTypeOfTransport], terminalId);
        } else {
            getOperationProbability(cards.get(rndCard), String.valueOf(rndMoney),
                "DEBIT", Transport.values()[rndTypeOfTransport], terminalId);
        }
    }

    public List<String> getCards() throws URISyntaxException {
        String cards = admin.getAllCards();
        List<String> listCard = Arrays.stream(cards.split(","))
            .collect(Collectors.toList());
        return listCard;
    }
}
