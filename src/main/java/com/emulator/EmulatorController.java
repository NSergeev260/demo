package com.emulator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmulatorController {

    private static final String CARD_ID = "106fbd78-1f37-11ef-bd2a-0cd292f91adb";
    private static final String DELETE_CARD_ID = "369fbccc-e724-433f-860d-db601e17ed3c";

    private static final String UPDATE_CARD_ID = "106fbd78-1f37-11ef-bd2a-0cd292f91adb";
    private static final String UPDATE_BALANCE = "&balance=" + "2000";
    private static final String UPDATE_BLOCKED_STATUS = "&isBlocked=" + "false";
    private static final String UPDATE_DOCUMENT_ID = "&documentId=" + "as";

    private static final String URL_GET_CARD = "http://localhost:80/getCard?cardId=" + CARD_ID;
    private static final String URL_INSERT_CARD = "http://localhost:80/insertTest";
    private static final String URL_GET_ALL_CARDS = "http://localhost:80/getCards";
    private static final String URL_DELETE_CARD = "http://localhost:80/delete?cardId=" + DELETE_CARD_ID;
    private static final String URL_UPDATE_CARD = "http://localhost:80/update?cardId=" + UPDATE_CARD_ID +
        UPDATE_BALANCE + UPDATE_BLOCKED_STATUS;
//    + UPDATE_DOCUMENT_ID;
    //    private static final int NUMBER_OF_REQUESTS = 1;



    public static void main(String[] args) throws Exception {

        Requests requests = new Requests();

//        System.out.println("=======GET CARD======");
//        requests.setGetRequest(URL_GET_CARD);
//        System.out.println("=======INSERT CARD======");
//        requests.setPostRequest(URL_INSERT_CARD);
//        System.out.println("=======GET ALL CARDs======");
//        requests.setGetRequest(URL_GET_ALL_CARDS);
        System.out.println("=======DELETE ONE CARD======");
        requests.setPostRequest(URL_DELETE_CARD);

//        System.out.println("=======UPDATE CARD======");
//        requests.setPostRequest(URL_UPDATE_CARD);
    }
}
