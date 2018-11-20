/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.daos;

import java.sql.ResultSet;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
