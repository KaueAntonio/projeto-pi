var database = require("../database/config");

function cadastrar(nome, cnpj, senha) {
  var instrucao = `
    insert into empresa(nome, cnpj, senha) values
    ('${nome}', '${cnpj}', '${senha}');
    `;
  console.log("Listando Usuários");
  return database.executar(instrucao);
}

function editar(nome, cnpj, senha, idEmpresa){
  var instrucao = 
  `UPDATE empresa SET cnpj = "${cnpj}" WHERE idEmpresa = ${idEmpresa};`;
  database.executar(instrucao);

  instrucao = `
  UPDATE empresa SET senha = "${senha}" WHERE idEmpresa = ${idEmpresa};`;
  database.executar(instrucao);

  instrucao = `
  UPDATE empresa SET nome = "${nome}" WHERE idEmpresa = ${idEmpresa};`;
  return database.executar(instrucao);
}

function excluir(nome, cnpj, senha, idEmpresa){
  var instrucao =
  `DELETE FROM empresa WHERE idEmpresa = ${idEmpresa};`;
  return database.executar(instrucao);
}

function login(nome, senha){
  var instrucao = 
  `SELECT * FROM empresa WHERE nome = '${nome}' AND senha = '${senha}'`;
  return database.executar(instrucao);
}

function listar(){
  var instrucao =
  `SELECT * FROM empresa;`
  return database.executar(instrucao);
}

module.exports = {
  cadastrar,
  editar,
  excluir,
  listar,
  login
};