
CREATE DATABASE Card;
USE Card;

CREATE TABLE IF NOT EXISTS transportCard(
cardID VARCHAR(36) NOT NULL,
balance DECIMAL(8, 2) NOT NULL,
type VARCHAR(6),
cutOffBankDept DECIMAL(5, 2) NOT NULL,
isBlocked VARCHAR(5) NOT NULL,
PRIMARY KEY(cardID)
);

INSERT INTO transportCard(cardID, balance, type, cutOffBankDept, isBlocked) VALUES (UUID(), 50, 'CREDIT', 100, 'false');

SELECT * FROM transportCard;
DESCRIBE transportCard;


CREATE TABLE IF NOT EXISTS cardHolder(
cardID VARCHAR(36) NOT NULL,
documentId VARCHAR(36) NOT NULL,
PRIMARY KEY(cardID)
);

INSERT INTO cardHolder (cardID)
SELECT
	cardID
FROM
	transportCard
WHERE
	cardID = transportCard.cardID;
INSERT INTO cardHolder(documentId) VALUES (UUID());

SELECT * FROM cardHolder;
DESCRIBE cardHolder;

ALTER TABLE cardHolder ADD FOREIGN KEY(cardID) REFERENCES transportCard(cardID);
SELECT * FROM transportCard CROSS JOIN cardHolder ON cardHolder.cardID = transportCard.cardID;
