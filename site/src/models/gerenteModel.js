var database = require("../database/config");

function cadastrar(nome, email, senha) {
  var instrucao = `
    insert into tabela(nome, email, senha) values
    ('${nome}', '${email}', '${senha}');
    `;
  return database.executar(instrucao);
}

function login(email, senha, idEmpresa){
  var instrucao = `SELECT * FROM [dbo].[operacoes] WHERE email_gerente = '${email}' AND senha_gerente = '${senha}' AND fk_empresa = '${idEmpresa}';`;
  return database.executar(instrucao);
}

module.exports = {
  cadastrar,
  login
};
