package com.finalproject.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, UUID> {

}