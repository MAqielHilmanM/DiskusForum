/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

import api.ApiConnection;
import api.daos.BaseDao;
import api.daos.UserDao;
import com.sun.istack.internal.Nullable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Constant;
import api.tools.Tools;

/**
 *
 * @author maqielhm
 */

public class ApiReadQuery<T extends BaseDao<T>> extends ApiBaseQuery<List<T>> implements ApiOperatorInterface<ApiReadQuery.Condition>{

    private List<String> mColumns;
    private String mTable = "";
    private String mWhere = "";
    private boolean mShowAll = false;
    private T mTableObject;
    private String mQuery = "";

    public ApiReadQuery() {
        mColumns = new ArrayList<>();
        mTableObject = null;
    }

    public ApiReadQuery(T table) {
        mColumns = new ArrayList<>();
        mTableObject = table;
        mTable = table.getTableName();
    }

    public ApiReadQuery addTable(T table) throws Exception  {
        if (mTable.isEmpty()) {
            mTable = table.getTableName();
            mTableObject = table;
            return this;
        }
        throw new Exception("Add relation if add multitable");
    }

//    
//    public ApiReadQuery addTable(String tableName, Relation relation, String condition) {
//        mTables.add(tableName);
//        mRelations.add(new Relational(relation, condition));
//        return this;
//    }

//    
//    public ApiReadQuery addGroupBy(String columnName) {
//        mGroups.add(columnName);
//        return this;
//    }

    public ApiReadQuery showAllColumn() {
        mShowAll = true;
        mColumns.clear();
        return this;
    }

    public ApiReadQuery showColumn(String column) {
        if (mShowAll) {
            System.err.println("Remove Show All Data");
        }
        mColumns.add(column);
        mShowAll = false;
        return this;
    }

    @Override
    public void prepareQuery() {
        if (mQuery.equals("")) {
            mQuery = mQuery.concat("SELECT ");
            if (mShowAll) {
                mQuery = mQuery.concat("* ");
            } else {
                for (int i = 0; i < mColumns.size(); i++) {
                    mQuery = mQuery.concat(mColumns.get(i));
                    if (i != mColumns.size() - 1) {
                        mQuery = mQuery.concat(",");
                    }
                    mQuery = mQuery.concat(" ");
                }
            }

            mQuery = mQuery.concat("FROM ");
            
            mQuery = mQuery.concat(mTable);

            if(!mWhere.isEmpty()){
                mQuery = mQuery.concat(" WHERE "+mWhere);
            }

        }

        System.out.println("READ QUERY : " + mQuery);
    }

    @Override
    public List<T> execute() {
        List<T> lists = new ArrayList<T>();
        try {
            if(!ApiConnection.hasSet()) throw new Exception("Connection Not Set");
            ApiConnection.createConnection();
            Statement state = ApiConnection.getConnection().createStatement();
            prepareQuery();
            ResultSet rs = state.executeQuery(mQuery);

            if (mTableObject == null) {
                throw new Exception("Table Class Not Found");
            }
            lists = mTableObject.toObjects(rs);
            ApiConnection.closeConnection();
        } catch (Exception e) {
            System.err.println("READ QUERY ERROR :" + e);
        }
        return lists;
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

        public ApiReadQuery AND() {
            mWhere = mWhere.concat(" AND ");
        
            return ApiReadQuery.this;
        }

        public ApiReadQuery OR() {
            mWhere = mWhere.concat(" OR ");
            return ApiReadQuery.this;
        }

        public ApiReadQuery NOT() {
            mWhere = mWhere.concat(" NOT ");
            return ApiReadQuery.this;
        }
        
        public List<T> execute(){
            return ApiReadQuery.this.execute();
        }
    }
}
