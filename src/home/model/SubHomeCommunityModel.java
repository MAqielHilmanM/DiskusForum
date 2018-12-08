/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import api.daos.CommunityDao;
import api.daos.MemberDao;
import api.daos.PrivilagesDao;
import api.daos.ThreadDao;
import api.daos.UserDao;
import api.query.ApiReadQuery;
import base.BaseModel;
import home.com_thread.CommunityThreadRowModel;
import home.member.MemberRowModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Helper;

/**
 *
 * @author maqielhm
 */
public class SubHomeCommunityModel implements BaseModel<SubHomeCommunityModel> {

    private String mIdCommunity;
    private String mCommunityName;
    private String mCreatorName;
    private int mMemberActive;
    private int mMemberTotal;
    private int mThreadTotal;
    private Date mDate;
    private String mDescription;
    private String mIdMember;

    private List<MemberRowModel> mMemberLists = new ArrayList<>();
    private List<CommunityThreadRowModel> mCommThreadLists = new ArrayList<>();

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
    public List<SubHomeCommunityModel> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubHomeCommunityModel> findAllBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubHomeCommunityModel findSingleBy(Object request) {
        try {
            ApiReadQuery<CommunityDao> apiCommunity = new ApiReadQuery<>(new CommunityDao());
            ResultSet rs = apiCommunity
                    .showAllColumn()
                    .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_COMMUNITY)
                    .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                    .orderByDescending(CommunityDao.TABLE_NAME+"."+CommunityDao.COLUMN_CREATED_DATE)
                    .conditionEqual(CommunityDao.COLUMN_ID, request)
                    .executeAsResultSet();

            while (rs.next()) {
                mIdCommunity = (String) request;
                mCommunityName = rs.getString(CommunityDao.COLUMN_NAME);
                mCreatorName = rs.getString(UserDao.COLUMN_NAME);
                mDate = rs.getDate(CommunityDao.COLUMN_CREATED_DATE);
                mDescription = rs.getString(CommunityDao.COLUMN_DESC);
                mIdMember = rs.getString(MemberDao.COLUMN_ID_MEMBER);
                mMemberTotal = rs.getInt(CommunityDao.COLUMN_MEMBER_COUNT);
                mThreadTotal = rs.getInt(CommunityDao.COLUMN_THREAD_COUNT);
            }

            if (!mIdCommunity.isEmpty()) {
                mCommThreadLists.clear();
                ApiReadQuery<ThreadDao> apiThread = new ApiReadQuery<>(new ThreadDao());
                ResultSet results = apiThread
                        .showAllColumn()
                        .conditionEqual(ThreadDao.COLUMN_ID_COMMUNITY, mIdCommunity)
                        .executeAsResultSet();
                while (results.next()) {
                    mCommThreadLists.add(
                            new CommunityThreadRowModel(
                                    results.getString(ThreadDao.COLUMN_ID),
                                    Helper.convertDateToString(results.getDate(ThreadDao.COLUMN_CREATED_DATE)),
                                    results.getString(ThreadDao.COLUMN_TITLE),
                                    results.getString(ThreadDao.COLUMN_BODY)));
                }
            }

            if (!mIdCommunity.isEmpty()) {
                ApiReadQuery<MemberDao> apiMember = new ApiReadQuery<>(new MemberDao());
                ResultSet results = apiMember
                        .showAllColumn()
                        .addInnerJoinTable(CommunityDao.TABLE_NAME, CommunityDao.COLUMN_ID)
                        .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                        .addInnerJoinTable(PrivilagesDao.TABLE_NAME, PrivilagesDao.COLUMN_ID)
                        .orderByDescending(CommunityDao.TABLE_NAME+"."+CommunityDao.COLUMN_CREATED_DATE)
                        .conditionEqual(CommunityDao.COLUMN_ID, mIdCommunity)
                        .executeAsResultSet();
                while (results.next()) {
                    mMemberLists.add(new MemberRowModel(
                            results.getString(MemberDao.COLUMN_ID_MEMBER),
                            results.getString(UserDao.COLUMN_NAME),
                            results.getString(PrivilagesDao.COLUMN_NAME),
                            "-",
                            results.getString(CommunityDao.COLUMN_NAME),
                            results.getString(UserDao.COLUMN_TOTAL_POST),
                            "-"));
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(SubHomeHotThreadModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public String getmIdCommunity() {
        return mIdCommunity;
    }

    public String getmCommunityName() {
        return mCommunityName;
    }

    public String getmCreatorName() {
        return mCreatorName;
    }

    public int getmMemberActive() {
        return mMemberActive;
    }

    public int getmMemberTotal() {
        return mMemberTotal;
    }

    public int getmThreadTotal() {
        return mThreadTotal;
    }

    public Date getmDate() {
        return mDate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmIdMember() {
        return mIdMember;
    }

    public List<MemberRowModel> getmMemberLists() {
        return mMemberLists;
    }

    public List<CommunityThreadRowModel> getmCommThreadLists() {
        return mCommThreadLists;
    }
    

}
