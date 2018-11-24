/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

import api.ApiConnection;
import api.daos.BaseDao;
import api.tools.Tools;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author maqielhm
 * @param <T>
 */
public class ApiDeleteQuery<T extends BaseDao<T>> extends ApiBaseQuery<Boolean> implements ApiOperatorInterface<ApiDeleteQuery.Condition>{

    private final T mTableObject;
    private String mWhere = "";
    private final String mTableName;
    private Boolean isDeleteRelation;
    private String mQuery = "";

    public ApiDeleteQuery(T table) {
        this.mTableObject = table;
        this.mTableName = mTableObject.getTableName();
        this.isDeleteRelation = false;
    }

    public ApiDeleteQuery(T table, String primaryKeyValue) {
        this.mTableObject = table;
        this.mTableName = table.getTableName();
        this.isDeleteRelation = false;
    }

    public ApiDeleteQuery isDeleteRelation() {
        isDeleteRelation = true;
        return this;
    }

    @Override
    public void prepareQuery() {
        if (mQuery.equals("")) {
            mQuery = "DELETE FROM ";
            mQuery = mQuery.concat(mTableName);

            if(!mWhere.equals("")){
                mQuery = mQuery.concat(" WHERE "+mWhere);
            }
            
            if (isDeleteRelation) {
                mQuery = mQuery.concat("CASCADE CONSTRAINT");
            }
            
            

        }
        System.out.println("QUERY : " + mQuery);
    }

    @Override
    public Boolean execute() {
        try {
            Statement state = ApiConnection.getConnection().createStatement();
            prepareQuery();
            if (state.executeUpdate(mQuery) > 0) {
                System.out.println("Delete Data Sucess");
                return true;
            }

        } catch (Exception e) {
            System.err.println("DELETE QUERY ERROR :" + e);
        }

        System.out.println("Delete Data Failed");
        return false;
    }

    @Override
    public void inputCustomQuery(String query) {
        mQuery = query;
    }

    @Override
    public Condition conditionEqual(Object a, Object b) {
        mWhere = mWhere.concat(Tools.convertToQueryValue(a)+" = "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionNotEqual(Object a, Object b) {
        mWhere = mWhere.concat(Tools.convertToQueryValue(a)+" <> "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionGraterThanOrEqual(Object a, Object b) {
        mWhere = mWhere.concat(Tools.convertToQueryValue(a)+" >= "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionLessThanOrEqual(Object a, Object b) {
        mWhere = mWhere.concat(Tools.convertToQueryValue(a)+" <= "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionLessThan(Object a, Object b) {
        mWhere = mWhere.concat(Tools.convertToQueryValue(a)+" < "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionGraterThan(Object a, Object b) {
        mWhere = mWhere.concat(Tools.convertToQueryValue(a)+" > "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionBetween(String column, Object a, Object b) {
        mWhere = mWhere.concat(column+" Between "+a+" AND "+b);
        return new Condition();
    }

    @Override
    public Condition conditionLike(String column, String b) {
        mWhere = mWhere.concat(column+" Like "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionIn(String column, Object[] b) {
        mWhere = mWhere.concat(column+" IN(");
        for (Object object : b) {
            object = object instanceof String?"'"+object+"'":object;
            mWhere = mWhere.concat(object+",");
        }
        
        if (mWhere.charAt(mWhere.length() - 1) == ',') {
                mWhere = mWhere.substring(0, mWhere.length() - 1);
        }
        mWhere = mWhere.concat(")");
        
        return new Condition();
    }

    public class Condition {

        public ApiDeleteQuery AND() {
            mWhere = mWhere.concat(" AND ");
            return ApiDeleteQuery.this;
        }

        public ApiDeleteQuery OR() {
            mWhere = mWhere.concat(" OR ");
            return ApiDeleteQuery.this;
        }

        public ApiDeleteQuery NOT() {
            mWhere = mWhere.concat(" NOT ");
            return ApiDeleteQuery.this;
        }

        public Boolean execute() {
            return ApiDeleteQuery.this.execute();
        }
    }

}
