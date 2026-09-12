CREATE TABLE IF NOT EXISTS product (
    product_id   INT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    description  VARCHAR(255),
    premium      DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS customer_policy (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    customer_id   VARCHAR(20)  NOT NULL,
    product_id    INT NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(product_id)
);
