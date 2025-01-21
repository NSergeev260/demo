--liquibase formatted sql

--changeset nsergeev:1
INSERT INTO transport_card(card_id, balance, type_of_card, is_blocked, document_id)
VALUES (gen_random_uuid (), 100, 'CREDIT', FALSE, gen_random_uuid ());