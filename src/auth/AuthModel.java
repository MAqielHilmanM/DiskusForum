/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import api.ApiConnection;
import api.daos.AuthDao;
import api.daos.UserDao;
import api.query.ApiInsertQuery;
import api.query.ApiReadQuery;
import api.tools.Tools;
import base.BaseModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maqielhm
 */
public class AuthModel implements BaseModel<AuthModel.Response> {

    @Override
    public boolean insert(Object req) {
        Request request = (Request) req;
        String id;
        Boolean isUnique = false;
        do {
            id = Tools.generateId("USER", 10);
            System.out.println("Generated id : "+id);

            ApiReadQuery<UserDao> readApi = new ApiReadQuery<>(new UserDao());
            List<UserDao> datas = readApi
                    .showAllColumn()
                    .conditionEqual(UserDao.COLUMN_ID, id)
                    .execute();
            
            if (datas.size() > 0) {
                isUnique = true;
            } else {
                isUnique = false;
            }
        } while (isUnique);

        ApiInsertQuery<UserDao> userApi = new ApiInsertQuery<>(new UserDao());
        boolean isSuccessful = userApi
                .insertColumnValue(UserDao.COLUMN_ID, id)
                .insertColumnValue(UserDao.COLUMN_NAME, request.name)
                .execute();
        if (isSuccessful) {
            ApiInsertQuery<AuthDao> authApi = new ApiInsertQuery<>(new AuthDao());
            isSuccessful = authApi
                    .insertColumnValue(AuthDao.COLUMN_ID, id)
                    .insertColumnValue(AuthDao.COLUMN_USERNAME, request.user)
                    .insertColumnValue(AuthDao.COLUMN_EMAIL, request.email)
                    .insertColumnValue(AuthDao.COLUMN_PASSWORD, Tools.encryptWithSHA1(request.pass))
                    .execute();
            if(isSuccessful) return true;
        }
        System.out.println("ERROR");
        return false;
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
    public List<Response> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Response findSingleBy(Object req) {
        Request request = (Request) req;
        ApiReadQuery<AuthDao> api = new ApiReadQuery<>(new AuthDao());
        List<AuthDao> data = api
                .showAllColumn()
                .orderByAscending(UserDao.COLUMN_ID)
                .conditionEqual(AuthDao.COLUMN_USERNAME, request.getUser())
                .AND()
                .conditionEqual(AuthDao.COLUMN_PASSWORD, Tools.encryptWithSHA1(request.getPass()))
                .execute();
        if (data.size() > 0) {
            return new Response(data.get(0).getId());
        }
        return null;
    }

    @Override
    public List<Response> findAllBy(Object request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    class Request {

        private String user;
        private String pass;
        private String name;
        private String email;

        public Request(String username, String password) {
            user = username;
            pass = password;
        }

        public Request(String name, String email, String user, String pass) {
            this.user = user;
            this.pass = pass;
            this.name = name;
            this.email = email;
        }

        public String getUser() {
            return user;
        }

        public String getPass() {
            return pass;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

    }

    class Response {

        private String id;

        public Response(String ids) {
            id = ids;
        }

        public Response() {
        }

        public String getId() {
            return id;
        }

        public void setId(String ids) {
            id = ids;
        }

    }

}
