package com.emulator;

import com.finalproject.transport.Transport;
import lombok.extern.slf4j.Slf4j;
import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerTerminal {
    private static final String URL_PATH = "http://localhost:80";
    private static final String URL_CARD = "?cardId=";
    private static final String URL_TRANSPORT = "&typeOfTransport=";
    private static final String URL_MONEY = "&money=";
    private static final String URL_TERMINAL = "&terminalId=";
    private static final String URL_CARD_TYPE = "?cardType=";

    private ParallelRequestsHttp requestsHttp = new ParallelRequestsHttp();

    public void getPay(String cardId, Transport typeOfTransport,
                       String terminalId) throws URISyntaxException {
        log.info("=======PAY MONEY======");
        String request = URL_PATH + "/pay" + URL_CARD + cardId +
            URL_TRANSPORT + typeOfTransport + URL_TERMINAL + terminalId;

        requestsHttp.postRequest(request);

    }

    public void getPut(String cardId, String money,
                       String terminalId) throws URISyntaxException {
        log.info("=======PUT MONEY======");
        String request = URL_PATH + "/put" + URL_CARD + cardId +
            URL_MONEY + money + URL_TERMINAL + terminalId;

        requestsHttp.postRequest(request);
    }

    public void getBalance(String cardId, String terminalId) throws URISyntaxException {
        log.info("=======GET BALANCE======");
        String request = URL_PATH + "/balance" + "/" + cardId + "?" +
            URL_TERMINAL + terminalId;

        requestsHttp.getRequest(request);
    }

    public void getInfo(String cardId) throws URISyntaxException {
        log.info("=======GET INFO======");
        String request = URL_PATH + "/getInfo" + URL_CARD + cardId;

        requestsHttp.getRequest(request);
    }

    public String activateCard(String cardType, String terminalId) throws URISyntaxException {
        log.info("=======ACTIVATE(INSERT) CARD======");
        String request = URL_PATH + "/activate" + URL_CARD_TYPE + cardType +
            URL_TERMINAL + terminalId;

        return requestsHttp.postRequest(request);
    }
}
