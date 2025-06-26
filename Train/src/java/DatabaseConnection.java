import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:mysql://localhost:3306/ticket_reservation"; // Corrected Port
        String dbUser = "root";
        String dbPass = "password";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            return DriverManager.getConnection(dbURL, dbUser, dbPass);
        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
            throw e;
        }
    }
}
