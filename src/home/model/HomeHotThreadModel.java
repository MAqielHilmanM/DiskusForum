/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import api.daos.AuthDao;
import api.daos.CommentDao;
import api.daos.CommunityDao;
import api.daos.MemberDao;
import api.daos.ThreadDao;
import api.daos.UserDao;
import api.query.ApiInsertQuery;
import api.query.ApiReadQuery;
import base.BaseModel;
import home.hot_thread.HotThreadRowModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maqielhm
 */
public class HomeHotThreadModel implements BaseModel<HotThreadRowModel> {

    List<HotThreadRowModel> hotThreadLists = new ArrayList<>();

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
    public List<HotThreadRowModel> findAll() {
        ApiReadQuery<ThreadDao> api = new ApiReadQuery<>(new ThreadDao());
        ResultSet data = api
                .showAllColumn()
                .orderByAscending(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_CREATED_DATE)
                .addInnerJoinTable(CommunityDao.TABLE_NAME, CommunityDao.COLUMN_ID)
                .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_COMMUNITY)
                .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                .groupBy(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_ID)
                .executeAsResultSet();
        try {
            hotThreadLists.clear();
            while (data != null && data.next()) {
                hotThreadLists.add(
                        new HotThreadRowModel(
                                data.getString(ThreadDao.COLUMN_ID),
                                data.getString(UserDao.COLUMN_URL_PHOTO),
                                data.getString(UserDao.COLUMN_NAME),
                                data.getString(CommunityDao.COLUMN_NAME),
                                data.getString(ThreadDao.COLUMN_TITLE),
                                data.getString(ThreadDao.COLUMN_BODY),
                                data.getString(ThreadDao.COLUMN_CREATED_DATE)));
            }
        } catch (SQLException e) {
            System.err.println("ERROR : " + e);
        }
        return hotThreadLists;
    }

    @Override
    public List<HotThreadRowModel> findAllBy(Object req) {
        String request = (String) req;
        ApiReadQuery<ThreadDao> api = new ApiReadQuery<>(new ThreadDao());
        ResultSet data = api
                .showAllColumn()
                .orderByAscending(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_CREATED_DATE)
                .addInnerJoinTable(CommunityDao.TABLE_NAME, CommunityDao.COLUMN_ID)
                .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_COMMUNITY)
                .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                .groupBy(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_ID)
                .conditionLike(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_TITLE, "%" + request + "%")
                .OR()
                .conditionLike(ThreadDao.TABLE_NAME + "." + ThreadDao.COLUMN_BODY, "%" + request + "%")
                .executeAsResultSet();

        try {
            hotThreadLists.clear();
            while (data != null && data.next()) {
                hotThreadLists.add(
                        new HotThreadRowModel(
                                request,
                                data.getString(UserDao.COLUMN_URL_PHOTO),
                                data.getString(UserDao.COLUMN_NAME),
                                data.getString(CommunityDao.COLUMN_NAME),
                                data.getString(ThreadDao.COLUMN_TITLE),
                                data.getString(ThreadDao.COLUMN_BODY),
                                data.getString(ThreadDao.COLUMN_CREATED_DATE)));
            }
        } catch (SQLException e) {
            System.err.println("ERROR : " + e);
        }
        return hotThreadLists;
    }

    @Override
    public HotThreadRowModel findSingleBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<HotThreadRowModel> getHotThreadLists() {
        return hotThreadLists;
    }

    public void setHotThreadLists(List<HotThreadRowModel> hotThreadLists) {
        this.hotThreadLists = hotThreadLists;
    }

}
