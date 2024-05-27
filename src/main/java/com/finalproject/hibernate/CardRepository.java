package com.finalproject.hibernate;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface CardRepository extends CrudRepository<Card, String> {

  Card save(Card card);

  Optional<Card> findById(String cardId);
}