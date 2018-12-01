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
enum Order {
    ASCENDING,
    DESCENDING
}

public class ApiReadQuery<T extends BaseDao<T>> extends ApiBaseQuery<List<T>> implements ApiOperatorInterface<ApiReadQuery.Condition>, ApiJoinTableInterface<ApiReadQuery> {

    private List<String> mColumns;
    private String mTable = "";
    private String mLastTable = "";
    private String mWhere = "";
    private boolean mShowAll = false;
    private T mTableObject;
    private String mQuery = "";
    private Order orderBy;
    private String orderByColumn;
    private List<String> mGroups;

    public ApiReadQuery() {
        mColumns = new ArrayList<>();
        mGroups = new ArrayList<>();
        mTableObject = null;
    }

    public ApiReadQuery(T table) {
        mColumns = new ArrayList<>();
        mTableObject = table;
        mTable = table.getTableName();
        mGroups = new ArrayList<>();
    }

    public ApiReadQuery addTable(T table) throws Exception {
        if (mTable.isEmpty()) {
            mTable = table.getTableName();
            mTableObject = table;
            return this;
        }
        throw new Exception("Add relation if add multitable");
    }

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

    public ApiReadQuery showColumn(String[] column) {
        if (mShowAll) {
            System.err.println("Remove Show All Data");
        }
        for (String col : column) {
            mColumns.add(col);
        }
        mShowAll = false;
        return this;
    }

    public ApiReadQuery orderByAscending(String column) {
        orderBy = Order.ASCENDING;
        orderByColumn = column;
        return this;
    }

    public ApiReadQuery orderByDescending(String column) {
        orderBy = Order.DESCENDING;
        orderByColumn = column;
        return this;
    }

    public ApiReadQuery groupBy(String column) {
        mGroups.add(column);
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

            if (!mWhere.isEmpty()) {
                mQuery = mQuery.concat(" WHERE " + mWhere);
            }

            if (mGroups.size() > 0) {
                mQuery = mQuery.concat(" GROUP BY ");
                for (int i = 0; i < mGroups.size(); i++) {
                    mQuery = mQuery.concat(mGroups.get(i));
                    if (i != mGroups.size() - 1) {
                        mQuery = mQuery.concat(",");
                    }
                    mQuery = mQuery.concat(" ");
                }
            }

            if (orderBy != null && orderByColumn != null) {
                mQuery = mQuery.concat(" ORDER BY " + orderByColumn + " ");
                if (orderBy == Order.ASCENDING) {
                    mQuery = mQuery.concat("ASC");
                } else {
                    mQuery = mQuery.concat("DESC");
                }
            }

        }

        System.out.println("READ QUERY : " + mQuery);
    }

    @Override
    public List<T> execute() {
        List<T> lists = new ArrayList<T>();
        try {
            if (!ApiConnection.hasSet()) {
                throw new Exception("Connection Not Set");
            }
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
    public ResultSet executeAsResultSet() {
        try {
            if (!ApiConnection.hasSet()) {
                throw new Exception("Connection Not Set");
            }
            ApiConnection.createConnection();
            Statement state = ApiConnection.getConnection().createStatement();
            prepareQuery();
            ResultSet rs = state.executeQuery(mQuery);
            return rs;
        } catch (Exception e) {
            System.err.println("READ QUERY ERROR :" + e);
        }
        ApiConnection.closeConnection();
        return null;
    }

    @Override
    public void inputCustomQuery(String query) {
        mQuery = query;
    }

    @Override
    public Condition conditionEqual(Object a, Object b) {
        mWhere = mWhere.concat(a + " = " + Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionNotEqual(Object a, Object b) {
        mWhere = mWhere.concat(a + " <> " + Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionGraterThanOrEqual(Object a, Object b) {
        mWhere = mWhere.concat(a + " >= " + Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionLessThanOrEqual(Object a, Object b) {
        mWhere = mWhere.concat(a + " <= " + Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionLessThan(Object a, Object b) {
        mWhere = mWhere.concat(a + " < " + Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionGraterThan(Object a, Object b) {
        mWhere = mWhere.concat(a + " > " + Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionBetween(String column, Object a, Object b) {
        mWhere = mWhere.concat(column + " Between " + a + " AND " + b);
        return new Condition();
    }

    @Override
    public Condition conditionLike(String column, String b) {
        mWhere = mWhere.concat(column + " Like " + Tools.convertToQueryValue(b));
        return new Condition();
    }

    @Override
    public Condition conditionIn(String column, Object[] b) {
        mWhere = mWhere.concat(column + " IN(");
        for (Object object : b) {
            object = object instanceof String ? "'" + object + "'" : object;
            mWhere = mWhere.concat(object + ",");
        }

        if (mWhere.charAt(mWhere.length() - 1) == ',') {
            mWhere = mWhere.substring(0, mWhere.length() - 1);
        }
        mWhere = mWhere.concat(")");

        return new Condition();
    }

    @Override
    public ApiReadQuery addNaturalJoinTable(String table) {
        if (mTable.isEmpty()) {
            System.err.println("ERROR! table must Be define First, Auto define table");
            mTable = table;
        } else {
            mTable += " NATURAL JOIN " + table;
        }
        mLastTable = table;
        return this;
    }

    @Override
    public ApiReadQuery addCrossJoinTable(String table) {
        if (mTable.isEmpty()) {
            System.err.println("ERROR! table must Be define First, Auto define table");
            mTable = table;
        } else {
            mTable += " CROSS JOIN " + table;
        }
        mLastTable = table;
        return this;
    }

    @Override
    public ApiReadQuery addInnerJoinTable(String table1, String column1, String table2, String column2) {
        if (mTable.isEmpty()) {
            System.err.println("ERROR! table must Be define First, Auto define table");
            mTable = table1;
        } else {
            mTable += " JOIN " + table1 + " ON (" + table1 + "." + column1 + " = " + table2 + "." + column2 + ") ";
        }
        mLastTable = table1;
        return this;
    }

    @Override
    public ApiReadQuery addLeftJoinTable(String table1, String column1, String table2, String column2) {
        if (mTable.isEmpty()) {
            System.err.println("ERROR! table must Be define First, Auto define table");
            mTable = table1;
        } else {
            mTable += " LEFT JOIN " + table1 + " ON (" + table1 + "." + column1 + " = " + table2 + "." + column2 + ") ";
        }
        mLastTable = table1;
        return this;
    }

    @Override
    public ApiReadQuery addRightJoinTable(String table1, String column1, String table2, String column2) {
        if (mTable.isEmpty()) {
            System.err.println("ERROR! table must Be define First, Auto define table");
            mTable = table1;
        } else {
            mTable += " RIGHT JOIN " + table1 + " ON (" + table1 + "." + column1 + " = " + table2 + "." + column2 + ") ";
        }
        mLastTable = table1;
        return this;
    }

    @Override
    public ApiReadQuery addFullJoinTable(String table1, String column1, String table2, String column2) {
        if (mTable.isEmpty()) {
            System.err.println("ERROR! table must Be define First, Auto define table");
            mTable = table1;
        } else {
            mTable += " FULL JOIN " + table1 + " ON (" + table1 + "." + column1 + " = " + table2 + "." + column2 + ") ";
        }
        mLastTable = table1;
        return this;
    }

    @Override
    public ApiReadQuery addInnerJoinTable(String table, String column) {
        if (mTable.isEmpty()) {
            System.err.println("ERROR! table must Be define First, Auto define table");
            mTable = table;
        } else {
            mTable += " JOIN " + table + " USING (" + column + ") ";
        }
        mLastTable = table;
        return this;
    }

    public class Condition extends ApiReadQuery<T> {

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

        public List<T> execute() {
            return ApiReadQuery.this.execute();
        }

        public ResultSet executeAsResultSet() {
            return ApiReadQuery.this.executeAsResultSet();
        }
    }
}
