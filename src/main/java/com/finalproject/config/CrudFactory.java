package com.finalproject.config;

import com.finalproject.crudmethods.ICardCrud;
import com.finalproject.crudmethods.IHistoryCrud;
import com.finalproject.hibernate.*;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.jdbc.CrudMethodsCardJDBC;
import com.finalproject.jdbc.CrudMethodsHistoryJDBC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CrudFactory {

    @Value("${crudImplementation}")
    private String crudImplementation;
    @Autowired
    private ConnectionToDB connectionToDB;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private HistoryRepository historyRepository;

    private static final String CHOSEN_HIBERNATE = "Chosen Hibernate";
    private static final String MESSAGE_JDBC = "Chosen JDBC";
    private static final String MESSAGE_EXCEPTION = "supported crudImplementations are jdbc, hibernate";

    public ICardCrud getICardCrud() {
        switch (crudImplementation.toLowerCase()) {
            case "jdbc" -> {
                log.info(MESSAGE_JDBC);
                return new CrudMethodsCardJDBC(connectionToDB);
            }
            case "hibernate" -> {
                log.info(CHOSEN_HIBERNATE);
                return new CrudMethodsCardHibernate(cardRepository);
            }
            default -> throw new IllegalArgumentException(MESSAGE_EXCEPTION);
        }
    }

    public IHistoryCrud getIHistoryCrud() {
        switch (crudImplementation.toLowerCase()) {
            case "jdbc" -> {
                log.info(MESSAGE_JDBC);
                return new CrudMethodsHistoryJDBC(connectionToDB);
            }
            case "hibernate" -> {
                log.info(CHOSEN_HIBERNATE);
                return new CrudMethodsHistoryHibernate(historyRepository);
            }
            default -> throw new IllegalArgumentException(MESSAGE_EXCEPTION);
        }
    }
}