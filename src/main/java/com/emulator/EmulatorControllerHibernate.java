package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerHibernate {

    private static final String CARD_ID = "106fbd78-1f37-11ef-bd2a-0cd292f91adb";

    private static final String UPDATE_BALANCE = "&balance=" + "2000";
    private static final String UPDATE_BLOCKED_STATUS = "&isBlocked=" + "false";
    private static final String UPDATE_DOCUMENT_ID = "&documentId=" + "NULL";

    private static final String URL_GET_CARD = "http://localhost:80/getCard?cardId=" + CARD_ID;
    private static final String URL_INSERT_CARD = "http://localhost:80/insertTest";
    private static final String URL_GET_ALL_CARDS = "http://localhost:80/getCards";
    private static final String URL_DELETE_CARD = "http://localhost:80/delete?cardId=" + CARD_ID;
    private static final String URL_UPDATE_CARD = "http://localhost:80/update?cardId=" + CARD_ID +
        UPDATE_BALANCE + UPDATE_BLOCKED_STATUS + UPDATE_DOCUMENT_ID;

    private Requests requests = new Requests();

    public void getCardRequest() throws URISyntaxException {
        log.info("=======GET CARD======");
        requests.getRequest(URL_GET_CARD);
    }

    public void insertCardRequest() throws URISyntaxException {
        log.info("=======INSERT CARD======");
        requests.postRequest(URL_INSERT_CARD);
    }

    public void getAllCardsRequest() throws URISyntaxException {
        log.info("=======GET ALL CARDs======");
        requests.getRequest(URL_GET_ALL_CARDS);
    }

    public void deleteCardRequest() throws URISyntaxException {
        log.info("=======DELETE ONE CARD======");
        requests.postRequest(URL_DELETE_CARD);
    }

    public void updateCardRequest() throws URISyntaxException {
        log.info("=======UPDATE CARD======");
        requests.postRequest(URL_UPDATE_CARD);
    }
}
