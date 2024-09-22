package org.example.singleton;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static Optional<Connection> connection = Optional.empty();

    // Load environment variables
    private static final Dotenv dotenv = Dotenv.load();

    public static Connection getConnectionOrThrow() throws SQLException {
        if (connection.isEmpty() || connection.get().isClosed()) {
            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");

            try {
                connection = Optional.ofNullable(DriverManager.getConnection(url, user, password));
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection", ex);
                throw ex;
            }
        }

        return connection.orElseThrow(() -> new SQLException("Unable to establish a database connection"));
    }

    // Optional: Add a method to explicitly close the connection
    public static void closeConnection() {
        connection.ifPresent(conn -> {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    LOGGER.log(Level.INFO, "Database connection closed successfully");
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Failed to close the database connection", ex);
            }
        });
        connection = Optional.empty();
    }
}
