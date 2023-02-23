DROP TABLE IF EXISTS PRICES;
CREATE TABLE PRICES (
brand_id INTEGER NOT NULL,
start_date DATETIME NOT NULL,
end_date DATETIME NOT NULL,
price_list INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
product_id INTEGER NOT NULL,
priority INTEGER NOT NULL,
price DECIMAL(8,2) NOT NULL,
currency VARCHAR(50) NOT NULL
);