CREATE TABLE tipo_urgencia (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cor VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tipo_urgencia (nome, cor) VALUES ('Indefinida', '#FFFFFF');
INSERT INTO tipo_urgencia (nome, cor) VALUES ('Sem pressa', '#ADD8E6');
INSERT INTO tipo_urgencia (nome, cor) VALUES ('Com prazo marcado', '#FFFF00');
INSERT INTO tipo_urgencia (nome, cor) VALUES ('Urgente', '#FFA500');
INSERT INTO tipo_urgencia (nome, cor) VALUES ('Para ontem', '#FF0000');
INSERT INTO tipo_urgencia (nome, cor) VALUES ('Voltar no tempo e fazer ANTEONTEM', '#808080');