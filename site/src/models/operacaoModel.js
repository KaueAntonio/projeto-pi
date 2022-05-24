var database = require("../database/config");

function adicionar(
  nome_operacao,
  nome_gerente,
  email_gerente,
  senha_gerente,
  localidade,
  id_empresa
) {
  var instrucao = `
    INSERT INTO [dbo].[operacoes](nome_operacao, localidade, nome_gerente, email_gerente, senha_gerente, fk_empresa) VALUES 
    ('${nome_operacao}', '${localidade}', '${nome_gerente}', '${email_gerente}', '${senha_gerente}', '${id_empresa}');
    `;
  return database.executar(instrucao);
}

function editar(
    nome_operacao,
    nome_gerente,
    email_gerente,
    senha_gerente,
    localidade,
    id_empresa,
    idOperacao
  ) {
    var instrucao = 
  `UPDATE [dbo].[operacoes] SET nome_operacao = '${nome_operacao}' WHERE id_operacao = ${idOperacao};`;
  database.executar(instrucao);

  var instrucao = 
  `UPDATE [dbo].[operacoes] SET localidade = '${localidade}' WHERE id_operacao = ${idOperacao};`;
  database.executar(instrucao);

  var instrucao = 
  `UPDATE [dbo].[operacoes] SET nome_gerente = '${nome_gerente}' WHERE id_operacao = ${idOperacao};`;
  database.executar(instrucao);

  var instrucao = 
  `UPDATE [dbo].[operacoes] SET email_gerente = '${email_gerente}' WHERE id_operacao = ${idOperacao};`;
  database.executar(instrucao);

  var instrucao = 
  `UPDATE [dbo].[operacoes] SET senha_gerente = '${senha_gerente}' WHERE id_operacao = ${idOperacao};`;
  return database.executar(instrucao);
  }

  function excluir(
    nome_operacao,
    nome_gerente,
    email_gerente,
    senha_gerente,
    localidade,
    id_empresa,
    idOperacao
  ) {
    var instrucao = `DELETE FROM [dbo].[log_registros] WHERE fk_maquina = (SELECT )`;
    var instrucao = `DELETE FROM [dbo].[maquinas] WHERE fk_operacao = ${idOperacao}`;
    database.executar(instrucao);
    var instrucao = `
      DELETE FROM [dbo].[operacoes] WHERE id_operacao = '${idOperacao}' AND email_gerente = '${email_gerente}' AND senha_gerente = '${senha_gerente}' AND fk_empresa = '${id_empresa}';
      `;
    return database.executar(instrucao);
  }

  function listar() {
    var instrucao = `
      SELECT * FROM [dbo].[operacoes];
      `;
    console.log("Listando Usuários");
    return database.executar(instrucao);
  }

module.exports = {
  adicionar,
  excluir,
  editar,
  listar
};
