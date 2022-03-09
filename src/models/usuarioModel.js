var database = require("../database/config");

function listar() {
  var instrucao = `
    select * from [dbo].[usuario_kaue] left join equipe on fkEquipe = idEquipe;
    `;
  console.log("Listando Usuários");
  return database.executar(instrucao);
}
module.exports = {
  listar,
};
