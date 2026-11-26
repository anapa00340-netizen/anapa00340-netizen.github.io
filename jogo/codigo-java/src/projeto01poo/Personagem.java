package projeto01poo;

public class Personagem implements poderSombrio {
    private final String nome;
    private final int ataque;
    private int vida;

    public Personagem(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vida = Math.max(0, vida);
        this.ataque = Math.max(0, ataque);
    }

    public void atacar(Personagem inimigo) {
        if (inimigo == null) {
            System.out.println("Não há inimigo para atacar.");
            return;
        }
        if (!estarVivo()) {
            System.out.println(nome + " não pode atacar.");
            return;
        }
        if (!inimigo.estarVivo()) {
            System.out.println(inimigo.nome + " já foi derrotado.");
            return;
        }

        inimigo.receberDano(ataque);
        System.out.println(nome + " atacou " + inimigo.nome + ".");
    }

    public void receberDano(int dano) {
        if (dano > 0) vida = Math.max(0, vida - dano);
    }

    @Override
    public void receberDano() {
    }

    public boolean estarVivo() {
        return vida > 0;
    }

    public boolean estarMorto() {
        return vida == 0;
    }

    public void mostrarStatus() {
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Ataque: " + ataque);
    }

    public int getVida() {
        return vida;
    }
}
