const express = require("express");
const http = require('http');
const app = express();
const server = http.createServer(app);
const { Server } = require("socket.io");
const io = new Server(server);
const cors = require("cors");
const path = require("path");
const PORTA = 8080;


const indexRouter = require("./src/routes/index");
const usuarioRouter = require("./src/routes/usuarios");
const empresaRouter = require("./src/routes/empresa");
const operacaoRouter = require("./src/routes/operacao");
const painelRouter = require("./src/routes/painel");
const gerenteRouter = require("./src/routes/gerente");

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, "public")));

app.use(cors());

app.use("/", indexRouter);
app.use("/usuarios", usuarioRouter);
app.use("/empresa", empresaRouter);
app.use("/operacao", operacaoRouter);
app.use("/painel", painelRouter);
app.use("/gerente", gerenteRouter);

io.on('connection', (socket) => {
  console.log('a user connected');
});

server.listen(PORTA, function () {
  console.log(`Aplicação Rodando`);
});
