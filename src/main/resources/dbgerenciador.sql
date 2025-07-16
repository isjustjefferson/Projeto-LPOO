CREATE DATABASE dbgerenciador;
USE dbgerenciador;
CREATE TABLE IF NOT EXISTS user(
    id_user smallint UNIQUE AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    email varchar(50) NOT NULL PRIMARY KEY,
    senha varchar(50) NOT NULL,
    imagem LONGBLOB
);
DROP TABLE user;
SELECT * FROM user;
UNLOCK TABLES;