package projeto01poo;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PrincipalMain {
    private static final int ATAQUE_HEROI = 20;
    private static final int ATAQUE_INIMIGO = 15;

    public static void main(String[] args) {
        try (Scanner leia = new Scanner(System.in, StandardCharsets.UTF_8)) {
            jogar(leia);
        }
    }

    private static void jogar(Scanner leia) {
        Integer vidaHeroi = lerInteiro(leia, "Digite a vida do herói:", 1, Integer.MAX_VALUE);
        if (vidaHeroi == null) return;

        Integer vidaInimigo = lerInteiro(leia, "Digite a vida do inimigo:", 1, Integer.MAX_VALUE);
        if (vidaInimigo == null) return;

        Personagem jogador = new Personagem("Herói", vidaHeroi, ATAQUE_HEROI);
        Personagem inimigo = new Personagem("Inimigo", vidaInimigo, ATAQUE_INIMIGO);

        while (jogador.estarVivo() && inimigo.estarVivo()) {
            System.out.println("Vida do herói: " + jogador.getVida());
            System.out.println("Vida do inimigo: " + inimigo.getVida());

            Integer opcao = lerInteiro(leia, "Digite 1 para atacar:", 1, 1);
            if (opcao == null) return;

            jogador.atacar(inimigo);
            if (inimigo.estarVivo()) {
                inimigo.atacar(jogador);
            }
        }

        System.out.println("Batalha encerrada!");
        jogador.mostrarStatus();
        inimigo.mostrarStatus();
        System.out.println(jogador.estarVivo() ? "Herói venceu!" : "Inimigo venceu!");
        salvarResultado(jogador.estarVivo() ? "Heroi" : "inimigo");
    }

    private static Integer lerInteiro(Scanner leia, String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.println(mensagem);
            if (!leia.hasNext()) {
                System.out.println("Batalha cancelada.");
                return null;
            }

            if (leia.hasNextInt()) {
                int valor = leia.nextInt();
                if (valor >= minimo && valor <= maximo) return valor;
            } else {
                leia.next();
            }

            System.out.println(minimo == maximo ? "Opção inválida." : "Digite uma vida inteira e positiva.");
        }
    }

    private static void salvarResultado(String vencedor) {
        if (!Conexao.estaConfigurada()) return;

        String sql = "INSERT INTO resultado (Vencedor) VALUES (?)";
        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, vencedor);
            comando.executeUpdate();
            System.out.println("Resultado salvo.");
        } catch (SQLException erro) {
            System.out.println("Não foi possível salvar o resultado no banco.");
        }
    }
}
