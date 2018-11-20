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
public class CommentDao extends BaseDao<CommentDao> {

    public static final String TABLE_NAME = "t_comment";
    public static final String COLUMN_ID_COMMENT = "id_comment";
    public static final String COLUMN_ID_MEMBER = "id_member";
    public static final String COLUMN_ID_THREAD = "id_thread";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_LIKE = "like";
    public static final String COLUMN_CREATED_DATE = "created_date";
    public static final String[] COLUMNS = {
        COLUMN_ID_COMMENT,
        COLUMN_ID_MEMBER,
        COLUMN_ID_THREAD,
        COLUMN_COMMENT,
        COLUMN_LIKE,
        COLUMN_CREATED_DATE
    };

    private String id_comment;
    private String id_member;
    private String id_thread;
    private String comment;
    private String like;
    private Date created_date;

    public CommentDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<CommentDao> toObjects(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
