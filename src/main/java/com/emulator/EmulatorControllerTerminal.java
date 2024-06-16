package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerTerminal {
    private static final String CARD_ID = "9d06e46d-2b38-11ef-88c3-0cd292f91adb";
    private static final String TYPE_OF_TRANSPORT = "SUBWAY";
    private static final String TERMINAL_ID = "SPATULA";
    private static final String MONEY = "10000";
    private static final String CARD_TYPE = "CREDIT";

    private static final String URL_PAY = "http://localhost:80/pay" +
        "?cardId=" + CARD_ID + "&typeOfTransport=" + TYPE_OF_TRANSPORT +
        "&terminalId=" + TERMINAL_ID;

    private static final String URL_PUT = "http://localhost:80/put" +
        "?cardId=" + CARD_ID + "&money=" + MONEY + "&terminalId=" +
        TERMINAL_ID;

    private static final String URL_GET_BALANCE = "http://localhost:80/balance" +
        "/" + CARD_ID + "?" + "terminalId=" + TERMINAL_ID;

    private static final String URL_GET_INFO = "http://localhost:80/getInfo" +
        "?cardId=" + CARD_ID;

    private static final String URL_ACTIVATE = "http://localhost:80/activate" +
        "?cardType=" + CARD_TYPE + "&terminalId=" + TERMINAL_ID;

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
