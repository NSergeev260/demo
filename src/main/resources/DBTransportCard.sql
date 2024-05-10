
CREATE DATABASE сardDB;
USE сardDB;

CREATE TABLE IF NOT EXISTS transportCard(
cardId VARCHAR(36) NOT NULL,
balance DECIMAL(8, 2) NOT NULL,
typeOfCard VARCHAR(6) NOT NULL,
cutOffBankDept DECIMAL(5, 2) NOT NULL,
isBlocked BOOLEAN NOT NULL,
documentId VARCHAR(36) NOT NULL,
PRIMARY KEY(cardId)
);

INSERT INTO transportCard(cardId, balance, typeOfCard, cutOffBankDept, isBlocked, documentId)
VALUES (UUID(), 50, 'CREDIT', 100, FALSE, UUID());

SELECT * FROM transportCard;
DESCRIBE transportCard;

CREATE TABLE IF NOT EXISTS cardHistory(
numberOfHistory int NOT NULL AUTO_INCREMENT,
cardId VARCHAR(36) NOT NULL,
operation VARCHAR(36) NOT NULL,
result BOOLEAN NOT NULL,
amount DECIMAL(8, 2) NOT NULL,
dateOfOperation date NOT NULL,
balanceAfterOperation DECIMAL(8, 2) NOT NULL,
PRIMARY KEY(numberOfHistory),
FOREIGN KEY(cardId) REFERENCES transportCard(cardId)
);

INSERT INTO cardHistory(numberOfHistory, operation, result, amount, dateOfOperation, balanceAfterOperation)
VALUES ('1', 'pay', TRUE, 40, '2008-10-29', 90);
INSERT INTO cardHistory(cardId) select cardId from transportCard;


SELECT * FROM cardHistory;
DESCRIBE cardHistory;
SELECT * FROM transportCard CROSS JOIN cardHistory ON cardHistory.cardId = transportCard.cardId;