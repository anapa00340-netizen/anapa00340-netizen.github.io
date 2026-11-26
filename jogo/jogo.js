import { atacar, criarBatalha, danoHeroi, danoInimigo, vidaMaxima } from './regras.js';

const formulario = document.querySelector('#preparacao');
const vidaHeroi = document.querySelector('#vida-inicial-heroi');
const vidaInimigo = document.querySelector('#vida-inicial-inimigo');
const combate = document.querySelector('#combate');
const barraHeroi = document.querySelector('#barra-heroi');
const barraInimigo = document.querySelector('#barra-inimigo');
const totalHeroi = document.querySelector('#total-heroi');
const totalInimigo = document.querySelector('#total-inimigo');
const mensagem = document.querySelector('#mensagem');
const botaoAtacar = document.querySelector('#atacar');
const botaoReiniciar = document.querySelector('#reiniciar');
const numero = new Intl.NumberFormat('pt-BR');

vidaHeroi.max = vidaMaxima;
vidaInimigo.max = vidaMaxima;
document.querySelector('#dano-heroi').textContent = danoHeroi;
document.querySelector('#dano-inimigo').textContent = danoInimigo;

let estado;

function mostrarVida(barra, total, vida, inicial) {
  barra.max = inicial;
  barra.value = vida;
  total.textContent = numero.format(vida) + ' / ' + numero.format(inicial);
}

function atualizarCombate() {
  mostrarVida(barraHeroi, totalHeroi, estado.vidaHeroi, estado.inicialHeroi);
  mostrarVida(barraInimigo, totalInimigo, estado.vidaInimigo, estado.inicialInimigo);

  botaoAtacar.disabled = estado.vencedor !== null;
  if (estado.vencedor === 'heroi') {
    mensagem.textContent = 'Herói venceu!';
  } else if (estado.vencedor === 'inimigo') {
    mensagem.textContent = 'Inimigo venceu!';
  } else if (estado.turno === 0) {
    mensagem.textContent = 'Batalha iniciada. É a vez do herói.';
  } else {
    mensagem.textContent = 'Turno ' + numero.format(estado.turno) + '. O inimigo contra-atacou.';
  }
}

formulario.addEventListener('submit', (evento) => {
  evento.preventDefault();
  estado = criarBatalha(vidaHeroi.valueAsNumber, vidaInimigo.valueAsNumber);
  formulario.hidden = true;
  combate.hidden = false;
  atualizarCombate();
  botaoAtacar.focus();
});

botaoAtacar.addEventListener('click', () => {
  estado = atacar(estado);
  atualizarCombate();
  if (estado.vencedor) botaoReiniciar.focus();
});

botaoReiniciar.addEventListener('click', () => {
  combate.hidden = true;
  formulario.hidden = false;
  vidaHeroi.focus();
});
