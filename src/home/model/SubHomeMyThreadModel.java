/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import api.daos.CommentDao;
import api.daos.CommunityDao;
import api.daos.MemberDao;
import api.daos.PrivilagesDao;
import api.daos.ThreadDao;
import api.daos.UserDao;
import api.query.ApiReadQuery;
import base.BaseModel;
import home.comment.CommentRowModel;
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
public class SubHomeMyThreadModel implements BaseModel<SubHomeMyThreadModel> {

    private String mIdThread;
    private String mTitle;
    private String mBody;
    private String mTrustAvg;
    private int mTrustCount;
    private int mTrustTotal;
    private Date mDate;

    private String mIdMember;
    private String mIdCommunity;

    private List<CommentRowModel> mCommentLists = new ArrayList<>();

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
    public List<SubHomeMyThreadModel> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubHomeMyThreadModel> findAllBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubHomeMyThreadModel findSingleBy(Object request) {
        try {
            ApiReadQuery<ThreadDao> apiThread = new ApiReadQuery<>(new ThreadDao());
            ResultSet resultsThread = apiThread
                    .showAllColumn()
                    .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_MEMBER)
                    .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                    .addInnerJoinTable(PrivilagesDao.TABLE_NAME, PrivilagesDao.COLUMN_ID)
                    .addInnerJoinTable(CommunityDao.TABLE_NAME, CommunityDao.COLUMN_ID, ThreadDao.TABLE_NAME, ThreadDao.COLUMN_ID_COMMUNITY)
                    .conditionEqual(ThreadDao.COLUMN_ID, request)
                    .executeAsResultSet();
            while (resultsThread.next()) {
                mIdThread = (String) request;
                mTitle = resultsThread.getString(ThreadDao.COLUMN_TITLE);
                mBody = resultsThread.getString(ThreadDao.COLUMN_BODY);
                mDate = resultsThread.getDate(ThreadDao.COLUMN_CREATED_DATE);
                mTrustCount = resultsThread.getInt(ThreadDao.COLUMN_TOTAL_LIKE);
                mTrustTotal = resultsThread.getInt(ThreadDao.COLUMN_TOTAL_COMMENT);
                mTrustAvg = Helper.getPercentageFromAverage(mTrustCount, mTrustTotal);

                mIdCommunity = resultsThread.getString(CommunityDao.COLUMN_ID);
                mIdMember = resultsThread.getString(MemberDao.COLUMN_ID_MEMBER);
            }

            if (!mIdThread.isEmpty()) {
                mCommentLists.clear();
                ApiReadQuery<CommentDao> apiComment = new ApiReadQuery<>(new CommentDao());
                ResultSet results = apiComment
                        .showAllColumn()
                        .addInnerJoinTable(MemberDao.TABLE_NAME, MemberDao.COLUMN_ID_MEMBER)
                        .addInnerJoinTable(UserDao.TABLE_NAME, UserDao.COLUMN_ID)
                        .conditionEqual(CommentDao.COLUMN_ID_THREAD, mIdThread)
                        .executeAsResultSet();
                while (results.next()) {
                    mCommentLists.add(
                            new CommentRowModel(
                                    results.getString(CommentDao.COLUMN_ID_COMMENT),
                                    results.getString(UserDao.COLUMN_URL_PHOTO),
                                    results.getString(CommentDao.COLUMN_CREATED_DATE),
                                    results.getString(CommentDao.COLUMN_COMMENT),
                                    results.getString(UserDao.COLUMN_NAME)));
                }

            }

            return this;
        } catch (SQLException ex) {
            Logger.getLogger(SubHomeHotThreadModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;

    }

}
