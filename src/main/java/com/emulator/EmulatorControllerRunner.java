package com.emulator;

import lombok.extern.slf4j.Slf4j;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.*;

@Slf4j
public class EmulatorControllerRunner {

    private static String terminalId = "EMULATOR";
    private static final LocalTime START = LocalTime.now();
    private static LocalTime finish;

    public static void main(String[] args) throws Exception {

        Operations operations = new Operations();
        List<String> listCardId = new ArrayList<>(operations.getCards());
        System.out.println("=======EMULATION ++++ OPERATIONS UNDER COLLECTION OF CARDs=======");
        finish = START.plusMinutes(Long.parseLong(args[1]));

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            newThread(operations, listCardId);
            log.info("Thread # {} has been created", i);
        }
    }

    private static void newThread(Operations operations, List<String> listCardId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (LocalTime.now().isBefore(finish)) {
                        operations.getCardOperation(listCardId, terminalId);
                    }
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}