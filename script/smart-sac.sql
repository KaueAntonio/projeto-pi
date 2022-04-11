CREATE DATABASE smart_sac;

CREATE TABLE empresas(
	id_empresa 			INT PRIMARY KEY AUTO_INCREMENT,
    nome_empresa		VARCHAR(45),
    cnpj				VARCHAR(16),
    email				VARCHAR(45),
    senha				VARCHAR(45),
    telefone			VARCHAR(15),
    logradouro			VARCHAR(50),
    numero			 	VARCHAR(5),
    cep					VARCHAR(9),
    cidade				VARCHAR(30),
    estado				CHAR(2)
);

CREATE TABLE avaliacoes(
	id_avaliacoes		INT PRIMARY KEY AUTO_INCREMENT,
    nome_avaliador		VARCHAR(45),
    data_avaliacao		DATE,
    mensagem			VARCHAR(255),
    qtd_estrelas		INT,
    fk_empresa			INT,
	FOREIGN KEY (fk_empresa) REFERENCES empresas (id_empresa)
);

CREATE TABLE operacoes(
	id_operacao			INT PRIMARY KEY AUTO_INCREMENT,
    nome_operacao		VARCHAR(45),
    localidade			VARCHAR(20),
    nome_gerente		VARCHAR(45),
    tel_gerente			VARCHAR(15),
    email_gerente		VARCHAR(40),
    senha_gerente		VARCHAR(30),
    fk_empresa			INT,
    FOREIGN KEY (fk_empresa) REFERENCES empresas (id_empresa)
);

CREATE TABLE funcionarios (
	id_funcionario		INT PRIMARY KEY AUTO_INCREMENT,
    nome_funcionario	VARCHAR(45),
    cpf					VARCHAR(13),
    fk_operacao			INT,
    FOREIGN KEY (fk_operacao) REFERENCES operacoes (id_operacao)
);

CREATE TABLE maquinas (
	id_maquina			INT PRIMARY KEY AUTO_INCREMENT,
    hostname			VARCHAR(45),
    serial_maquina		VARCHAR(30),
    localidade_maquina	VARCHAR(30),
    fk_funcionario		INT,
    FOREIGN KEY (fk_funcionario) REFERENCES funcionarios (id_funcionario)
);

CREATE TABLE log_cliques (
	id_clique			INT PRIMARY KEY AUTO_INCREMENT,
    cliques				INT,
    data_clique			DATETIME,
    fk_maquina			INT,
    FOREIGN KEY (fk_maquina) REFERENCES maquinas (id_maquina) 
);

CREATE TABLE componentes (
	id_componente 		INT PRIMARY KEY AUTO_INCREMENT,
    nome_componente		VARCHAR(45),
    serie				VARCHAR(45),
    tipo				VARCHAR(30),
    fk_maquina			INT,
    FOREIGN KEY (fk_maquina) REFERENCES maquinas (id_maquina)
);

CREATE TABLE log_registros (
	id_registro			INT PRIMARY KEY AUTO_INCREMENT,
    uso					DOUBLE,
    disponivel			DOUBLE,
    frequencia			DOUBLE,
    tempo_ativo			DOUBLE,
    fk_componente		INT,
    FOREIGN KEY (fk_componente) REFERENCES componentes (id_componente)
    
);

CREATE TABLE log_alertas (
	id_log_alerta 		INT PRIMARY KEY AUTO_INCREMENT,
    codigo_urgencia		VARCHAR(30),
    descricao			VARCHAR(200),
    fk_registro			INT,
    FOREIGN KEY (fk_registro) REFERENCES log_registros (id_registro)
);

SHOW TABLES;