package com.finalproject.config;

import com.finalproject.hibernate.CrudMethodsCardHibernate;
import com.finalproject.hibernate.CrudMethodsHistoryHibernate;
import com.finalproject.hibernate.ICardCrud;
import com.finalproject.hibernate.IHistoryCrud;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.jdbc.CrudMethodsCardJDBC;
import com.finalproject.jdbc.CrudMethodsHistoryJDBC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ICardCrudFactory {

    @Value("${crudImplementation}")
    private String crudImplementation;
    @Autowired
    private ConnectionToDB connectionToDB;

    public ICardCrud getICardCrud() {
        switch (crudImplementation.toLowerCase()) {
            case "jdbc" -> {
                log.info("Chosen JDBC");
                return new CrudMethodsCardJDBC(connectionToDB);
            }
            case "hibernate" -> {
                log.info("Chosen Hibernate");
                return new CrudMethodsCardHibernate();
            }
            default -> throw new IllegalArgumentException("supported crudImplementations are jdbc, hibernate");
        }
    }

    public IHistoryCrud getIHistoryCrud() {
        switch (crudImplementation.toLowerCase()) {
            case "jdbc" -> {
                log.info("Chosen JDBC");
                return new CrudMethodsHistoryJDBC(connectionToDB);
            }
            case "hibernate" -> {
                log.info("Chosen Hibernate");
                return new CrudMethodsHistoryHibernate();
            }
            default -> throw new IllegalArgumentException("supported crudImplementations are jdbc, hibernate");
        }
    }
}