const dados = [
    {
      estrelinhas: 4,
      titulo: "Muito Bom!!",
      mensagem: "Achei muito bom o projeto, gostei demais!",
      usuario: "Kaue",
      nomeempresa: "SAC Dos Bom",
      dia: 10,
      mes: 03,
      ano: 2022,
    },
    {
      estrelinhas: 2,
      titulo: "Muito Daora Mesmo!!",
      mensagem: "Daora Demais Parabéns!",
      usuario: "Juninho",
      nomeempresa: "Alguma Qualquer",
      dia: 30,
      mes: 01,
      ano: 2022,
    },
  ];

  let len = dados.length;
  for (let i = 0; i < len; i++) {
    let arquivo = dados[i];
    let estrelas = [
      "./Imagens/star.png",
      "./Imagens/star.png",
      "./Imagens/star.png",
      "./Imagens/star.png",
      "./Imagens/star.png",
    ];
    let est = arquivo.estrelinhas;
    for (let j = 0; j < est; j++) {
      estrelas[j] = "./Imagens/stary.png";
    }

    resultados.innerHTML += `
    <div class="container_avaliacao">
    <div class="container_estrelas" style="display: flex; align-items: center; flex-direction: row;">
    <img class="avaliacao_estrela" src="${estrelas[0]}" alt="">
    <img class="avaliacao_estrela" src="${estrelas[1]}" alt="">
    <img class="avaliacao_estrela" src="${estrelas[2]}" alt="">
    <img class="avaliacao_estrela" src="${estrelas[3]}" alt="">
    <img class="avaliacao_estrela" src="${estrelas[4]}" alt="">
    </div>
    <br>
    <b style="padding: 6px;">${arquivo.titulo}</b><br><br>
    ${arquivo.mensagem}<br><br>
    <h5>${arquivo.usuario} - ${arquivo.nomeempresa}
     ${arquivo.dia}/${arquivo.mes}/${arquivo.ano}</h4>
    </div><br><br>`;
  }