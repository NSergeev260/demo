package com.emulator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmulatorControllerRunner {

    public static void main(String[] args) throws Exception {
        EmulatorControllerHibernate emulatorControllerHibernate = new EmulatorControllerHibernate();

        System.out.println("=======GET CARD======");
//        emulatorControllerHibernate.getCardRequest();

        System.out.println("=======INSERT CARD======");
//        emulatorControllerHibernate.insertCardRequest();

        System.out.println("=======GET ALL CARDs======");
        emulatorControllerHibernate.getAllCardsRequest();

        System.out.println("=======DELETE ONE CARD======");
//        emulatorControllerHibernate.deleteCardRequest();

        System.out.println("=======UPDATE CARD======");
//        emulatorControllerHibernate.updateCardRequest();
    }
}
