import api.ApiConnection;
import api.query.ApiReadQuery;
import api.daos.UserDao;
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
            
            ApiReadQuery apiQuery = new ApiReadQuery(UserDao.TABLE_NAME);
            ResultSet rs = apiQuery.showAllColumn().execute();
            List<UserDao> users = new ArrayList<>();
            users = new UserDao().toObjects(rs);
            
            System.out.println(users.size());
            System.out.println(users.get(0).getAddress());
            
            ApiConnection.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
