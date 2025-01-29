--liquibase formatted sql

--changeset nsergeev:1
CREATE DATABASE card_db;
--SELECT 'CREATE DATABASE card_db'
--WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'card_db');

--changeset nsergeev:2
CREATE TABLE IF NOT EXISTS transport_card(
card_id VARCHAR(36) NOT NULL,
balance DECIMAL(8, 2) NOT NULL,
type_of_card VARCHAR(6) NOT NULL,
is_blocked boolean NOT NULL,
document_id VARCHAR(36),
PRIMARY KEY(card_id)
);

--changeset nsergeev:3
CREATE TABLE IF NOT EXISTS card_history(
id SERIAL NOT NULL PRIMARY KEY,
card_id VARCHAR(36) NOT NULL,
operation VARCHAR(36) NOT NULL,
result boolean NOT NULL,
amount DECIMAL(8, 2),
date_of_operation date NOT NULL,
balance_after_operation DECIMAL(8, 2) NOT NULL,
terminal_id VARCHAR(36) NOT NULL,
FOREIGN KEY(card_id) REFERENCES transport_card(card_id)
);