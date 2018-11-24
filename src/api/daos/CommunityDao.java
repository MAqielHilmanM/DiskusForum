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
public class CommunityDao extends BaseDao<CommunityDao> {

    public static final String TABLE_NAME = "t_community";
    public static final String COLUMN_ID = "id_community";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_THREAD_COUNT = "total_thread";
    public static final String COLUMN_MEMBER_COUNT = "total_member";
    public static final String COLUMN_CREATED_DATE = "created_date";
    public static final String[] COLUMNS = {
        COLUMN_ID,
        COLUMN_TITLE,
        COLUMN_DESC,
        COLUMN_THREAD_COUNT,
        COLUMN_MEMBER_COUNT,
        COLUMN_CREATED_DATE
    };

    private String id;
    private String title;
    private String desc;
    private int total_thread;
    private int total_member;
    private Date createdDate;

    public CommunityDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<CommunityDao> toObjects(ResultSet rs) {
        List<CommunityDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                id = rs.getString(COLUMN_ID);
                title = rs.getString(COLUMN_TITLE);
                desc = rs.getString(COLUMN_DESC);
                total_thread = rs.getInt(COLUMN_THREAD_COUNT);
                total_member = rs.getInt(COLUMN_MEMBER_COUNT);
                createdDate = rs.getDate(COLUMN_CREATED_DATE);
                lists.add(this);
            }
        } catch (Exception e) {
            System.err.println("ERROR :" + e);
        }
        return lists;

    }

}
