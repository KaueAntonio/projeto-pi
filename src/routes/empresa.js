var express = require("express");
var router = express.Router();

var usuarioController = require("../controllers/empresaController");

router.get("/cadastrar", function (req, res) {
  usuarioController.cadastrar(req, res);
});

module.exports = router;
