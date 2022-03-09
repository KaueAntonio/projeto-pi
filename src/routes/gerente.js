var express = require("express");
var router = express.Router();

var gerenteController = require("../controllers/gerenteController");

router.get("/cadastrar", function (req, res) {
  gerenteController.cadastrar(req, res);
});

module.exports = router;
