var express = require("express");
var router = express.Router();

var empresaController = require("../controllers/empresaController");

router.get("/cadastrar", function (req, res) {
  empresaController.cadastrar(req, res);
});

router.get("/listar", function (req, res) {
  empresaController.listar(req, res);
});

module.exports = router;
