
CREATE DATABASE card_db;
USE card_db;

CREATE TABLE IF NOT EXISTS transport_card(
card_id VARCHAR(36) NOT NULL,
balance DECIMAL(8, 2) NOT NULL,
type_of_card VARCHAR(6) NOT NULL,
is_blocked boolean NOT NULL,
document_id VARCHAR(36),
PRIMARY KEY(card_id)
);

INSERT INTO transport_card(card_id, balance, type_of_card, is_blocked, document_id)
VALUES (UUID(), 100, 'CREDIT', FALSE, UUID());

SELECT * FROM transport_card;
DESCRIBE transport_card;

CREATE TABLE IF NOT EXISTS card_history(
id int NOT NULL AUTO_INCREMENT,
card_id VARCHAR(36) NOT NULL,
operation VARCHAR(36) NOT NULL,
result boolean NOT NULL,
amount DECIMAL(8, 2),
date_of_operation date NOT NULL,
balance_after_operation DECIMAL(8, 2) NOT NULL,
terminal_id VARCHAR(36) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(card_id) REFERENCES transport_card(card_id)
);

SELECT * FROM card_history;
DESCRIBE card_history;
SELECT * FROM transport_card LEFT JOIN card_history ON card_history.card_id = transport_card.card_id;
