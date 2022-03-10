var database = require("../database/config");

function cadastrar(nome, email, senha) {
  var instrucao = `
    insert into tabela(nome, email, senha) values
    ('${nome}', '${email}', '${senha}');
    `;
  console.log("Listando Usuários");
  return database.executar(instrucao);
}

function editar(){
  var instrucao = 
  `UPDATE tabela SET email = "";
  UPDATE tabela SET senha = "";
  UPDATE tabela SET usuario = "";`;
  return database.executar(instrucao);
}

function excluir(){
  var instrucao =
  `DELETE FROM tabela WHERE id = ${2}`;
  return database.executar(instrucao);
}

function login(){
  var instrucao = 
  `SELECT FROM TABELA where id = ${2}`;
  return database.executar(instrucao);
}

module.exports = {
  cadastrar,
  editar,
  excluir,
};