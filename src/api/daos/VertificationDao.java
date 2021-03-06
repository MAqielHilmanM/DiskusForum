/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maqielhm
 */
public class VertificationDao extends BaseDao<VertificationDao> {

    public static final String TABLE_NAME = "t_verification";
    public static final String COLUMN_ID = "id_user";
    public static final String COLUMN_PHOTO = "url_photo";
    public static final String COLUMN_ISVERIFIED = "isVerified";
    public static final String COLUMN_VERIFIED_DATE = "verified_date";
    public static final String[] COLUMNS = {
        COLUMN_ID,
        COLUMN_PHOTO,
        COLUMN_ISVERIFIED,
        COLUMN_VERIFIED_DATE
    };

    private String id;
    private String url_photo;
    private boolean isVerified;
    private Date verifiedDate;

    public VertificationDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<VertificationDao> toObjects(ResultSet rs) {
        super.setmResultSet(rs);
        List<VertificationDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                id = rs.getString(COLUMN_ID);
                url_photo = rs.getString(COLUMN_PHOTO);
                isVerified = rs.getBoolean(COLUMN_ISVERIFIED);
                verifiedDate = rs.getDate(COLUMN_VERIFIED_DATE);
                lists.add(this);
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e);
        }
        return lists;
    }

}
