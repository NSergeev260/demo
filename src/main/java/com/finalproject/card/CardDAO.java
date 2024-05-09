package com.finalproject.card;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CardDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void createCard(ICard card) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Integer id = (Integer) session.save(card);
            System.out.println("Card is created with id" + id);
            session.getTransaction().commit();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
