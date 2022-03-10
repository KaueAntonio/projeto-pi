var database = require("../database/config");

function cadastrar(nome, email, senha) {
  var instrucao = `
    insert into tabela(nome, email, senha) values
    ('${nome}', '${email}', '${senha}');
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

function login(email, senha){
  var instrucao = 
  `SELECT * FROM TABELA WHERE email = ${email} AND senha = ${senha}`;
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