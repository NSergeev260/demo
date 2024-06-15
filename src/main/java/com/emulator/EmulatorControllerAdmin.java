package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
public class EmulatorControllerAdmin {
    private static final String CARD_ID = "9d06e46d-2b38-11ef-88c3-0cd292f91adb";

    private static final String TERMINAL_ID = "&terminalId=" + "SPATULA";
    private static final String NUMBER_OF_RECORDS = "2";

    private static final String URL_BLOCK_CARD = "http://localhost:80/block?cardId=" + CARD_ID + TERMINAL_ID;
    private static final String URL_UNBLOCK_CARD = "http://localhost:80/unblock?cardId=" + CARD_ID + TERMINAL_ID;
    private static final String URL_IS_BLOCKED_CARDS = "http://localhost:80/isBlocked?cardId=" + CARD_ID;
    private static final String URL_GENERATE_CARD = "http://localhost:80/generateMockData?numberOfRecords=" + NUMBER_OF_RECORDS;

    private Requests requests = new Requests();

    public void getBlock() throws URISyntaxException {
        log.info("=======BLOCK A CARD======");
        requests.postRequest(URL_BLOCK_CARD);
    }

    public void getUnblock() throws URISyntaxException {
        log.info("=======UNBLOCK A CARD======");
        requests.postRequest(URL_UNBLOCK_CARD);
    }

    public void getStatusBlocked() throws URISyntaxException {
        log.info("=======IS BLOCKED======");
        requests.getRequest(URL_IS_BLOCKED_CARDS);
    }

    public void getMockData() throws URISyntaxException {
        log.info("=======GET DATA FOR DB======");
        requests.postRequest(URL_GENERATE_CARD);
    }
}
