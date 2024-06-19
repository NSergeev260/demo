package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerAdmin {
    private final String URL_PATH = "http://localhost:80";
    private final String URL_CARD = "?cardId=";
    private final String URL_TERMINAL = "&terminalId=";
    private final String URL_RECORDS = "?numberOfRecords=";

    private Requests requests = new Requests();

    public void getBlock(String cardId, String terminal) throws URISyntaxException {
        log.info("=======BLOCK A CARD======");
        requests.postRequest(URL_PATH + "/block" + URL_CARD + cardId +
            URL_TERMINAL + terminal);
    }

    public void getUnblock(String cardId, String terminalId) throws URISyntaxException {
        log.info("=======UNBLOCK A CARD======");
        requests.postRequest(URL_PATH + "/unblock" + URL_CARD + cardId +
            URL_TERMINAL + terminalId);
    }

    public String getStatusBlocked(String cardId) throws URISyntaxException {
        log.info("=======IS BLOCKED======");
        return requests.getRequest(URL_PATH + "/isBlocked" + URL_CARD + cardId);
    }

    public void getMockData(String numberOfRecords) throws URISyntaxException {
        log.info("=======GET DATA FOR DB======");
        requests.postRequest(URL_PATH + "/generateMockData" +
            URL_RECORDS + numberOfRecords);
    }
}
