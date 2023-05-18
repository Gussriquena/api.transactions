CREATE TABLE public.product (
	id_product bigserial NOT NULL,
	amount int4 NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id_product)
);

CREATE TABLE public.transaction_type (
	id_transaction bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT transaction_type_pkey PRIMARY KEY (id_transaction)
);

CREATE TABLE public."transaction" (
	id_transaction bigserial NOT NULL,
	price numeric(38, 2) NULL,
	transaction_amount int4 NOT NULL,
	id_product_fk int8 NULL,
	id_transaction_type_fk int8 NULL,
	CONSTRAINT transaction_pkey PRIMARY KEY (id_transaction)
);

ALTER TABLE public."transaction" ADD CONSTRAINT fkfrm77a9enpig1smrh126f7vac FOREIGN KEY (id_transaction_type_fk) REFERENCES public.transaction_type(id_transaction);
ALTER TABLE public."transaction" ADD CONSTRAINT fkrntknltq2eq7mkxbiwww8opdj FOREIGN KEY (id_product_fk) REFERENCES public.product(id_product);