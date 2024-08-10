package com.emulator;

import lombok.extern.slf4j.Slf4j;
import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerAdmin {
    private static final String URL_PATH = "http://localhost:80";
    private static final String URL_CARD = "?cardId=";
    private static final String URL_TERMINAL = "&terminalId=";
    private static final String URL_RECORDS = "?numberOfRecords=";
    private static final String URL_BALANCE = "&balance=";
    private static final String URL_BLOCKED = "&isBlocked=";
    private static final String URL_DOCUMENT = "&documentId=";

    private ParallelRequestsHttp requestsHttp = new ParallelRequestsHttp();

    public void getBlock(String cardId, String terminalId) throws URISyntaxException {
        log.info("=======BLOCK A CARD======");
        String request = URL_PATH + "/block" + URL_CARD + cardId +
            URL_TERMINAL + terminalId;

        requestsHttp.postRequest(request);
    }

    public void getUnblock(String cardId, String terminalId) throws URISyntaxException {
        log.info("=======UNBLOCK A CARD======");
        String request = URL_PATH + "/unblock" + URL_CARD + cardId +
            URL_TERMINAL + terminalId;

        requestsHttp.postRequest(request);
    }

    public String getStatusBlocked(String cardId) throws URISyntaxException {
        log.info("=======IS BLOCKED======");
        String request = URL_PATH + "/isBlocked" + URL_CARD + cardId;

        return requestsHttp.getRequest(request);
    }

    public void getMockData(String numberOfRecords) throws URISyntaxException {
        log.info("=======GET DATA FOR DB======");
        String request = URL_PATH + "/generateMockData" +
            URL_RECORDS + numberOfRecords;

        requestsHttp.postRequest(request);
    }

    public void insertCard() throws URISyntaxException {
        log.info("=======INSERT CARD======");
        String request = URL_PATH + "/insertTest";

        requestsHttp.postRequest(request);
    }

    public void getCard(String cardId) throws URISyntaxException {
        log.info("=======GET CARD======");
        String request = URL_PATH + "/getCard" + URL_CARD + cardId;

        requestsHttp.getRequest(request);
    }

    public String getAllCards() throws URISyntaxException {
        log.info("=======GET ALL CARDs======");
        String request = URL_PATH + "/getCards";

        return requestsHttp.getRequest(request);
    }

    public void updateCard(String cardId, String balance, String isBlocked,
                           String document) throws URISyntaxException {
        log.info("=======UPDATE CARD======");
        String request = URL_PATH + "/update" + URL_CARD + cardId +
            URL_BALANCE + balance + URL_BLOCKED + isBlocked + URL_DOCUMENT +
            document;

        requestsHttp.postRequest(request);
    }

    public void deleteCard(String cardId) throws URISyntaxException {
        log.info("=======DELETE ONE CARD======");
        String request = URL_PATH + "/delete" + URL_CARD + cardId;

        requestsHttp.postRequest(request);
    }
}
