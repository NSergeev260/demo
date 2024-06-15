package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerTerminal {
    private static final String CARD_ID = "9d06e46d-2b38-11ef-88c3-0cd292f91adb";

    private static final String TERMINAL_ID = "&terminalId=" + "SPATULA";
    private static final String NUMBER_OF_RECORDS = "2";

    private static final String URL_PAY = "http://localhost:80/block?cardId=" + CARD_ID + TERMINAL_ID;
    private static final String URL_PUT = "http://localhost:80/unblock?cardId=" + CARD_ID + TERMINAL_ID;
    private static final String URL_GET_BALANCE = "http://localhost:80/isBlocked?cardId=" + CARD_ID;
    private static final String URL_GET_INFO = "http://localhost:80/generateMockData?numberOfRecords=" + NUMBER_OF_RECORDS;
    private static final String URL_ACTIVATE = "http://localhost:80/generateMockData?numberOfRecords=" + NUMBER_OF_RECORDS;

    private Requests requests = new Requests();

    public void getPay() throws URISyntaxException {
        log.info("=======PAY MONEY======");
        requests.postRequest(URL_PAY);
    }

    public void getPut() throws URISyntaxException {
        log.info("=======PUT MONEY======");
        requests.postRequest(URL_PUT);
    }

    public void getBalance() throws URISyntaxException {
        log.info("=======GET BALANCE======");
        requests.getRequest(URL_GET_BALANCE);
    }

    public void getInfo() throws URISyntaxException {
        log.info("=======GET INFO======");
        requests.getRequest(URL_GET_INFO);
    }

    public void activateCard() throws URISyntaxException {
        log.info("=======ACTIVATE(INSERT) CARD======");
        requests.postRequest(URL_ACTIVATE);
    }
}
