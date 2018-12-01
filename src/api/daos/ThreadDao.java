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
public class ThreadDao extends BaseDao<ThreadDao> {

    public static final String TABLE_NAME = "t_thread";
    public static final String COLUMN_ID = "id_thread";
    public static final String COLUMN_ID_COMMUNITY = "id_community";
    public static final String COLUMN_ID_MEMBER = "id_member";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_BODY = "body";
    public static final String COLUMN_TOTAL_LIKE = "total_like";
    public static final String COLUMN_TOTAL_COMMENT = "total_comment";
    public static final String COLUMN_CREATED_DATE = "created_date";
    public static final String[] COLUMNS = {
        COLUMN_ID,
        COLUMN_ID_COMMUNITY,
        COLUMN_ID_MEMBER,
        COLUMN_TITLE,
        COLUMN_BODY,
        COLUMN_TOTAL_LIKE,
        COLUMN_TOTAL_COMMENT,
        COLUMN_CREATED_DATE
    };
    
    private String id;
    private String id_community;
    private String id_member;
    private String title;
    private String body;
    private int likeCount;
    private int commentCount;
    private Date createdDate;

    public ThreadDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<ThreadDao> toObjects(ResultSet rs) {
        super.setmResultSet(rs);
        List<ThreadDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                id = rs.getString(COLUMN_ID);
                id_community = rs.getString(COLUMN_ID_COMMUNITY);
                id_member = rs.getString(COLUMN_ID_MEMBER);
                title = rs.getString(COLUMN_TITLE);
                body = rs.getString(COLUMN_TITLE);
                likeCount = rs.getInt(COLUMN_TOTAL_LIKE);
                commentCount = rs.getInt(COLUMN_TOTAL_COMMENT);
                createdDate = rs.getDate(COLUMN_CREATED_DATE);
                lists.add(this);
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e);
        }
        return lists;
    
    }

    public String getId() {
        return id;
    }

    public String getId_community() {
        return id_community;
    }

    public String getId_member() {
        return id_member;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }
    

}
