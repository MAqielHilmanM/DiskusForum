/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.daos;

import java.util.Date;

/**
 *
 * @author maqielhm
 */
public class CommunityDao {
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

}
