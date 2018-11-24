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
    private int like;
    private Date created_date;

    public CommentDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<CommentDao> toObjects(ResultSet rs) {
        List<CommentDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                id_comment = rs.getString(COLUMN_COMMENT);
                id_member = rs.getString(COLUMN_ID_COMMENT);
                id_thread = rs.getString(COLUMN_ID_THREAD);
                comment = rs.getString(COLUMN_COMMENT);
                like = rs.getInt(COLUMN_LIKE);
                created_date = rs.getDate(COLUMN_CREATED_DATE);
                lists.add(this);
            }
        } catch (Exception e) {
            System.err.println("ERROR :" + e);
        }
        return lists;
    }

}
