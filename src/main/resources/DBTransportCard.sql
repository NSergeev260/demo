
CREATE DATABASE сardDB;
USE сardDB;

CREATE TABLE IF NOT EXISTS transportCard(
cardId VARCHAR(36) NOT NULL,
balance DECIMAL(8, 2) NOT NULL,
type VARCHAR(6),
cutOffBankDept DECIMAL(5, 2) NOT NULL,
isBlocked BOOLEAN NOT NULL,
documentId VARCHAR(36) NOT NULL,
PRIMARY KEY(cardId)
);

INSERT INTO transportCard(cardId, balance, type, cutOffBankDept, isBlocked, documentId) VALUES (UUID(), 50, 'CREDIT', 100, FALSE, UUID());

SELECT * FROM transportCard;
DESCRIBE transportCard;


CREATE TABLE IF NOT EXISTS history(
cardId VARCHAR(36) NOT NULL,
operation VARCHAR(36) NOT NULL,
result BOOLEAN NOT NULL,
amount VARCHAR(255) NOT NULL,
dateOfOperation date NOT NULL,
balanceAfterOperation VARCHAR(255) NOT NULL,
PRIMARY KEY(cardID)
);

INSERT INTO history(cardId, operation, result, amount, dateOfOperation, balanceAfterOperation) VALUES (UUID());

SELECT * FROM history;
DESCRIBE history;

ALTER TABLE history ADD FOREIGN KEY(cardId) REFERENCES transportCard(cardId);
SELECT * FROM transportCard CROSS JOIN history ON history.cardId = transportCard.cardId;
