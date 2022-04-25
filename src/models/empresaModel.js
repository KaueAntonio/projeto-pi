var database = require("../database/config");

function cadastrar(nome, cnpj, senha) {
  var instrucao = `
    insert into empresa(nome_empresa, cnpj, senha) values
    ('${nome}', '${cnpj}', '${senha}');
    `;
  console.log("Listando Usuários");
  return database.executar(instrucao);
}

function editar(nome, cnpj, senha, idEmpresa){
  var instrucao = 
  `UPDATE empresa SET cnpj = "${cnpj}" WHERE id_empresa = ${idEmpresa};`;
  database.executar(instrucao);

  instrucao = `
  UPDATE empresa SET senha = "${senha}" WHERE id_empresa = ${idEmpresa};`;
  database.executar(instrucao);

  instrucao = `
  UPDATE empresa SET nome_empresa = "${nome}" WHERE id_empresa = ${idEmpresa};`;
  return database.executar(instrucao);
}

function excluir(nome, cnpj, senha, idEmpresa){
  var instrucao =
  `DELETE FROM empresa WHERE id_empresa = ${idEmpresa};`;
  return database.executar(instrucao);
}

function login(nome, senha){
  var instrucao = 
  `SELECT * FROM empresa WHERE nome_empresa = '${nome}' AND senha = '${senha}'`;
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