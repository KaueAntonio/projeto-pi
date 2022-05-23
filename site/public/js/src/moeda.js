export default (valor, parametro) => {
  if (valor !== null && valor !== undefined) {
    return valor.toLocaleString('pt-BR', { minimumFractionDigits: parametro })
  } else {
    return valor
  }
}
