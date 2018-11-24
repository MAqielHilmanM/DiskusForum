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
public class PrivilagesDao extends BaseDao<PrivilagesDao>{
    public static final String TABLE_NAME = "t_privilages";
    public static final String COLUMN_ID = "id_privilages";
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

    public PrivilagesDao() {
        super(TABLE_NAME, COLUMNS);
    }

    @Override
    public List<PrivilagesDao> toObjects(ResultSet rs) {
        List<PrivilagesDao> lists = new ArrayList<>();
        try {
            while (rs.next()) {
                id = rs.getString(COLUMN_ID);
                lists.add(this);
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e);
        }
        return lists;

    }

}
