package projeto01poo;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        if (!Conexao.estaConfigurada()) {
            System.out.println("Configure JOGO_JDBC_URL para testar a conexão.");
            return;
        }

        try (Connection conexao = Conexao.conectar()) {
            System.out.println(conexao.isValid(3) ? "Conectado com sucesso!" : "Conexão inválida.");
        } catch (SQLException erro) {
            System.out.println("Não foi possível conectar ao banco. Confira a configuração e o driver.");
        }
    }
}
