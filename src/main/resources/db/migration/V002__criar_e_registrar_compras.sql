CREATE TABLE compras (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_grupo BIGINT(20) NOT NULL,
	data_compra DATE NOT NULL,
	data_vencimento DATE NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	observacao VARCHAR(50) NULL,
	valor DECIMAL(14,2) NOT NULL,
	parcela_atual INT(3) NOT NULL,
	parcela_total INT(3) NOT NULL,
	valor_pago DECIMAL(14.2) NULL,
	data_pagamento Date NULL,
	FOREIGN KEY (codigo_grupo) REFERENCES grupos(codigo) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO compras set 
codigo_grupo=1, 
data_compra='2020-01-31', 
data_vencimento='2020-02-01',
descricao='compra teste',
valor=1234.56,
parcela_atual=1,
parcela_total=2;