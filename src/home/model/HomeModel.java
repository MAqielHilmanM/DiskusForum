/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import api.daos.UserDao;
import api.query.ApiReadQuery;
import base.BaseModel;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author maqielhm
 */
public class HomeModel implements BaseModel<HomeModel> {

    private String mId;
    private String mName;
    private String mUrlPhoto;
    private HomeHotThreadModel mHotThreadModel;
    private HomeCommunityModel mCommunityModel;
    private HomeMyThreadModel mMyThreadModel;
    private HomeProfileModel mProfileModel;
    private HashMap<String, SubHomeCommunityModel> mSubHomeCommunityHashMap;
    private HashMap<String, SubHomeHotThreadModel> mSubHomeHotThreadHashMap;
    private HashMap<String, SubHomeMyThreadModel> mSubHomeMyThreadHashMap;

    public HomeModel() {
        mHotThreadModel = new HomeHotThreadModel();
        mProfileModel = new HomeProfileModel();
        mCommunityModel = new HomeCommunityModel();
        mMyThreadModel = new HomeMyThreadModel();
        mSubHomeCommunityHashMap = new HashMap<>();
        mSubHomeHotThreadHashMap = new HashMap<>();
        mSubHomeMyThreadHashMap = new HashMap<>();
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
    public List<HomeModel> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HomeModel findSingleBy(Object id) {
        ApiReadQuery<UserDao> db = new ApiReadQuery<>(new UserDao());
        List<UserDao> lists = db
                .showAllColumn()
                .conditionEqual(UserDao.COLUMN_ID, id)
                .execute();

        if (lists.size() > 0) {
            HomeModel rs = new HomeModel();
            rs.setmName(lists.get(0).getName());
            rs.setmUrlPhoto(lists.get(0).getUrl_photo());
            return rs;
        } else {
            return null;
        }

    }

    @Override
    public List<HomeModel> findAllBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setmName(String name) {
        mName = name;
    }

    public void setmUrlPhoto(String urlPhoto) {
        mUrlPhoto = urlPhoto;
    }

    public void setmHotThreadModel(HomeHotThreadModel hotThreadLists) {
        mHotThreadModel = hotThreadLists;
    }

    public void setmProfileModel(HomeProfileModel profileModel) {
        mProfileModel = profileModel;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String id) {
        mId = id;
    }

    public String getmName() {
        return mName;
    }

    public String getmUrlPhoto() {
        return mUrlPhoto;
    }

    public HomeHotThreadModel getmHotThreadModel() {
        return mHotThreadModel;
    }

    public HomeCommunityModel getmCommunityModel() {
        return mCommunityModel;
    }

    public void setmCommunityModel(HomeCommunityModel mCommunityModel) {
        this.mCommunityModel = mCommunityModel;
    }

    public HomeMyThreadModel getmMyThreadModel() {
        return mMyThreadModel;
    }

    public void setmMyThreadModel(HomeMyThreadModel mMyThreadModel) {
        this.mMyThreadModel = mMyThreadModel;
    }

    public HomeProfileModel getmProfileModel() {
        return mProfileModel;
    }

    public SubHomeCommunityModel getmSubHomeCommunityHashMap(String key) {
        return mSubHomeCommunityHashMap.get(key);
    }

    public void setmSubHomeCommunityHashMap(String key, SubHomeCommunityModel data) {
        this.mSubHomeCommunityHashMap.put(key, data);
    }

    public SubHomeHotThreadModel getmSubHomeHotThreadHashMap(String key) {
        return mSubHomeHotThreadHashMap.get(key);
    }

    public void setmSubHomeHotThreadHashMap(String key, SubHomeHotThreadModel data) {
        this.mSubHomeHotThreadHashMap.put(key, data);
    }

    public SubHomeMyThreadModel getmSubHomeMyThreadHashMap(String key) {
        return mSubHomeMyThreadHashMap.get(key);
    }

    public void setmSubHomeMyThreadHashMap(String key, SubHomeMyThreadModel data) {
        this.mSubHomeMyThreadHashMap.put(key, data);
    }

}
