CREATE TABLE pessoa (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    contato VARCHAR(15),
    senha VARCHAR(100) NOT NULL,
    avatar MEDIUMBLOB,
    ativo BOOLEAN DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, email, contato, senha, avatar, ativo) 
	VALUES (
		'Josenildo Mauricio', 
		'mauricius.contact@gmail.com', 
		'62982696065', 
		'$2a$10$iMxlyIaYBfjTAmSun3mDxuDcWoB2sMfo82iQkUFFJ95Ny.YVfWHge',
		'',
		false
    );