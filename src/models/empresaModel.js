var database = require("../database/config");

function cadastrar(nome, cnpj, senha) {
  var instrucao = `
    insert into empresa(nome, cnpj, senha) values
    ('${nome}', '${cnpj}', '${senha}');
    `;
  console.log("Listando Usuários");
  return database.executar(instrucao);
}

function editar(nome, email, senha){
  var instrucao = 
  `UPDATE tabela SET email = "${email}";
  UPDATE tabela SET senha = "${senha}";
  UPDATE tabela SET usuario = "${nome}";`;
  return database.executar(instrucao);
}

function excluir(email, senha){
  var instrucao =
  `DELETE FROM tabela WHERE email = ${email} AND senha = ${senha}`;
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