INSERT INTO product VALUES
 (1, 'Term Life',     'Fixed-term life cover',       450.00),
 (2, 'Health Shield', 'Individual health cover',     780.00),
 (3, 'Motor Secure',  'Comprehensive vehicle cover', 320.00),
 (4, 'Home Protect',  'Property and contents cover', 610.00);

INSERT INTO customer_policy (customer_name, customer_id, product_id) VALUES
 ('Anita Rao',   'CUST-1001', 1),
 ('Ben Carter',  'CUST-1002', 3),
 ('Priya Menon', 'CUST-1003', 2),
 ('Daniel Osei', 'CUST-1004', 4),
 ('Anita Rao',   'CUST-1001', 3);
