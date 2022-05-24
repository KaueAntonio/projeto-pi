export default (valor) => {
  if (valor !== null && valor !== undefined && valor.length > 0) {
    return valor.replace(/(\d)(\d{4})$/, '$1-$2')
  } else {
    return valor
  }
}
