/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

import api.ApiConnection;
import api.daos.BaseDao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author maqielhm
 * @param <T> for Table Class Name
 */
public class ApiInsertQuery<T extends BaseDao<T>> extends ApiBaseQuery<Boolean> {

    private final HashMap<String, String> mColumnsValue;
    private String mTableName;
    private String mQuery = "";
    private T mTable;

    public ApiInsertQuery() {
        mColumnsValue = new HashMap<>();
    }

    public ApiInsertQuery(String table) {
        mColumnsValue = new HashMap<>();
        mTableName = table;
    }

    public ApiInsertQuery(T mTable) {
        this.mTable = mTable;
        mTableName = mTable.getTableName();
        mColumnsValue = new HashMap<>();
    }

    public ApiInsertQuery defineTable(String table) {
        mTableName = table;
        return this;
    }

    public ApiInsertQuery defineTable(T table) {
        mTableName = table.getTableName();
        return this;
    }

    public ApiInsertQuery insertColumnValue(String column, String Value) {
        if (mTableName.isEmpty()) {
            System.out.println("Table Name is Empty");
            return null;
        } else {

            mColumnsValue.put(column, Value);
            return this;
        }

    }

    @Override
    public Boolean execute() {
        try {
            if (!ApiConnection.hasSet()) {
                throw new Exception("Connection Not Set");
            }
            ApiConnection.createConnection();
            Statement state = ApiConnection.getConnection().createStatement();
            prepareQuery();
            if (state.executeUpdate(mQuery) > 0) {
                System.out.println("Insert Data Sucess");
                return true;
            }
            ApiConnection.closeConnection();
        } catch (Exception e) {
            System.err.println("READ QUERY ERROR :" + e);
        }

        System.out.println("Insert Data Failed");
        return false;
    }

    @Override
    public void prepareQuery() {
        if (mQuery.isEmpty()) {
            mQuery = mQuery.concat("INSERT INTO ");
            mQuery = mQuery.concat(mTableName);

            List<String> columns = new ArrayList<>();
            List<String> values = new ArrayList<>();

            mColumnsValue.forEach((t, u) -> {
                columns.add(t);
                values.add(u);
            });

            mQuery = mQuery.concat("(");
            for (int i = 0; i < columns.size(); i++) {
                mQuery = mQuery.concat(columns.get(i));
                if (i < columns.size() - 1) {
                    mQuery = mQuery.concat(",");
                }
            }
            mQuery = mQuery.concat(") VALUES (");
            for (int i = 0; i < values.size(); i++) {
                if (!values.get(i).contains("SHA1")) {
                    mQuery = mQuery.concat("'");
                }
                mQuery = mQuery.concat(values.get(i));
                if (!values.get(i).contains("SHA1")) {
                    mQuery = mQuery.concat("'");
                }
                if (i < values.size() - 1) {
                    mQuery = mQuery.concat(",");
                }
            }
            mQuery = mQuery.concat(")");

        }

        System.out.println("QUERY = " + mQuery);
    }

    @Override
    public void inputCustomQuery(String query) {
        mQuery = query;
    }

}
