INSERT INTO product (name, amount) VALUES ('Celular', 10);
INSERT INTO product (name, amount) VALUES ('Carregador', 10);
INSERT INTO product (name, amount) VALUES ('Pelicula', 10);
INSERT INTO product (name, amount) VALUES ('Cabo USB', 10);

INSERT INTO transaction_type (name) VALUES ('debit');
INSERT INTO transaction_type (name) VALUES ('credit');

INSERT INTO transaction (amount,"date",price,id_product_fk,id_transaction_type_fk) VALUES
	 (1,'2023-05-19',10.00,2,2),
	 (1,'2023-05-19',10.00,2,1),
	 (1,'2023-05-19',10.00,2,2),
	 (5,'2023-05-19',10.00,2,2),
	 (6,'2023-05-19',10.00,2,1),
	 (1,'2023-05-21',10.00,1,1),
	 (1,'2023-05-21',10.00,1,1),
	 (1,'2023-05-21',10.00,1,1),
	 (1,'2023-05-21',10.00,2,1),
	 (10,'2023-05-21',10.00,1,2);
INSERT INTO transaction (amount,"date",price,id_product_fk,id_transaction_type_fk) VALUES
	 (10,'2023-05-21',24.00,1,2),
	 (10,'2023-05-21',24.00,2,2);