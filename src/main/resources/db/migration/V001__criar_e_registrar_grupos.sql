CREATE TABLE grupos (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO grupos set nome="Avulso";
INSERT INTO grupos set nome="Credicard";
INSERT INTO grupos set nome="Nubank";
INSERT INTO grupos set nome="Santander";
INSERT INTO grupos set nome="Submarino";