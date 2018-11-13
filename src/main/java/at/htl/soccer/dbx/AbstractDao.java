package at.htl.soccer.dbx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hier werden die gemeinsamen Elemete aller Daos eingetragen
 */
public abstract class AbstractDao {

    protected static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    protected static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db;create=true";
    protected static final String USER = "app";
    protected static final String PASSWORD = "app";
    protected static Connection conn;

    public AbstractDao() {
        initJdbc();
    }

    protected void initJdbc() {
        try {
            Class.forName(DRIVER_STRING);
            conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Verbindung zur Datenbank nicht möglich:\n"
                    + e.getMessage() + "\n");
        }
    }

    protected void teardownJdbc() {
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();
                System.out.println("Goodbye!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
