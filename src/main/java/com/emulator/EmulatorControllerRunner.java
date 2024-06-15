package com.emulator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmulatorControllerRunner {

    public static void main(String[] args) throws Exception {
        EmulatorControllerHibernate hibernate = new EmulatorControllerHibernate();
        EmulatorControllerAdmin admin = new EmulatorControllerAdmin();
        EmulatorControllerTerminal terminal = new EmulatorControllerTerminal();
//        hibernate.getCardRequest();
//        hibernate.insertCardRequest();
//        hibernate.getAllCardsRequest();
//        hibernate.deleteCardRequest();
//        hibernate.updateCardRequest();
//        admin.getBlock();
//        admin.getUnblock();
//        admin.getStatusBlocked();
//        admin.getMockData();
        terminal.getPay();
        terminal.getPut();
        terminal.getBalance();
        terminal.getInfo();
        terminal.activateCard();
    }
}
