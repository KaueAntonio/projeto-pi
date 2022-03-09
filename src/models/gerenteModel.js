var database = require("../database/config");

function cadastrar(nome, email, senha) {
  var instrucao = `
    insert into tabela(nome, email, senha) values
    ('${nome}', '${email}', '${senha}');
    `;
  console.log("Listando Usuários");
  return database.executar(instrucao);
}

module.exports = {
  cadastrar,
};
