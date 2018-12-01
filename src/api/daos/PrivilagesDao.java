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
public class PrivilagesDao extends BaseDao<PrivilagesDao> {

    public static final String TABLE_NAME = "t_privileges";
    public static final String COLUMN_ID = "id_privileges";
    public static final String COLUMN_NAME = "privileges_name";
    public static final String COLUMN_CAN_ADD_THREAD = "canAddThread";
    public static final String COLUMN_CAN_DELETE_THREAD = "canDeleteThread";
    public static final String COLUMN_CAN_EDIT_THREAD = "canEditThread";
    public static final String COLUMN_CAN_ADD_MEMBER = "canAddMember";
    public static final String COLUMN_CAN_DELETE_MEMBER = "canDeleteMember";
    public static final String COLUMN_CAN_ADD_COMMENT = "canAddComment";
    public static final String COLUMN_CAN_REMOVE_COMMENT = "canRemoveComment";
    public static final String COLUMN_CAN_EDIT_COMMENT = "canEditComment";
    public static final String COLUMN_CAN_REMOVE_USER = "canRemoveUser";
    public static final String COLUMN_CAN_EDIT_USER = "canEditUser";
    public static final String COLUMN_CAN_ADD_COMMUNITY = "canAddCommunity";
    public static final String COLUMN_CAN_EDIT_COMMUNITY = "canEditCommunity";
    public static final String COLUMN_CAN_REMOVE_COMMUNITY = "canRemoveCommunity";
    public static final String[] COLUMNS = {
        COLUMN_ID,
        COLUMN_NAME,
        COLUMN_CAN_ADD_THREAD,
        COLUMN_CAN_DELETE_THREAD,
        COLUMN_CAN_EDIT_THREAD,
        COLUMN_CAN_ADD_MEMBER,
        COLUMN_CAN_DELETE_MEMBER,
        COLUMN_CAN_ADD_COMMENT,
        COLUMN_CAN_REMOVE_COMMENT,
        COLUMN_CAN_EDIT_COMMENT,
        COLUMN_CAN_REMOVE_USER,
        COLUMN_CAN_EDIT_USER,
        COLUMN_CAN_ADD_COMMUNITY,
        COLUMN_CAN_EDIT_COMMUNITY,
        COLUMN_CAN_REMOVE_COMMUNITY
    };

    private String id;
    private String name;
    private boolean canAddThread;
    private boolean canDeleteThread;
    private boolean canEditThread;
    private boolean canAddMember;
    private boolean canDeleteMember;
    private boolean canAddComment;
    private boolean canRemoveComment;
    private boolean canEditComment;
    private boolean canRemoveUser;
    private boolean canEditUser;
    private boolean canAddCommunity;
    private boolean canEditCommunity;
    private boolean canRemoveCommunity;

    public PrivilagesDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<PrivilagesDao> toObjects(ResultSet rs) {
        super.setmResultSet(rs);
        List<PrivilagesDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                id = rs.getString(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                canAddThread = rs.getBoolean(COLUMN_CAN_ADD_THREAD);
                canDeleteThread = rs.getBoolean(COLUMN_CAN_DELETE_THREAD);
                canEditThread = rs.getBoolean(COLUMN_CAN_EDIT_THREAD);
                canAddMember = rs.getBoolean(COLUMN_CAN_ADD_MEMBER);
                canDeleteMember = rs.getBoolean(COLUMN_CAN_DELETE_MEMBER);
                canAddComment = rs.getBoolean(COLUMN_CAN_ADD_COMMENT);
                canRemoveComment = rs.getBoolean(COLUMN_CAN_REMOVE_COMMENT);
                canEditComment = rs.getBoolean(COLUMN_CAN_EDIT_COMMENT);
                canRemoveUser = rs.getBoolean(COLUMN_CAN_REMOVE_USER);
                canEditUser = rs.getBoolean(COLUMN_CAN_EDIT_USER);
                canAddCommunity = rs.getBoolean(COLUMN_CAN_ADD_COMMUNITY);
                canEditCommunity = rs.getBoolean(COLUMN_CAN_EDIT_COMMUNITY);
                canRemoveCommunity = rs.getBoolean(COLUMN_CAN_REMOVE_COMMUNITY);
                lists.add(this);
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e);
        }
        return lists;

    }

}
