package brandao.gabriel.address.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class SQL {
    private static final Connection SQL_CONNECTION;

    static {
        SQL_CONNECTION = connect();
    }

    public SQL(){};

    private static Connection connect() {
        String url = "";
        String user = "";
        String password = "";
        try {
            Properties dbProps = getProp();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado no BD.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        props.load(SQL.class.getResourceAsStream("/dbproperties.properties"));
        return props;
    }

    public static Connection getConnection() {
        return SQL_CONNECTION;
    }
}
