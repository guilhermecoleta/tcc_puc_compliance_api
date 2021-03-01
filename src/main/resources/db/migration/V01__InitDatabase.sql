create table supplier (
    id bigint constraint pk_supplier primary key,
    name varchar(500) not null,
    cnpj varchar(14) unique not null,
    phone varchar(11),
    email varchar(256) not null
);

create sequence seq_supplier
    increment 1
        minvalue 1
        maxvalue 9223372036854775807
        start 1
        cache 1;

create table product (
    id bigint constraint pk_product primary key,
    description varchar(500),
    name varchar(500) not null,
    file_path varchar(500),
    supplier_id bigint constraint fk_supplier references supplier(id)
);

create sequence seq_product
    increment 1
        minvalue 1
        maxvalue 9223372036854775807
        start 1
        cache 1;



