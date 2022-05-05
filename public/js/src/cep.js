export default (valor) => {
  if (valor !== null && valor !== undefined && valor.length > 0) {
    return valor.replace(/(\d{2})(\d{3})(\d{3})/, '$1.$2-$3')
  } else {
    return valor
  }
}
