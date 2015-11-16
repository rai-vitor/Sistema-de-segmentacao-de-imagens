package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * classe ainda não utilizada. Feita para eu me lembrar de corrigir ela se der tempo.
 */
public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:seg.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}