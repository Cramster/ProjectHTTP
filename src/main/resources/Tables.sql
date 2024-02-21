--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables
DROP TABLE Seller IF EXISTS;
CREATE TABLE Seller (
    seller_id int primary key,
    name varchar(255) NOT NULL
);