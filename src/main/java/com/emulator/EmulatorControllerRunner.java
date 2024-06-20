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

    private static final int NUMBER_OF_CARD = 10;
    private static final int NUMBER_OF_REQUESTS = 40;

    public static void main(String[] args) throws Exception {

        Operations operations = new Operations();

        List<String> listCardId = new ArrayList<>(operations.
            getStartCollectionOfCard(NUMBER_OF_CARD, terminalId));
        log.info("Collection of cardId: {}", listCardId);

        System.out.println("=======EMULATION ++++ OPERATIONS UNDER COLLECTION OF CARDs=======");
        for (int i = 0; i < NUMBER_OF_REQUESTS; i++) {
            operations.getCardOperation(listCardId, terminalId);

//            Runnable parallelRequest = new ParallelRequestHttp(URL, client);
//            Thread parallelThread = new Thread(parallelRequest);
//            parallelThread.start();
        }
    }
}