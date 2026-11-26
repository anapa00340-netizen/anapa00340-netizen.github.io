package projeto01poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static boolean estaConfigurada() {
        String url = System.getenv("JOGO_JDBC_URL");
        return url != null && !url.isBlank();
    }

    public static Connection conectar() throws SQLException {
        if (!estaConfigurada()) {
            throw new SQLException("Configure JOGO_JDBC_URL para conectar ao banco.");
        }

        String url = System.getenv("JOGO_JDBC_URL");
        String usuario = System.getenv("JOGO_JDBC_USUARIO");
        String senha = System.getenv("JOGO_JDBC_SENHA");
        if (usuario == null || usuario.isBlank()) {
            return DriverManager.getConnection(url);
        }
        return DriverManager.getConnection(url, usuario, senha == null ? "" : senha);
    }
}
