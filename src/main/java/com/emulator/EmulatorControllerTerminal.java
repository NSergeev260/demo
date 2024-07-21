package com.emulator;

import com.finalproject.transport.Transport;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class EmulatorControllerTerminal {
    private static final String URL_PATH = "http://localhost:80";
    private static final String URL_CARD = "?cardId=";
    private static final String URL_TRANSPORT = "&typeOfTransport=";
    private static final String URL_MONEY = "&money=";
    private static final String URL_TERMINAL = "&terminalId=";
    private static final String URL_CARD_TYPE = "?cardType=";

    private ParallelRequestsHttp parallelRequestsHttp = new ParallelRequestsHttp();

    public void getPay(String cardId, Transport typeOfTransport,
                       String terminalId) throws URISyntaxException {
        log.info("=======PAY MONEY======");
        String request = URL_PATH + "/pay" + URL_CARD + cardId +
            URL_TRANSPORT + typeOfTransport + URL_TERMINAL + terminalId;

        parallelRequestsHttp.postRequest(request);

    }

    public void getPut(String cardId, String money,
                       String terminalId) throws URISyntaxException {
        log.info("=======PUT MONEY======");
        String request = URL_PATH + "/put" + URL_CARD + cardId +
            URL_MONEY + money + URL_TERMINAL + terminalId;

        parallelRequestsHttp.postRequest(request);
    }

    public void getBalance(String cardId, String terminalId) throws URISyntaxException {
        log.info("=======GET BALANCE======");
        String request = URL_PATH + "/balance" + "/" + cardId + "?" +
            URL_TERMINAL + terminalId;

        parallelRequestsHttp.getRequest(request);
    }

    public void getInfo(String cardId) throws URISyntaxException {
        log.info("=======GET INFO======");
        String request = URL_PATH + "/getInfo" + URL_CARD + cardId;

        parallelRequestsHttp.getRequest(request);
    }

    public String activateCard(String cardType, String terminalId) throws URISyntaxException {
        log.info("=======ACTIVATE(INSERT) CARD======");
        String request = URL_PATH + "/activate" + URL_CARD_TYPE + cardType +
            URL_TERMINAL + terminalId;

        return parallelRequestsHttp.postRequest(request);
    }
}
