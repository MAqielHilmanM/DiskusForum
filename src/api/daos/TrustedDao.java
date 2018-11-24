/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.daos;

import static api.daos.VertificationDao.COLUMN_ID;
import static api.daos.VertificationDao.COLUMN_ISVERIFIED;
import static api.daos.VertificationDao.COLUMN_PHOTO;
import static api.daos.VertificationDao.COLUMN_VERIFIED_DATE;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maqielhm
 */
public class TrustedDao extends BaseDao<TrustedDao>{
    public static final String TABLE_NAME = "t_trusted";
    public static final String COLUMN_ID_TRUSTED = "id_trusted";
    public static final String COLUMN_ID_THREAD = "id_thread";
    public static final String COLUMN_ID_MEMBER = "id_member";
    public static final String COLUMN_IS_TRUSTED = "isTrusted";
    public static final String COLUMN_MODIFIED_DATE = "modified_date";
    public static final String[] COLUMNS = {
        COLUMN_ID_TRUSTED,
        COLUMN_ID_THREAD,
        COLUMN_ID_MEMBER,
        COLUMN_IS_TRUSTED,
        COLUMN_MODIFIED_DATE
    };

    private String idTrusted;
    private String idThread;
    private String idMember;
    private boolean isTrusted;
    private Date modifiedDate;

    public TrustedDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<TrustedDao> toObjects(ResultSet rs) {
        List<TrustedDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                idTrusted = rs.getString(COLUMN_ID_TRUSTED);
                idThread = rs.getString(COLUMN_ID_THREAD);
                idMember = rs.getString(COLUMN_ID_MEMBER);
                isTrusted = rs.getBoolean(COLUMN_IS_TRUSTED);
                modifiedDate = rs.getDate(COLUMN_MODIFIED_DATE);
                lists.add(this);
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e);
        }
        return lists;
    }
 
}
