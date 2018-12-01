/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import api.daos.AuthDao;
import api.daos.CommunityDao;
import api.daos.MemberDao;
import api.daos.UserDao;
import api.query.ApiReadQuery;
import base.BaseModel;
import home.community.CommunityRowModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maqielhm
 */
public class HomeCommunityModel implements BaseModel<CommunityRowModel> {

    List<CommunityRowModel> mCommunityLists = new ArrayList<>();

    @Override
    public boolean insert(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CommunityRowModel> findAll() {
        ApiReadQuery<CommunityDao> api = new ApiReadQuery<>(new CommunityDao());
        ResultSet data = api
                .showAllColumn()
                .orderByAscending(CommunityDao.TABLE_NAME + "." +CommunityDao.COLUMN_CREATED_DATE)
                .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_COMMUNITY)
                .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                .addInnerJoinTable(AuthDao.TABLE_NAME, AuthDao.COLUMN_ID)
                .groupBy(CommunityDao.TABLE_NAME + "." + CommunityDao.COLUMN_ID)
                .executeAsResultSet();
        try {
            mCommunityLists.clear();
            while (data != null && data.next()) {
                mCommunityLists.add(
                        new CommunityRowModel(
                                data.getString(CommunityDao.COLUMN_ID),
                                data.getString(CommunityDao.COLUMN_NAME),
                                data.getString(AuthDao.COLUMN_USERNAME),
                                data.getString(CommunityDao.COLUMN_DESC),
                                data.getString(CommunityDao.COLUMN_CREATED_DATE)));
            }
        } catch (SQLException e) {
            System.err.println("ERROR : " + e);
        }
        return mCommunityLists;
    }

    @Override
    public List<CommunityRowModel> findAllBy(Object request) {

        ApiReadQuery<CommunityDao> api = new ApiReadQuery<>(new CommunityDao());
        ResultSet data = api
                .showAllColumn()
                .orderByAscending(CommunityDao.TABLE_NAME + "." +CommunityDao.COLUMN_CREATED_DATE)
                .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_COMMUNITY)
                .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                .addInnerJoinTable(AuthDao.TABLE_NAME, AuthDao.COLUMN_ID)
                .groupBy(CommunityDao.TABLE_NAME + "." + CommunityDao.COLUMN_ID)
                .conditionLike(CommunityDao.TABLE_NAME + "." + CommunityDao.COLUMN_NAME, "%" + request + "%")
                .OR()
                .conditionLike(CommunityDao.TABLE_NAME + "." + CommunityDao.COLUMN_DESC, "%" + request + "%")
                .executeAsResultSet();

        try {
            mCommunityLists.clear();
            while (data != null && data.next()) {
                mCommunityLists.add(
                        new CommunityRowModel(
                                data.getString(CommunityDao.COLUMN_ID),
                                data.getString(CommunityDao.COLUMN_NAME),
                                data.getString(AuthDao.COLUMN_USERNAME),
                                data.getString(CommunityDao.COLUMN_DESC),
                                data.getString(CommunityDao.COLUMN_CREATED_DATE)));
            }
        } catch (SQLException e) {
            System.err.println("ERROR : " + e);
        }
        return mCommunityLists;
    }

    @Override
    public CommunityRowModel findSingleBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CommunityRowModel> getmCommunityLists() {
        return mCommunityLists;
    }

    public void setmCommunityLists(List<CommunityRowModel> mCommunityLists) {
        this.mCommunityLists = mCommunityLists;
    }

    

}
