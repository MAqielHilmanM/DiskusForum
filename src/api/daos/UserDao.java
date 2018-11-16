/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.daos;

import api.ApiConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import utils.Tools;

/**
 *
 * @author maqielhm
 */
public class UserDao extends BaseDao<UserDao>{
    public static final String TABLE_NAME = "t_user";
    public static final String[] COLUMNS = {"id_user","nama"};
    
    public static final String COLUMN_ID = "id_user";
    public static final String COLUMN_NAME = "nama";

    public UserDao() {
        super(TABLE_NAME, COLUMNS);
    }

    
    @Override
    protected UserDao toObject(ResultSet rs) {
        return this;
    }    
   
}
