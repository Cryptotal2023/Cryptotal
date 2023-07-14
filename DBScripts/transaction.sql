CREATE DATABASE IF NOT EXISTS transaction;
USE transaction;

DROP TABLE IF EXISTS AccountCryptoData;
DROP TABLE IF EXISTS CryptoTradePairData;
DROP TABLE IF EXISTS Transaction;

CREATE TABLE Transaction (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	accountId INT NOT NULL,
	cryptoAsset VARCHAR(255) NOT NULL,
	tradePairAsset VARCHAR(255) NOT NULL,
	tradeId BIGINT,
	orderId BIGINT NOT NULL,
	orderListId BIGINT,
	price DOUBLE NOT NULL,
	quantity DOUBLE NOT NULL,
	tradePairQuantity DOUBLE NOT NULL,
	commission DOUBLE,
	commissionAsset VARCHAR(255),
	isBuyer BOOLEAN,
	isMaker BOOLEAN,
	isBestMatch BOOLEAN,
	time DATETIME,
	createAt DATETIME NOT NULL default NOW(),
	memo VARCHAR(3000),
	FOREIGN KEY (accountId) REFERENCES account.Account(id)
	ON DELETE CASCADE,
	FOREIGN KEY (cryptoAsset) REFERENCES account.Crypto(asset)
	ON DELETE CASCADE,
	FOREIGN KEY (tradePairAsset) REFERENCES account.Crypto(asset)
	ON DELETE CASCADE,
	FOREIGN KEY (commissionAsset) REFERENCES account.Crypto(asset)
	ON DELETE CASCADE,
	UNIQUE KEY (accountId, orderId)
);

CREATE TABLE AccountCryptoData (
	accountId INT NOT NULL,
	cryptoAsset VARCHAR(255) NOT NULL,
	cost DOUBLE default 0.0,
	quantity DOUBLE default 0.0,
	buyCount INT NOT NULL default 0,
	sellCount INT NOT NULL default 0,
	createAt DATETIME NOT NULL default NOW(),
	lastModificationAt DATETIME NOT NULL default NOW(),
	updateFlag BOOLEAN NOT NULL default true,
	memo VARCHAR(3000),
	PRIMARY KEY (accountId, cryptoAsset),
	FOREIGN KEY (accountId) REFERENCES account.Account(id)
	ON DELETE CASCADE,
	FOREIGN KEY (cryptoAsset) REFERENCES account.Crypto(asset)
	ON DELETE CASCADE
);

CREATE TABLE CryptoTradePairData (
	accountId INT NOT NULL,
	cryptoAsset VARCHAR(255) NOT NULL,
    tradePairAsset VARCHAR(255) NOT NULL,
    latestTransactionId BIGINT NOT NULL,
	PRIMARY KEY (accountId, cryptoAsset, tradePairAsset),
	FOREIGN KEY (accountId) REFERENCES account.Account(id)
	ON DELETE CASCADE,
	FOREIGN KEY (cryptoAsset) REFERENCES account.Crypto(asset)
	ON DELETE CASCADE,
	FOREIGN KEY (tradePairAsset) REFERENCES account.Crypto(asset)
	ON DELETE CASCADE,
	FOREIGN KEY (latestTransactionId) REFERENCES Transaction(id)
	ON DELETE CASCADE
);

-- Insert Transaction data
INSERT INTO Transaction (accountId, cryptoAsset, tradePairAsset, tradeId, orderId, orderListId, price, quantity, tradePairQuantity, commission, commissionAsset, isBuyer, isMaker, isBestMatch, time, createAt, memo)
VALUES 
(1, 'BTC', 'BNB', 28457, 100234, -1, 4.00000100, 12.00000000, 48.000012, 10.10000000, 'BTC', true, false, true, "2023-07-06 08:00:00.000", NOW(), ""),
(1, 'BTC', 'BNB', 28458, 100334, -1, 3.50000, 20.00000000, 70.000000, 12.50000000, 'BTC', false, false, true, "2023-07-06 12:00:00.000", NOW(), ""),
(1, 'BNB', 'BTC', 28459, 100434, -1, 1.20000, 50.00000000, 16.000000, 10.50000000, 'BNB', false, false, true, "2023-07-06 12:30:00.000", NOW(), ""),
(1, 'BTC', 'ETH', 34429, 100876, -1, 3.0320, 33.00000000, 0.0529677, 15.10000000, 'BTC', true, true, true, "2023-07-06 12:45:00.000", NOW(), ""),
(1, 'BTC', 'USDT', 34490, 100987, -1, 20000, 1.00000000, 20000.0, 1, 'USDT', true, true, true, "2023-07-06 12:50:00.000", NOW(), ""),
(4, 'FLOW', 'USDT', 34490, 100987, -1, 10, 10.00000000, 50.0, 1, 'USDT', true, true, true, "2023-07-06 12:50:00.000", NOW(), "");

-- Insert AccountCryptoData data
INSERT INTO AccountCryptoData (accountId, cryptoAsset, cost, quantity, buyCount, sellCount, createAt, lastModificationAt, updateFlag, memo)
VALUES 
(1, 'BTC', 3.5, 49.00000000, 3, 1, NOW(), NOW(), true, ""),
(1, 'BNB', 1.000, 72.00000000, 1, 2, NOW(), NOW(), true, ""),
(1, 'ETH', 1995.9876, 0.8500000, 0, 1, NOW(), NOW(), true, "");

-- Insert CryptoTradePairData data
INSERT INTO CryptoTradePairData (accountId, cryptoAsset, tradePairAsset, latestTransactionId)
VALUES 
(1, 'BTC', 'USDT', 5);
