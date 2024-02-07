import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String url = "jdbc:postgresql://localhost:5432/postgres"; // Replace with your database URL
    private final String user = "postgres"; // Replace with your database username
    private final String password = "0000"; // Replace with your database password

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
