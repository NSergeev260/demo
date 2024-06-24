package com.emulator;

import lombok.extern.slf4j.Slf4j;
import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerAdmin {
    private static final String URL_PATH = "http://localhost:80";
    private static final String URL_CARD = "?cardId=";
    private static final String URL_TERMINAL = "&terminalId=";
    private static final String URL_RECORDS = "?numberOfRecords=";

    private ParallelRequestsHttp parallelRequestsHttp = new ParallelRequestsHttp();

    public void getBlock(String cardId, String terminal) throws URISyntaxException {
        log.info("=======BLOCK A CARD======");
        parallelRequestsHttp.postRequest(URL_PATH + "/block" + URL_CARD + cardId +
            URL_TERMINAL + terminal);
    }

    public void getUnblock(String cardId, String terminalId) throws URISyntaxException {
        log.info("=======UNBLOCK A CARD======");
        parallelRequestsHttp.postRequest(URL_PATH + "/unblock" + URL_CARD + cardId +
            URL_TERMINAL + terminalId);
    }

    public String getStatusBlocked(String cardId) throws URISyntaxException {
        log.info("=======IS BLOCKED======");
        return parallelRequestsHttp.getRequest(URL_PATH + "/isBlocked" + URL_CARD + cardId);
    }

    public void getMockData(String numberOfRecords) throws URISyntaxException {
        log.info("=======GET DATA FOR DB======");
        parallelRequestsHttp.postRequest(URL_PATH + "/generateMockData" +
            URL_RECORDS + numberOfRecords);
    }
}
