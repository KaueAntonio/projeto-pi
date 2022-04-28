var express = require("express");
var router = express.Router();

var painelController = require("../controllers/painelController");

router.get("/getCliques", function (req, res) {
    painelController.getCliques(req, res);
});

router.get("/getRegistros", function (req, res) {
    painelController.getRegistros(req, res);
});

router.get("/getDados", function (req, res) {
    painelController.getDados(req, res);
});

router.get("/getMaquinas", function (req, res) {
    painelController.getMaquinas(req, res);
});

module.exports = router;
