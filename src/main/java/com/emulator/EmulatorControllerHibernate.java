package com.emulator;

import lombok.extern.slf4j.Slf4j;
import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerHibernate {

    private final String URL_PATH = "http://localhost:80";
    private final String URL_CARD = "?cardId=";
    private final String URL_BALANCE = "&balance=";
    private final String URL_BLOCKED = "&isBlocked=";
    private final String URL_DOCUMENT = "&documentId=";

    private ParallelRequestsHttp parallelRequestsHttp = new ParallelRequestsHttp();

    public void insertCard() throws URISyntaxException {
        log.info("=======INSERT CARD======");
        parallelRequestsHttp.postRequest(URL_PATH + "/insertTest");
    }

    public void getCard(String cardId) throws URISyntaxException {
        log.info("=======GET CARD======");
        parallelRequestsHttp.getRequest(URL_PATH + "/getCard" + URL_CARD + cardId);
    }

    public void getAllCards() throws URISyntaxException {
        log.info("=======GET ALL CARDs======");
        parallelRequestsHttp.getRequest(URL_PATH + "/getCards");
    }

    public void updateCard(String cardId, String balance, String isBlocked,
                           String document) throws URISyntaxException {
        log.info("=======UPDATE CARD======");
        parallelRequestsHttp.postRequest(URL_PATH + "/update" + URL_CARD + cardId +
            URL_BALANCE + balance + URL_BLOCKED + isBlocked + URL_DOCUMENT +
            document);
    }

    public void deleteCard(String cardId) throws URISyntaxException {
        log.info("=======DELETE ONE CARD======");
        parallelRequestsHttp.postRequest(URL_PATH + "/delete" + URL_CARD + cardId);
    }
}
