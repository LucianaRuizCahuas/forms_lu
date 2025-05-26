package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {


    // La URL debe incluir el prefijo "jdbc:mysql://" y el puerto ":3306/"
    private static final String URL = "jdbc:mysql://dbpractica.cfic2uquytl7.us-east-1.rds.amazonaws.com:3306/dbVulcanizadora";
    // -------------------------

    private static final String USER = "admin"; // Reemplazar con tu nombre de usuario de MySQL
    private static final String PASSWORD = "23032007lu"; // Reemplazar con tu contraseña de MySQL
    private static Connection connection;

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Class.forName("com.mysql.cj.jdbc.Driver"); // No siempre es estrictamente necesario con JDBC 4.0+
                // pero no está de más dejarlo si no causa problemas.
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión a la base de datos establecida correctamente.");
            }
        } catch (SQLException e) {
            // Este es el error que te dirá exactamente por qué falla la conexión ahora.
            // Es CRUCIAL que leas el mensaje detallado de 'e.getMessage()'
            System.err.println("Error SQL al conectar a la base de datos: " + e.getMessage()); // Imprimir en consola para depuración
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.\n" + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            connection = null;
        }
        return connection;
    }


    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("Prueba de conexión exitosa!");
            // No cerrar la conexión aquí
        } else {
            System.out.println("Prueba de conexión fallida.");
        }
    }
}


