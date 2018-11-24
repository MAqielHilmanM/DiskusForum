/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.daos;

import api.ApiConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.Helper;

/**
 *
 * @author maqielhm
 */
public class UserDao extends BaseDao<UserDao> {

    public static final String TABLE_NAME = "t_user";

    public static final String COLUMN_ID = "id_user";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_BIOGRAPH = "biograph";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_URL_COVER = "URL_Cover";
    public static final String COLUMN_URL_PHOTO = "URL_Photo";
    public static final String COLUMN_CREATED_DATE = "created_date";

    public static final String[] COLUMNS = {
        COLUMN_ID,
        COLUMN_NAME,
        COLUMN_GENDER,
        COLUMN_PHONE,
        COLUMN_BIOGRAPH,
        COLUMN_ADDRESS,
        COLUMN_COUNTRY,
        COLUMN_URL_COVER,
        COLUMN_URL_PHOTO,
        COLUMN_CREATED_DATE
    };

    private String id;
    private String name;
    private char gender;
    private long phone;
    private String biograph;
    private String address;
    private String country;
    private String url_cover;
    private String url_photo;
    private Date created_date;

    public UserDao() {
        super(TABLE_NAME, COLUMNS);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public long getPhone() {
        return phone;
    }

    public String getBiograph() {
        return biograph;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getUrl_cover() {
        return url_cover;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public Date getCreated_date() {
        return created_date;
    }

    @Override
    public List<UserDao> toObjects(ResultSet rs) {
        List<UserDao> users = new ArrayList<>();
        try {
            while (rs.next()) {
                this.id = rs.getString(COLUMN_ID);
                this.name = rs.getString(COLUMN_NAME);
                this.gender = rs.getString(COLUMN_GENDER).charAt(0);
                this.phone = rs.getLong(COLUMN_PHONE);
                this.biograph = rs.getString(COLUMN_BIOGRAPH);
                this.address = rs.getString(COLUMN_ADDRESS);
                this.country = rs.getString(COLUMN_COUNTRY);
                this.url_cover = rs.getString(COLUMN_URL_COVER);
                this.url_photo = rs.getString(COLUMN_URL_PHOTO);
                this.created_date = rs.getDate(COLUMN_CREATED_DATE);
                users.add(this);
            }
        } catch (SQLException ex) {
            System.err.println("Error code : " + ex.getMessage());
        }
        return users;
    }

}
