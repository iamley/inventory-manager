DROP TABLE IF EXISTS PRICES;
CREATE TABLE PRICES (
brand_id INT(8) NOT NULL,
start_date DATETIME NOT NULL,
end_date DATETIME NOT NULL,
price_list INT(8) NOT NULL,
product_id INT(8) NOT NULL,
priority INT(8) NOT NULL,
price DECIMAL(8,2) NOT NULL,
currency VARCHAR(50) NOT NULL,
PRIMARY KEY (price_list),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;