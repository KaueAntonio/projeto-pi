var database = require("../database/config");

function cadastrar(nome, cnpj, senha) {
  var instrucao = `
<<<<<<< HEAD
    insert into empresa(nome_empresa, cnpj, senha) values
=======
    insert into [dbo].[empresas](nome_empresa, cnpj, senha) values
>>>>>>> aa470e8befeb1f3307e2d58c1239acdb9f113c8d
    ('${nome}', '${cnpj}', '${senha}');
    `;
  console.log("Listando Usuários");
  return database.executar(instrucao);
}

function editar(nome, cnpj, senha, idEmpresa){
  var instrucao = 
<<<<<<< HEAD
  `UPDATE empresa SET cnpj = "${cnpj}" WHERE id_empresa = ${idEmpresa};`;
  database.executar(instrucao);

  instrucao = `
  UPDATE empresa SET senha = "${senha}" WHERE id_empresa = ${idEmpresa};`;
  database.executar(instrucao);

  instrucao = `
  UPDATE empresa SET nome_empresa = "${nome}" WHERE id_empresa = ${idEmpresa};`;
=======
  `UPDATE [dbo].[empresas] SET cnpj = "${cnpj}" WHERE id_empresa = ${idEmpresa};`;
  database.executar(instrucao);

  instrucao = `
  UPDATE [dbo].[empresas] SET senha = "${senha}" WHERE id_empresa = ${idEmpresa};`;
  database.executar(instrucao);

  instrucao = `
  UPDATE [dbo].[empresas] SET nome_empresa = "${nome}" WHERE id_empresa = ${idEmpresa};`;
>>>>>>> aa470e8befeb1f3307e2d58c1239acdb9f113c8d
  return database.executar(instrucao);
}

function excluir(nome, cnpj, senha, idEmpresa){
  var instrucao =
<<<<<<< HEAD
  `DELETE FROM empresa WHERE id_empresa = ${idEmpresa};`;
=======
  `DELETE FROM [dbo].[empresas] WHERE id_empresa = ${idEmpresa};`;
>>>>>>> aa470e8befeb1f3307e2d58c1239acdb9f113c8d
  return database.executar(instrucao);
}

function login(nome, senha){
  var instrucao = 
<<<<<<< HEAD
  `SELECT * FROM empresa WHERE nome_empresa = '${nome}' AND senha = '${senha}'`;
=======
  `SELECT * FROM [dbo].[empresas] WHERE nome_empresa = '${nome}' AND senha = '${senha}'`;
>>>>>>> aa470e8befeb1f3307e2d58c1239acdb9f113c8d
  return database.executar(instrucao);
}

function listar(){
  var instrucao =
  `SELECT * FROM [dbo].[empresas];`
  return database.executar(instrucao);
}

module.exports = {
  cadastrar,
  editar,
  excluir,
  listar,
  login
};