/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maqielhm
 */
public class AuthDao extends BaseDao<AuthDao> {

    public static final String TABLE_NAME = "t_auth";
    public static final String COLUMN_ID = "id_user";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_MODIFIED_DATE = "modified_date";
    public static final String[] COLUMNS = {
        COLUMN_ID,
        COLUMN_EMAIL,
        COLUMN_USERNAME,
        COLUMN_PASSWORD,
        COLUMN_MODIFIED_DATE
    };
    
    private String id;
    private String email;
    private String username;
    private String password;
    private Date modifiedDate;

    public AuthDao() {
        super(TABLE_NAME, COLUMNS);
    }
    
    @Override
    public List<AuthDao> toObjects(ResultSet rs) {
        List<AuthDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                id = rs.getString(COLUMN_ID);
                email = rs.getString(COLUMN_EMAIL);
                username = rs.getString(COLUMN_USERNAME);
                password = rs.getString(COLUMN_PASSWORD);
                modifiedDate = rs.getDate(COLUMN_MODIFIED_DATE);
                lists.add(this);
            }
        } catch (Exception e) {
            System.err.println("ERROR :" + e);
        }
        return lists;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

}
