import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final String url = "jdbc:postgresql://localhost:5432/postgres"; // database URL
    private final String user = "postgres"; // database username
    private final String password = "0000"; // database password
    private Connection connection;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

//    public Connection connect() throws SQLException {
//        return DriverManager.getConnection(url, user, password);
//    }
}
