package com.emulator;

import com.finalproject.transport.Transport;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class EmulatorControllerRunner {
    private static String cardId = "179ac9cd-e542-4538-9720-15c0007d63a0";
    private static String balance = "2000";
    private static String isBlocked = "false";
    private static String documentId = "NULL";
    private static String terminalId = "SPATULA";
    private static String numberOfRecords = "10";
    private static Transport typeOfTransport = Transport.SUBWAY;
    private static String money = "10000";
    private static String cardType = "CREDIT";

    private static final int NUMBER_OF_CARD_ID = 10;
    private static final int NUMBER_OF_REQUESTS = 100;

    public static void main(String[] args) throws Exception {

        Operations operations = new Operations();
        Random rnd = new Random();

        List<String> listCardId = new ArrayList<>(operations.
            getStartCollectionOfCard(NUMBER_OF_CARD_ID, terminalId));
        log.info("Collection of cardId: {}", listCardId);

        System.out.println("=======EMULATION ++++ OPERATIONS UNDER COLLECTION OF CARDs=======");
        for (int i = 0; i < NUMBER_OF_REQUESTS; i++) {
            int rndCard = rnd.nextInt(listCardId.size());
            int rndMoney = rnd.nextInt(1001);
            int rndTypeOfTransport = rnd.nextInt(Transport.values().length);
            int rndCardType = rnd.nextInt(2);

            if (rndCardType == 1) {
                operations.getOperationProbability(listCardId.get(rndCard), String.valueOf(rndMoney),
                    "CREDIT", Transport.values()[rndTypeOfTransport], terminalId);
            } else {
                operations.getOperationProbability(listCardId.get(rndCard), String.valueOf(rndMoney),
                    "DEBIT", Transport.values()[rndTypeOfTransport], terminalId);
            }
        }
    }
}