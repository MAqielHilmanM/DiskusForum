import api.ApiConnection;
import api.daos.AuthDao;
import api.daos.BaseDao;
import api.query.ApiReadQuery;
import api.daos.UserDao;
import api.query.ApiDeleteQuery;
import api.query.ApiInsertQuery;
import api.query.ApiUpdateQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.management.Query;
import utils.Constant;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maqielhilmanm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {

            ApiConnection.setConnection(Constant.host, Constant.port, Constant.db, Constant.user, Constant.password);
            
            ApiReadQuery apiQuery = new ApiReadQuery(new AuthDao());
            List<AuthDao> data = apiQuery.showAllColumn().conditionEqual(AuthDao.COLUMN_USERNAME, "maqielhm").execute();
            System.out.println("email : "+data.get(0).getUsername());

//            List<AuthDao> lists = new ArrayList<>();
//            AuthDao authResult = new AuthDao();
//            ApiInsertQuery<AuthDao> apiAddQuery = new ApiInsertQuery();
//            Boolean results = apiAddQuery.
//                            defineTable(new AuthDao()).
//                            insertColumnValue(AuthDao.COLUMN_ID, "USER-00001").
//                            insertColumnValue(AuthDao.COLUMN_EMAIL, "aqiel@aqiel.com").
//                            insertColumnValue(AuthDao.COLUMN_USERNAME, "maqielhm").
//                            insertColumnValue(AuthDao.COLUMN_PASSWORD, "SHA1('aqiel')").
//                            execute();
//            
//            System.out.println(results?"Berhasil":"Gagal");

//            ApiDeleteQuery<AuthDao> apiDelete = new ApiDeleteQuery(new AuthDao());
//            Boolean results = apiDelete
//                    .conditionEqual(AuthDao.COLUMN_USERNAME, "maqielhm")
//                    .OR()
//                    .conditionEqual(AuthDao.COLUMN_EMAIL, "aqiel@aqiel.co")
//                    .execute();
//            System.out.println(results?"Berhasil":"Gagal");

//            ApiUpdateQuery<UserDao> apiUpdate = new ApiUpdateQuery<>();
//            Boolean results = apiUpdate
//                    .defineTable(new UserDao())
//                    .addColumn(UserDao.COLUMN_NAME, "Aqiel")
//                    .conditionEqual(UserDao.COLUMN_GENDER, "L")
//                    .AND()
//                    .conditionEqual(UserDao.COLUMN_COUNTRY, "Indonesia")
//                    .execute();
//            System.out.println(results?"Berhasil":"Gagal");

            ApiConnection.closeConnection();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
