var database = require("../database/config");

function getDados() {
  var instrucao = "SELECT * FROM [dbo].[operacoes]";
  return database.executar(instrucao);
}

function getCliques() {
  var instrucao = "SELECT * FROM [dbo].[log_cliques] JOIN [dbo].[maquinas] ON id_maquina = fk_maquina JOIN [dbo].[operacoes] ON id_operacao = fk_operacao";
  return database.executar(instrucao);
}

function getRegistros() {
  var instrucao = "SELECT * FROM [dbo].[log_registros]";
  return database.executar(instrucao);
}

function getMaquinas(){
  var instrucao = "SELECT * FROM [dbo].[maquinas] JOIN [dbo].[operacoes] ON id_operacao = fk_operacao";
  return database.executar(instrucao);
}

module.exports = {
  getDados,
  getCliques,
  getRegistros,
  getMaquinas
};
