CREATE DATABASE transportCard;
use transportCard;

CREATE TABLE IF NOT EXISTS transportCard(
cardID VARCHAR(36) NOT NULL,
userName VARCHAR(255) NOT NULL,
userSurname VARCHAR(255) NOT NULL,
balance DECIMAL(8, 2) NOT NULL,
debitCard VARCHAR(3),
creditCard VARCHAR(3),
cutOffBankDept DECIMAL(5, 2) NOT NULL,
statusCard VARCHAR(255) NOT NULL,
PRIMARY KEY(cardID)
);

INSERT INTO transportCard(cardID, userName, userSurname, balance, debitCard, creditCard, cutOffBankDept, statusCard) VALUES (UUID(), 'VASYA', 'PUPKIN', 0.0, 'NO', 'YES', 0.0, "Active");

SELECT * FROM transportCard;
DESCRIBE transportCard;