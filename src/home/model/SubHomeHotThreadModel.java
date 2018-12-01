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
import api.daos.TrustedDao;
import api.daos.UserDao;
import api.query.ApiInsertQuery;
import api.query.ApiReadQuery;
import api.tools.Tools;
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
public class SubHomeHotThreadModel implements BaseModel<SubHomeHotThreadModel> {

    private String mIdThread;
    private String mTitle;
    private String mBody;
    private String mTrustAvg;
    private int mTrustCount;
    private int mTrustTotal;
    private Date mDate;

    private String mAuthorId;
    private String mAuthorName;
    private String mAuthorTitle;
    private String mAuthorPosition;
    private String mAuthorCommunity;
    private String mAuthorPostCount;
    private String mAuthorStatus;
    private String mAuthorPic;

    private String mIdMember;
    private String mIdCommunity;

    private List<CommentRowModel> mCommentLists = new ArrayList<>();

    @Override
    public boolean insert(Object request) {
        if (request instanceof RequestLike) {
            ApiInsertQuery<TrustedDao> apiTrust = new ApiInsertQuery<>(new TrustedDao());
            String idTrust = Tools.generateId("TRUST", 10);
            return apiTrust
                    .insertColumnValue(TrustedDao.COLUMN_ID_MEMBER, ((RequestLike) request).idMember)
                    .insertColumnValue(TrustedDao.COLUMN_ID_THREAD, ((RequestLike) request).idThread)
                    .insertColumnValue(TrustedDao.COLUMN_ID_TRUSTED, idTrust)
                    .insertColumnValue(TrustedDao.COLUMN_IS_TRUSTED, "1")
                    .execute();

        } else if (request instanceof RequestComment) {
            ApiInsertQuery<CommentDao> apiComment = new ApiInsertQuery<>(new CommentDao());
            String idComment = Tools.generateId("COM", 10);
            if (((RequestComment) request).idReply == null) {
                return apiComment
                        .insertColumnValue(CommentDao.COLUMN_ID_COMMENT, idComment)
                        .insertColumnValue(CommentDao.COLUMN_ID_MEMBER, ((RequestComment) request).idMember)
                        .insertColumnValue(CommentDao.COLUMN_ID_THREAD, ((RequestComment) request).idThread)
                        .insertColumnValue(CommentDao.COLUMN_COMMENT, ((RequestComment) request).comment)
                        .execute();
            } else {
                return apiComment
                        .insertColumnValue(CommentDao.COLUMN_ID_COMMENT, idComment)
                        .insertColumnValue(CommentDao.COLUMN_ID_MEMBER, ((RequestComment) request).idMember)
                        .insertColumnValue(CommentDao.COLUMN_ID_THREAD, ((RequestComment) request).idThread)
                        .insertColumnValue(CommentDao.COLUMN_ID_REPLY, ((RequestComment) request).idReply)
                        .insertColumnValue(CommentDao.COLUMN_COMMENT, ((RequestComment) request).comment)
                        .execute();
            }
        } else {
            return false;
        }
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
    public List<SubHomeHotThreadModel> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubHomeHotThreadModel> findAllBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubHomeHotThreadModel findSingleBy(Object request) {
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
                mAuthorId = resultsThread.getString(UserDao.COLUMN_ID);
                mAuthorName = resultsThread.getString(UserDao.COLUMN_NAME);
                mAuthorPostCount = resultsThread.getString(UserDao.COLUMN_TOTAL_POST);
                mAuthorStatus = "-";
                mAuthorPic = resultsThread.getString(UserDao.COLUMN_URL_PHOTO);
                mAuthorCommunity = resultsThread.getString(CommunityDao.COLUMN_NAME);
                mAuthorPosition = resultsThread.getString(PrivilagesDao.COLUMN_NAME);
                mAuthorTitle = resultsThread.getString(UserDao.COLUMN_TITLE);

                mIdCommunity = resultsThread.getString(CommunityDao.COLUMN_ID);
                mIdMember = resultsThread.getString(MemberDao.COLUMN_ID_MEMBER);
            }

            if (!mIdThread.isEmpty()) {
                mCommentLists.clear();
                ApiReadQuery<CommentDao> apiComment = new ApiReadQuery<>(new CommentDao());
                ResultSet results = apiComment
                        .showAllColumn()
                        .addInnerJoinTable(MemberDao.TABLE_NAME,MemberDao.COLUMN_ID_MEMBER)
                        .addInnerJoinTable(UserDao.TABLE_NAME,UserDao.COLUMN_ID)
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

    public List<CommentRowModel> getmCommentLists() {
        return mCommentLists;
    }

    public String getmId() {
        return mIdThread;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmBody() {
        return mBody;
    }

    public String getmTrustAvg() {
        return mTrustAvg;
    }

    public int getmTrustCount() {
        return mTrustCount;
    }

    public int getmTrustTotal() {
        return mTrustTotal;
    }

    public Date getmDate() {
        return mDate;
    }

    public String getmAuthorId() {
        return mAuthorId;
    }

    public String getmAuthorPic() {
        return mAuthorPic;
    }

    public String getmAuthorName() {
        return mAuthorName;
    }

    public String getmAuthorTitle() {
        return mAuthorTitle;
    }

    public String getmAuthorPosition() {
        return mAuthorPosition;
    }

    public String getmAuthorCommunity() {
        return mAuthorCommunity;
    }

    public String getmIdThread() {
        return mIdThread;
    }

    public String getmIdMember() {
        return mIdMember;
    }

    public String getmIdCommunity() {
        return mIdCommunity;
    }

    public String getmAuthorPostCount() {
        return mAuthorPostCount;
    }

    public String getmAuthorStatus() {
        return mAuthorStatus;
    }

    public class RequestComment {

        private String idThread;
        private String idCommunity;
        private String idMember;
        private String idReply;
        private String comment;

        public String getIdThread() {
            return idThread;
        }

        public String getIdCommunity() {
            return idCommunity;
        }

        public String getIdMember() {
            return idMember;
        }

        public String getIdReply() {
            return idReply;
        }

        public String getComment() {
            return comment;
        }

        public RequestComment(String idThread, String idCommunity, String idMember, String idReply, String comment) {
            this.idThread = idThread;
            this.idCommunity = idCommunity;
            this.idMember = idMember;
            this.idReply = idReply;
            this.comment = comment;
        }

        public RequestComment(String idThread, String idCommunity, String idMember, String comment) {
            this.idThread = idThread;
            this.idCommunity = idCommunity;
            this.idMember = idMember;
            this.comment = comment;
        }

    }

    public class RequestLike {

        private String idThread;
        private String idCommunity;
        private String idMember;
        private boolean isLike;

        public RequestLike(String idThread, String idCommunity, String idMember, boolean isLike) {
            this.idThread = idThread;
            this.idCommunity = idCommunity;
            this.idMember = idMember;
            this.isLike = isLike;
        }

        public String getIdThread() {
            return idThread;
        }

        public String getIdCommunity() {
            return idCommunity;
        }

        public String getIdMember() {
            return idMember;
        }

        public boolean isIsLike() {
            return isLike;
        }

    }

}
