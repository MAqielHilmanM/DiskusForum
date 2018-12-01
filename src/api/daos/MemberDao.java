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
public class MemberDao extends BaseDao<MemberDao> {

    public static final String TABLE_NAME = "t_member";
    public static final String COLUMN_ID_MEMBER = "id_member";
    public static final String COLUMN_ID_COMMUNITY = "id_community";
    public static final String COLUMN_ID_PRIVILAGE = "id_privilages";
    public static final String COLUMN_JOIN_DATE = "join_date";
    public static final String[] COLUMNS = {
        COLUMN_ID_MEMBER,
        COLUMN_ID_COMMUNITY,
        COLUMN_ID_PRIVILAGE,
        COLUMN_JOIN_DATE
    };

    private String id_member;
    private String id_community;
    private String id_privilages;
    private Date joinDate;

    public MemberDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<MemberDao> toObjects(ResultSet rs) {
        super.setmResultSet(rs);
        List<MemberDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                id_member = rs.getString(COLUMN_ID_MEMBER);
                id_community = rs.getString(COLUMN_ID_COMMUNITY);
                id_privilages = rs.getString(COLUMN_ID_PRIVILAGE);
                joinDate = rs.getDate(COLUMN_JOIN_DATE);
                lists.add(this);
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e);
        }
        return lists;

    }

}
