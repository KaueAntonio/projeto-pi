const dados = [{
        estrelinhas: 5,
        titulo: "Incredible!",
        mensagem: "Best solution on the market!!",
        usuario: "Mark",
        nomeempresa: "Facebook",
        dia: 10,
        mes: 03,
        ano: 2022,
    },
    {
        estrelinhas: 4,
        titulo: "Very good!!",
        mensagem: "My company leveraged too much after product implementation!",
        usuario: "Jeff Bezos",
        nomeempresa: "Amazon",
        dia: 30,
        mes: 01,
        ano: 2022,
    },
    {
        estrelinhas: 4,
        titulo: "Surpresa!!",
        mensagem: "Não achei que faria uma diferença tão grande nos resultados da empresa!",
        usuario: "Luiza Helena",
        nomeempresa: "Magazine Luiza",
        dia: 23,
        mes: 04,
        ano: 2022,
    },
    {
        estrelinhas: 5,
        titulo: "Excited!",
        mensagem: "I just implemented the product in the company, and I'm already seeing results!",
        usuario: "Bill Gates",
        nomeempresa: "Microsoft",
        dia: 17,
        mes: 04,
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
    let mesConcatenado;
    let diaConcatenado;
    if (arquivo.dia < 10) {
        diaConcatenado = "0" + arquivo.dia;
    } else {
        diaConcatenado = arquivo.dia;
    }
    if (arquivo.mes < 10) {
        mesConcatenado = "0" + arquivo.mes;
    } else {
        mesConcatenado = arquivo.mes;
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
     ${diaConcatenado}/${mesConcatenado}/${arquivo.ano}</h4>
    </div><br><br>`;
}