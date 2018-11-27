/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

import api.ApiConnection;
import api.daos.BaseDao;
import api.tools.Tools;
import com.sun.org.apache.xpath.internal.operations.And;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author maqielhm
 */
public class ApiUpdateQuery<T extends BaseDao<T>> extends ApiBaseQuery<Boolean> implements ApiOperatorInterface<ApiUpdateQuery.Condition>{

    private T mTableObject;
    private String mTableName;
    private HashMap<String, String> mSetColumns;
    private String mQuery = "";
    private String mWhere = "";

    public ApiUpdateQuery(T tableObject) {
        this.mTableObject = tableObject;
        this.mSetColumns = new HashMap<>();
        this.mTableName = tableObject.getTableName();
    }

    public ApiUpdateQuery() {
        this.mSetColumns = new HashMap<>();
    }

    public ApiUpdateQuery defineTable(T table) {
        mTableObject = table;
        mTableName = table.getTableName();
        return this;
    }

    public ApiUpdateQuery addColumn(String column, String value) {
        mSetColumns.put(column, value);
        return this;
    }

    public ApiUpdateQuery updateSingleColumn(String primaryKey, String value) {
        return this;
    }

    @Override
    public void prepareQuery() {
        if (mQuery.equals("")) {
            mQuery = "UPDATE " + mTableName + " SET ";
            int i = 0;
            mSetColumns.forEach((t, u) -> {
                mQuery = mQuery.concat(t);
                mQuery = mQuery.concat(" = ");
                mQuery = mQuery.concat("'" + u + "'");
                mQuery = mQuery.concat(",");
            });

            if (mQuery.charAt(mQuery.length() - 1) == ',') {
                mQuery = mQuery.substring(0, mQuery.length() - 1);
            }
            
            if(!mWhere.equals("")){
                mQuery = mQuery.concat(" WHERE "+mWhere);
            }

        }
        System.out.println("QUERY : " + mQuery);
    }

    @Override
    public Boolean execute() {
        try {
            if(!ApiConnection.hasSet()) throw new Exception("Connection Not Set");
            ApiConnection.createConnection();
            Statement state = ApiConnection.getConnection().createStatement();
            prepareQuery();
            if (state.executeUpdate(mQuery) > 0) {
                System.out.println("UPDATE Data Sucess");
                return true;
            }
            ApiConnection.closeConnection();
        } catch (Exception e) {
            System.err.println("UPDATE ERROR :" + e);
        }

        System.out.println("UPDATE Data Failed");
        return false;
    }

    @Override
    public void inputCustomQuery(String query) {
        mQuery = query;
    }

    @Override
    public Condition conditionEqual(Object a, Object b) {
        mWhere = mWhere.concat(a+" = "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionNotEqual(Object a, Object b) {
        mWhere = mWhere.concat(a+" <> "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionGraterThanOrEqual(Object a, Object b) {
        mWhere = mWhere.concat(a+" >= "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionLessThanOrEqual(Object a, Object b) {
        mWhere = mWhere.concat(a+" <= "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionLessThan(Object a, Object b) {
        mWhere = mWhere.concat(a+" < "+Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionGraterThan(Object a, Object b) {
        mWhere = mWhere.concat(a+" > "+Tools.convertToQueryValue(b));
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

        public ApiUpdateQuery AND() {
            mWhere = mWhere.concat(" AND ");
        
            return ApiUpdateQuery.this;
        }

        public ApiUpdateQuery OR() {
            mWhere = mWhere.concat(" OR ");
            return ApiUpdateQuery.this;
        }

        public ApiUpdateQuery NOT() {
            mWhere = mWhere.concat(" NOT ");
            return ApiUpdateQuery.this;
        }
        
        public Boolean execute(){
            return ApiUpdateQuery.this.execute();
        }
    }

}
