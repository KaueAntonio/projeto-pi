CREATE DATABASE smart_sac;
USE smart_sac;

CREATE TABLE empresas (
	id_empresa 				INT PRIMARY KEY AUTO_INCREMENT,
    nome_empresa			VARCHAR(45),
    cnpj					VARCHAR(15),
    email					VARCHAR(50),
	senha					VARCHAR(45),
    telefone				VARCHAR(15),
    logradouro				VARCHAR(80),
    numero					INT,
    cep						VARCHAR(9),
    cidade					VARCHAR(30),
    estado					CHAR(2)
    );
    
CREATE TABLE avaliacoes (
	id_avaliacao			INT PRIMARY KEY AUTO_INCREMENT,
    nome_avaliador			VARCHAR(55),
    mensagem				VARCHAR(255),
    data_avaliacao			DATE,
    qtd_estrelas			INT,
    fk_empresa				INT,
    FOREIGN KEY (fk_empresa) REFERENCES empresas (id_empresa)
    );
    
CREATE TABLE operacoes (
	id_operacao				INT PRIMARY KEY AUTO_INCREMENT,
    nome_operacao			VARCHAR(45),
    localidade				VARCHAR(20),
    nome_gerente			VARCHAR(45),
    tel_gerente				VARCHAR(15),
    email_gerente			VARCHAR(50),
    senha_gerente			VARCHAR(45),
    fk_empresa				INT,
    FOREIGN KEY (fk_empresa) REFERENCES empresas (id_empresa)
    );
    
CREATE TABLE maquinas (
	id_maquina 				INT PRIMARY KEY AUTO_INCREMENT,
    hostname				VARCHAR(45),
    serial_maquina			VARCHAR(45),
    localidade_maquina		VARCHAR(30),
    fk_operacao				INT,
    FOREIGN KEY (fk_operacao) REFERENCES operacoes (id_operacao)
    );

CREATE TABLE log_cliques (
	id_clique 				INT PRIMARY KEY AUTO_INCREMENT,
    cliques					VARCHAR(15),
    data_clique				DATE,
    fk_maquina				INT,
    FOREIGN KEY (fk_maquina) REFERENCES maquinas (id_maquina)
    );
    
CREATE TABLE componentes (
	id_componente			INT PRIMARY KEY AUTO_INCREMENT,
    tipo					VARCHAR(15)
    );

CREATE TABLE log_registros (
	id_log_registro 		INT PRIMARY KEY auto_increment,
    uso						DOUBLE,
    disponivel				DOUBLE,
    frequencia				DOUBLE,
    fk_componente			INT,
    FOREIGN KEY (fk_componente) REFERENCES componentes (id_componente),
    fk_maquina				INT,
    FOREIGN KEY (fk_maquina) REFERENCES maquinas (id_maquina),
      data_hora				DATETIME
      );
      
CREATE TABLE log_alertas (
	id_log_alerta			INT PRIMARY KEY AUTO_INCREMENT,
    codigo_urgencia			VARCHAR(15),
    descricao				VARCHAR(255),
    fk_registro				INT,
    FOREIGN KEY (fk_registro) REFERENCES log_registros (id_log_registro)
    );