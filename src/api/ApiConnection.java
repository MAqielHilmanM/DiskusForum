/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maqielhm
 */
public class ApiConnection {

    private static Connection api;
    private static String host;
    private static int port;
    private static String db;
    private static String user;
    private static String pass;

    public static void setConnection(String hosts, int ports, String database, String username, String password) {
        host = hosts;
        port = ports;
        db = database;
        user = username;
        pass = password;
    }

    public static Connection getConnection() {
        return api;
    }

    public static void closeConnection() {
        try {
            api.close();
        } catch (SQLException e) {
            System.out.println("Gagal Memutuskan koneksi : " + e.toString());
        }
    }

    public static boolean isConnected() {
        try {
            return api != null || !api.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(ApiConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean hasSet() {
        if (host != null && port != 0 && db != null && user != null && pass != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void createConnection() {
        try {
            /* Register for jdbc driver class. */
            Class.forName("com.mysql.jdbc.Driver");

            /* Create connection url. */
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

            /* Get the Connection object. */
            api = DriverManager.getConnection(url, user, pass);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getApiInformation() {
        /* Get related meta data for this mysql server to verify db connect successfully.. */
        try {

            DatabaseMetaData dbmd = api.getMetaData();

            String dbName = dbmd.getDatabaseProductName();

            String dbVersion = dbmd.getDatabaseProductVersion();

            String dbUrl = dbmd.getURL();

            String userName = dbmd.getUserName();

            String driverName = dbmd.getDriverName();

            System.out.println("Database Name is " + dbName);

            System.out.println("Database Version is " + dbVersion);

            System.out.println("Database Connection Url is " + dbUrl);

            System.out.println("Database User Name is " + userName);

            System.out.println("Database Driver Name is " + driverName);

        } catch (Exception e) {
            System.err.println("ERROR LOAD INFORMATION : " + e);
        }
    }
}
