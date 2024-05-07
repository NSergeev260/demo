
CREATE DATABASE cardDB;
USE Card;

CREATE TABLE IF NOT EXISTS transportCard(
cardId VARCHAR(36) NOT NULL,
balance DECIMAL(8, 2) NOT NULL,
type VARCHAR(6),
cutOffBankDept DECIMAL(5, 2) NOT NULL,
isBlocked VARCHAR(5) NOT NULL,
PRIMARY KEY(cardId)
);

INSERT INTO transportCard(cardId, balance, type, cutOffBankDept, isBlocked) VALUES (UUID(), 50, 'CREDIT', 100, 'false');

SELECT * FROM transportCard;
DESCRIBE transportCard;


CREATE TABLE IF NOT EXISTS cardHolder(
cardId VARCHAR(36) NOT NULL,
documentId VARCHAR(36) NOT NULL,
PRIMARY KEY(cardId)
);

INSERT INTO cardHolder (cardID)
SELECT
	cardId
FROM
	transportCard
WHERE
	cardId = transportCard.cardId;
INSERT INTO cardHolder(documentId) VALUES (UUID());

SELECT * FROM cardHolder;
DESCRIBE cardHolder;

ALTER TABLE cardHolder ADD FOREIGN KEY(cardId) REFERENCES transportCard(cardId);
SELECT * FROM transportCard CROSS JOIN cardHolder ON cardHolder.cardId = transportCard.cardId;
