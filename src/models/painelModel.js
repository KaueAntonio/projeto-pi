var database = require("../database/config");

function getDados() {
  var instrucao = "SELECT * FROM [dbo].[operacoes]";
  return database.executar(instrucao);
}

function getCliques() {
  var instrucao = "SELECT * FROM [dbo].[log_cliques]";
  return database.executar(instrucao);
}

function getRegistros() {
  var instrucao = "SELECT * FROM [dbo].[log_registros]";
  return database.executar(instrucao);
}

module.exports = {
  getDados,
  getCliques,
  getRegistros
};
