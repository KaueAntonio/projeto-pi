var express = require("express");
var router = express.Router();

var empresaController = require("../controllers/empresaController");

router.get("/cadastrar", function (req, res) {
  empresaController.cadastrar(req, res);
});

router.get("/listar", function (req, res) {
  empresaController.listar(req, res);
});

router.get("/excluir", function (req, res) {
  empresaController.excluir(req, res);
});

router.get("/entrar", function (req, res) {
  empresaController.entrar(req, res);
});

module.exports = router;
