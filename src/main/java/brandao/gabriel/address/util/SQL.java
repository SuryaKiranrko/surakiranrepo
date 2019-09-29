package brandao.gabriel.address.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQL {
    private static final Connection SQL_CONNECTION;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        URL = "jdbc:postgresql://localhost:5432/";
        USER = "postgres";
        PASSWORD = "";
        SQL_CONNECTION = connect();
    }

    public SQL(){};

    private static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            System.out.println(URL);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado no BD.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static Connection getConnection() {
        return SQL_CONNECTION;
    }
}
