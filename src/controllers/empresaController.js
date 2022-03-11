var empresaModel = require("../models/empresaModel");

function cadastrar(req, res) {
  var nome = req.body.nome;
  var cnpj = req.body.cnpj;
  var senha = req.body.senha;

  if (nome == undefined) {
      res.status(400).send("Seu nome está undefined!");
  } else if (cnpj == undefined) {
      res.status(400).send("Seu email está undefined!");
  } else if (senha == undefined) {
      res.status(400).send("Sua senha está undefined!");
  } else {
    empresaModel.cadastrar(nome, cnpj, senha)
      .then(
          function (resultado) {
              res.json(resultado);
          }
      ).catch(
          function (erro) {
              console.log(erro);
              console.log(
                  "\nHouve um erro ao realizar o cadastro! Erro: ",
                  erro.sqlMessage
              );
              res.status(500).json(erro.sqlMessage);
          }
      );
  }
}

function login(req, res) {
  var nome = req.body.nome;
  var senha = req.body.senha;

  if (nome == undefined) {
      res.status(400).send("Seu email está undefined!");
  } else if (senha == undefined) {
      res.status(400).send("Sua senha está undefined!");
  } else {
    empresaModel.login(nome, senha)
      .then(
          function (resultado) {
              res.json(resultado);
          }
      ).catch(
          function (erro) {
              console.log(erro);
              console.log(
                  "\nHouve um erro ao realizar o login! Erro: ",
                  erro.sqlMessage
              );
              res.status(500).json(erro.sqlMessage);
          }
      );
  }
}

function listar(req, res){
  empresaModel.listar();
}

function testar(req, res) {
  console.log("ENTRAMOS FUNCIONANDO");
  res.json("ESTAMOS FUNCIONANDO!");
}

module.exports = {
  cadastrar,
  listar,
  testar,
  login
};
