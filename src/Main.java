
import api.ApiConnection;
import api.daos.AuthDao;
import api.daos.BaseDao;
import api.daos.CommunityDao;
import api.daos.MemberDao;
import api.daos.ThreadDao;
import api.query.ApiReadQuery;
import api.daos.UserDao;
import api.query.ApiDeleteQuery;
import api.query.ApiInsertQuery;
import api.query.ApiUpdateQuery;
import home.HomeController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.management.Query;
import auth.AuthController;
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
            new AuthController();

        
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            
        }

    }

}
