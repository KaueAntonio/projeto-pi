var sql = require("mssql");

var sqlServerConfig = {
  user: "smartsac",
  password: "#Paysandu",
  database: "bd-projeto-sac",
  server: "projeto-sac.database.windows.net",
  pool: {
    max: 10,
    min: 0,
    idleTimeoutMillis: 30000,
  },
  options: {
    encrypt: true,
  },
};

function executar(instrucao) {
  return new Promise(function (resolve, reject) {
    sql
      .connect(sqlServerConfig)
      .then(function () {
        return sql.query(instrucao);
      })
      .then(function (resultados) {
        console.log(resultados);
        resolve(resultados.recordset);
      })
      .catch(function (erro) {
        reject(erro);
        console.log("ERRO: ", erro);
      });
    sql.on("error", function (erro) {
      return "ERRO NO SQL SERVER (Azure): ", erro;
    });
  });
}

module.exports = {
  executar,
};
