drop database сarddb;
CREATE DATABASE сarddb;
USE сarddb;

CREATE TABLE IF NOT EXISTS transportCard(
cardId VARCHAR(36) NOT NULL,
balance DECIMAL(8, 2) NOT NULL,
typeOfCard VARCHAR(6) NOT NULL,
isBlocked BOOLEAN NOT NULL,
documentId VARCHAR(36),
PRIMARY KEY(cardId)
);

INSERT INTO transportCard(cardId, balance, typeOfCard, isBlocked, documentId)
VALUES (UUID(), 100, 'CREDIT', FALSE, UUID());

SELECT * FROM transportCard;
DESCRIBE transportCard;

CREATE TABLE IF NOT EXISTS cardHistory(
id int NOT NULL AUTO_INCREMENT,
cardId VARCHAR(36) NOT NULL,
operation VARCHAR(36) NOT NULL,
result BOOLEAN NOT NULL,
amount DECIMAL(8, 2),
dateOfOperation date NOT NULL,
balanceAfterOperation DECIMAL(8, 2) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(cardId) REFERENCES transportCard(cardId)
);

SELECT * FROM cardHistory;
DESCRIBE cardHistory;
SELECT * FROM transportCard LEFT JOIN cardHistory ON cardHistory.cardId = transportCard.cardId;
