export const danoHeroi = 20;
export const danoInimigo = 15;
export const vidaMaxima = 2147483647;

export function criarBatalha(vidaHeroi, vidaInimigo) {
  const vidas = [vidaHeroi, vidaInimigo];
  if (vidas.some((vida) => !Number.isInteger(vida) || vida < 1 || vida > vidaMaxima)) {
    throw new RangeError('As vidas devem ser números inteiros e positivos.');
  }

  return {
    vidaHeroi,
    vidaInimigo,
    inicialHeroi: vidaHeroi,
    inicialInimigo: vidaInimigo,
    turno: 0,
    vencedor: null,
  };
}

export function atacar(estado) {
  if (estado.vencedor) return estado;

  const vidaInimigo = Math.max(0, estado.vidaInimigo - danoHeroi);
  const vidaHeroi = vidaInimigo > 0 ? Math.max(0, estado.vidaHeroi - danoInimigo) : estado.vidaHeroi;
  let vencedor = null;
  if (vidaInimigo === 0) vencedor = 'heroi';
  else if (vidaHeroi === 0) vencedor = 'inimigo';

  return { ...estado, vidaHeroi, vidaInimigo, turno: estado.turno + 1, vencedor };
}
