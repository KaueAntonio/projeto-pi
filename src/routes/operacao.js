var express = require("express");
var router = express.Router();

var operacaoController = require("../controllers/operacaoController");

router.post("/adicionar", function (req, res) {
  operacaoController.adicionar(req, res);
});

router.post("/excluir", function (req, res) {
  operacaoController.excluir(req, res);
});

router.post("/editar", function (req, res) {
  operacaoController.editar(req, res);
});

router.get("/listar", function (req, res) {
    operacaoController.listar(req, res);
  });

module.exports = router;
