CREATE TABLE projeto (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_inicio DATE NOT NULL,
    data_entrega DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO projeto (nome, descricao, data_inicio, data_entrega) 
	VALUES (
		'Projeto Criado Para Teste', 
		'Esta é uma descrição do projeto que pode ter até 255 caracteres, acho que é suficiente.', 
		'2020-04-20', 
		'2020-05-22'
    );