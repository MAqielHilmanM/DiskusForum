/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.daos;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author maqielhm
 */
public abstract class BaseDao<T> {

    private String mTableName;
    private String[] mColumns;

    public BaseDao(String mTableName, String[] mColumns) {
        this.mTableName = mTableName;
        this.mColumns = mColumns;
    }

    public abstract List<T> toObjects(ResultSet rs);

    public String getTableName() {
        return mTableName;
    }

    protected void setTableName(String tableName){
        this.mTableName = tableName;
    }

    public String[] getColumns() {
        return mColumns;
    }

    protected void setColumns(String[] columns){
        this.mColumns = columns;
    }
}
