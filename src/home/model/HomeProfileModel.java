/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import api.daos.AuthDao;
import api.daos.ThreadDao;
import api.daos.UserDao;
import api.query.ApiReadQuery;
import base.BaseModel;
import java.util.ArrayList;
import java.util.List;
import utils.Helper;

/**
 *
 * @author maqielhm
 */
class TopCommunity {

    private String title;
    private String date;
    private String comName;

    public TopCommunity(String title, String date, String comName) {
        this.title = title;
        this.date = date;
        this.comName = comName;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getComName() {
        return comName;
    }

}

public class HomeProfileModel implements BaseModel<HomeProfileModel> {

    private String mId;
    private String mName;
    private String mUrl;
    private String mEmail;
    private String mTitle;
    private String mUsername;
    private String mCountry;
    private String mGender;
    private String mDate;
    private String mSince;
    private String mTelp;
    private String mPostCount;
    private String mCommCount;
    private List<TopCommunity> mTopCommunityLists;

    public HomeProfileModel() {
        mTopCommunityLists = new ArrayList<>();
    }

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
    public List<HomeProfileModel> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HomeProfileModel> findAllBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HomeProfileModel findSingleBy(Object id) {
        
        ApiReadQuery<UserDao> api = new ApiReadQuery<>(new UserDao());
        List<UserDao> data = api
                .showAllColumn()
                .conditionEqual(UserDao.COLUMN_ID, id)
                .execute();
        if (data.size() > 0) {
            mId = (String) id;
            mName = data.get(0).getName();
            mCountry = data.get(0).getCountry();
            mGender = data.get(0).getGender() == 'L' ? "Pria" : "Wanita";
            mTelp = data.get(0).getPhone();
            mDate = Helper.convertDateToString(data.get(0).getCreated_date());
            mTitle = data.get(0).getTitle();
            mPostCount = String.valueOf(data.get(0).getTotal_post());
            mCommCount = String.valueOf(data.get(0).getTotal_community());
            mUrl = data.get(0).getUrl_photo();
            mSince = String.valueOf(2018 - data.get(0).getCreated_date().getYear() - 1900);

            ApiReadQuery<AuthDao> apiAuth = new ApiReadQuery<>(new AuthDao());
            List<AuthDao> authRes = apiAuth
                    .showAllColumn()
                    .conditionEqual(AuthDao.COLUMN_ID, id)
                    .execute();
            mUsername = authRes.get(0).getUsername();
            mEmail = authRes.get(0).getEmail();

            return this;
        }

        return null;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String name) {
        mName = name;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String url) {
        mUrl = url;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String email) {
        mEmail = email;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String title) {
        mTitle = title;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String username) {
        mUsername = mUsername;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String country) {
        mCountry = country;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String gender) {
        mGender = gender;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String date) {
        mDate = date;
    }

    public String getmSince() {
        return mSince;
    }

    public void setmSince(String since) {
        mSince = since;
    }

    public String getmTelp() {
        return mTelp;
    }

    public void setmTelp(String telp) {
        mTelp = telp;
    }

    public String getmPostCount() {
        return mPostCount;
    }

    public void setmPostCount(String postCount) {
        mPostCount = postCount;
    }

    public String getmCommCount() {
        return mCommCount;
    }

    public void setmCommCount(String commCount) {
        mCommCount = commCount;
    }

    public TopCommunity getmTopCommunityLists(int index) {
        return mTopCommunityLists.get(index);
    }

    public void setmTopCommunityLists(TopCommunity topCommunityLists) {
        mTopCommunityLists.add(topCommunityLists);
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String id) {
        mId = id;
    }

}
