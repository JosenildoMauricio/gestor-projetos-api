CREATE TABLE tarefa (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    concluido BOOLEAN DEFAULT FALSE,
    id_projeto BIGINT(20) NOT NULL,
    FOREIGN KEY (id_projeto) REFERENCES projeto(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tarefa (nome, descricao, concluido, id_projeto) 
	VALUES (
		'Primeira tarefa do projeto teste', 
		'Esta é uma descrição da tarefa do projeto cria que pode ter até 255 caracteres, acho que é suficiente.',
		false,
		1
    );