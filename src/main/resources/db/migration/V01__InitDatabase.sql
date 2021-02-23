create table supply (
    id bigint constraint PK_SUPPLY PRIMARY KEY,
    description varchar(500)
);

CREATE SEQUENCE SEQ_SUPPLY
    INCREMENT 1
        MINVALUE 1
        MAXVALUE 9223372036854775807
        START 1
        CACHE 1