var operacaoModel = require("../models/operacaoModel");

function adicionar(req, res) {
  var nome_operacao = req.body.nome;
  var nome_gerente = req.body.nome_gerente;
  var email_gerente = req.body.email_gerente;
  var senha_gerente = req.body.senha_gerente;
  var localidade = req.body.localidade;
  var id_empresa = req.body.idEmpresa;

  if (nome_operacao == undefined) {
    res.status(400).send("nome_operacao está undefined!");
  } else if (nome_gerente == undefined) {
    res.status(400).send("nome_gerente está undefined!");
  } else if (email_gerente == undefined) {
    res.status(400).send("email_gerente está undefined!");
  } else if (senha_gerente == undefined) {
    res.status(400).send("senha_gerente está undefined!");
  } else if (localidade == undefined) {
    res.status(400).send("localidade está undefined!");
  } else if (id_empresa == undefined) {
    res.status(400).send("id_empresa está undefined!");
  } else {
    operacaoModel
      .adicionar(
        nome_operacao,
        nome_gerente,
        email_gerente,
        senha_gerente,
        localidade,
        id_empresa
      )
      .then(function (resultado) {
        res.json(resultado);
      })
      .catch(function (erro) {
        console.log(erro);
        console.log(
          "\nHouve um erro ao realizar a adição! Erro: ",
          erro.sqlMessage
        );
        res.status(500).json(erro.sqlMessage);
      });
  }
}

function editar(req, res) {
  var nome_operacao = req.body.nome;
  var nome_gerente = req.body.nome_gerente;
  var email_gerente = req.body.email_gerente;
  var senha_gerente = req.body.senha_gerente;
  var localidade = req.body.localidade;
  var id_empresa = req.body.idEmpresa;
  var idOperacao = req.body.selectNome;

  if (nome_operacao == undefined) {
    res.status(400).send("nome_operacao está undefined!");
  } else if (nome_gerente == undefined) {
    res.status(400).send("nome_gerente está undefined!");
  } else if (email_gerente == undefined) {
    res.status(400).send("email_gerente está undefined!");
  } else if (senha_gerente == undefined) {
    res.status(400).send("senha_gerente está undefined!");
  } else if (localidade == undefined) {
    res.status(400).send("localidade está undefined!");
  } else if (id_empresa == undefined) {
    res.status(400).send("id_empresa está undefined!");
  } else if (idOperacao == undefined) {
    res.status(400).send("id_empresa está undefined!");
  } else{
    operacaoModel
      .editar(
        nome_operacao,
        nome_gerente,
        email_gerente,
        senha_gerente,
        localidade,
        id_empresa,
        idOperacao
      )
      .then(function (resultado) {
        res.json(resultado);
      })
      .catch(function (erro) {
        console.log(erro);
        console.log(
          "\nHouve um erro ao realizar a adição! Erro: ",
          erro.sqlMessage
        );
        res.status(500).json(erro.sqlMessage);
      });
  }
}

function excluir(req, res) {
  var nome_operacao = req.body.nome;
  var nome_gerente = req.body.nome_gerente;
  var email_gerente = req.body.email_gerente;
  var senha_gerente = req.body.senha_gerente;
  var localidade = req.body.localidade;
  var id_empresa = req.body.idEmpresa;

  if (nome_operacao == undefined) {
    res.status(400).send("nome_operacao está undefined!");
  } else if (nome_gerente == undefined) {
    res.status(400).send("nome_gerente está undefined!");
  } else if (email_gerente == undefined) {
    res.status(400).send("email_gerente está undefined!");
  } else if (senha_gerente == undefined) {
    res.status(400).send("senha_gerente está undefined!");
  } else if (localidade == undefined) {
    res.status(400).send("localidade está undefined!");
  } else if (id_empresa == undefined) {
    res.status(400).send("id_empresa está undefined!");
  } else {
    operacaoModel
      .excluir(
        nome_operacao,
        nome_gerente,
        email_gerente,
        senha_gerente,
        localidade,
        id_empresa
      )
      .then(function (resultado) {
        res.json(resultado);
      })
      .catch(function (erro) {
        console.log(erro);
        console.log("\nHouve um erro ao excluir! Erro: ", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
      });
  }
}

function listar(req, res) {
    operacaoModel
      .listar()
      .then(function (resultado) {
        if (resultado.length > 0) {
          res.status(200).json(resultado);
        } else {
          res.status(204).send("Nenhum resultado encontrado!");
        }
      })
      .catch(function (erro) {
        console.log(erro);
        console.log(
          "Houve um erro ao realizar a consulta! Erro: ",
          erro.sqlMessage
        );
        res.status(500).json(erro.sqlMessage);
      });
  }

module.exports = {
  adicionar,
  excluir,
  editar,
  listar
};
