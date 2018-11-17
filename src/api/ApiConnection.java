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

/**
 *
 * @author maqielhm
 */
public class ApiConnection {

    private static Connection api;

    public static void setConnection(String host, int port, String db, String user, String password) {
        api = createConnection(host, port, db, user, password);
        
    }

    public static Connection getConnection() {
        return api;
    }

    public static void closeConnection() {
        try {
            api.close();
        } catch (SQLException e) {
            System.out.println("Gagal Memutuskan koneksi : "+e.toString());
        }
    }
    
    public static boolean isConnected(){
        return api == null;
    }

    private static Connection createConnection(String host, int port, String db, String user, String password) {
        /* Declare and initialize a sql Connection variable. */
        Connection ret = null;

        try {

            /* Register for jdbc driver class. */
            Class.forName("com.mysql.jdbc.Driver");

            /* Create connection url. */
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

            /* Get the Connection object. */
            ret = DriverManager.getConnection(url, user, password);

            /* Get related meta data for this mysql server to verify db connect successfully.. */
            DatabaseMetaData dbmd = ret.getMetaData();

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

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return ret;
        }
    }

}
