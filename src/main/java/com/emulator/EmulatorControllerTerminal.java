package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerTerminal {
    private final String URL_PATH = "http://localhost:80";
    private final String URL_CARD = "?cardId=";
    private final String URL_TRANSPORT = "&typeOfTransport=";
    private final String URL_MONEY = "&money=";
    private final String URL_TERMINAL = "&terminalId=";
    private final String URL_CARD_TYPE = "?cardType=";

    private Requests requests = new Requests();

    public void getPay(String cardId, String typeOfTransport,
                       String terminalId) throws URISyntaxException {
        log.info("=======PAY MONEY======");
        requests.postRequest(URL_PATH + "/pay" + URL_CARD + cardId +
            URL_TRANSPORT + typeOfTransport + URL_TERMINAL + terminalId);
    }

    public void getPut(String cardId, String money,
                       String terminalId) throws URISyntaxException {
        log.info("=======PUT MONEY======");
        requests.postRequest(URL_PATH + "/put" + URL_CARD + cardId +
            URL_MONEY + money + URL_TERMINAL + terminalId);
    }

    public void getBalance(String cardId, String terminalId) throws URISyntaxException {
        log.info("=======GET BALANCE======");
        requests.getRequest(URL_PATH + "/balance" + "/" + cardId + "?" +
            URL_TERMINAL + terminalId);
    }

    public void getInfo(String cardId) throws URISyntaxException {
        log.info("=======GET INFO======");
        requests.getRequest(URL_PATH + "/getInfo" + URL_CARD + cardId);
    }

    public String activateCard(String cardType, String terminal) throws URISyntaxException {
        log.info("=======ACTIVATE(INSERT) CARD======");
        return requests.postRequest(URL_PATH + "/activate" + URL_CARD_TYPE + cardType +
            URL_TERMINAL + terminal);
    }
}
