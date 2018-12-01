/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import api.daos.CommunityDao;
import api.daos.MemberDao;
import api.daos.ThreadDao;
import api.daos.UserDao;
import api.query.ApiReadQuery;
import base.BaseModel;
import home.my_thread.MyThreadRowModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Constant;

/**
 *
 * @author maqielhm
 */
public class HomeMyThreadModel implements BaseModel<MyThreadRowModel> {

    List<MyThreadRowModel> mMyThreadLists = new ArrayList<>();

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
    public List<MyThreadRowModel> findAll() {
        ApiReadQuery<ThreadDao> api = new ApiReadQuery<>(new ThreadDao());
        ResultSet data = api
                .showAllColumn()
                .orderByAscending(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_CREATED_DATE)
                .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_MEMBER)
                .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                .addInnerJoinTable(CommunityDao.TABLE_NAME, CommunityDao.COLUMN_ID, ThreadDao.TABLE_NAME, ThreadDao.COLUMN_ID_COMMUNITY)
                .groupBy(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_ID)
                .conditionLike(UserDao.TABLE_NAME + "." + UserDao.COLUMN_ID, Constant.USER_ID)
                .executeAsResultSet();
        try {
            mMyThreadLists.clear();
            while (data != null && data.next()) {
                mMyThreadLists.add(
                        new MyThreadRowModel(
                                data.getString(ThreadDao.COLUMN_ID),
                                data.getString(ThreadDao.COLUMN_CREATED_DATE),
                                data.getString(CommunityDao.COLUMN_NAME),
                                data.getString(ThreadDao.COLUMN_TITLE),
                                data.getString(ThreadDao.COLUMN_BODY)));
            }
        } catch (SQLException e) {
            System.err.println("ERROR : " + e);
        }
        return mMyThreadLists;
    }

    @Override
    public List<MyThreadRowModel> findAllBy(Object request) {

        ApiReadQuery<ThreadDao> api = new ApiReadQuery<>(new ThreadDao());
        ResultSet data = api
                .showAllColumn()
                .orderByAscending(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_CREATED_DATE)
                .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_MEMBER)
                .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                .addInnerJoinTable(CommunityDao.TABLE_NAME, CommunityDao.COLUMN_ID, ThreadDao.TABLE_NAME, ThreadDao.COLUMN_ID_COMMUNITY)
                .groupBy(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_ID)
                .conditionLike(UserDao.TABLE_NAME + "." + UserDao.COLUMN_ID, Constant.USER_ID)
                .AND()
                .conditionLike(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_TITLE, "%" + request + "%")
                .OR()
                .conditionLike(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_BODY, "%" + request + "%")
                .executeAsResultSet();

        try {
            mMyThreadLists.clear();
            while (data != null && data.next()) {
                mMyThreadLists.add(
                        new MyThreadRowModel(
                                data.getString(ThreadDao.COLUMN_ID),
                                data.getString(ThreadDao.COLUMN_CREATED_DATE),
                                data.getString(CommunityDao.COLUMN_NAME),
                                data.getString(ThreadDao.COLUMN_TITLE),
                                data.getString(ThreadDao.COLUMN_BODY)));
            }
        } catch (SQLException e) {
            System.err.println("ERROR : " + e);
        }
        return mMyThreadLists;
    }

    @Override
    public MyThreadRowModel findSingleBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MyThreadRowModel> getmMyThreadLists() {
        return mMyThreadLists;
    }

    public void setmMyThreadLists(List<MyThreadRowModel> mMyThreadLists) {
        this.mMyThreadLists = mMyThreadLists;
    }

}
