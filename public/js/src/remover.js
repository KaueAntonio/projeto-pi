export default (parametro, valor) => {
  if (parametro === 'cpfCnpj') {
    return valor.replace(/[.]/g, '').replace('/', '').replace('-', '')
  } else if (parametro === 'cep') {
    return valor.replace('.', '').replace('-', '')
  } else if (parametro === 'moeda') {
    return valor.replace(/[.]/g, '').replace(',', '.').trim()
  } else if (parametro === 'telefone') {
    return valor.replace('-', '')
  } else {
    return valor
  }
}
