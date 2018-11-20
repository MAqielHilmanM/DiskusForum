/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

import api.ApiConnection;
import api.daos.UserDao;
import com.sun.istack.internal.Nullable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Constant;
import utils.Tools;

/**
 *
 * @author maqielhm
 */
enum Relation {
    NATURAL,
    JOINON,
    JOINUSING,
    FULLOUTERJOIN,
    LEFTOUTERJOIN,
    RIGHTOUTERJOIN,
    INNERJOIN,
    CROSSJOIN
}

enum Grouping {
    AVG,
    SUM,
    MIN
}

class Relational {

    Relation relation;
    String condition;

    public Relational(Relation relation, String condition) {
        this.relation = relation;
        this.condition = condition;
    }

}

public class ApiReadQuery implements ApiBaseQuery<ApiReadQuery>{

    final private List<String> mColumns;
    final private List<String> mTables;
    final private List<Relational> mRelations;
    final private List<String> mGroups;
    final private List<String> mWhere;
    private boolean mShowAll = false;

    public ApiReadQuery(String table) {
        mTables = new ArrayList<>();
        mColumns = new ArrayList<>();
        mRelations = new ArrayList<>();
        mGroups = new ArrayList<>();
        mWhere = new ArrayList<>();
        mTables.add(table);
    }

    public ApiReadQuery() {
        mTables = new ArrayList<>();
        mColumns = new ArrayList<>();
        mRelations = new ArrayList<>();
        mGroups = new ArrayList<>();
        mWhere = new ArrayList<>();
    }

    public ApiReadQuery addTable(String tableName) {
        if (mTables.size() < 1) {
            mTables.add(tableName);
        }
        System.err.println("Please add relation to add multitable");
        return this;
    }

    public ApiReadQuery addTable(String tableName, Relation relation, String condition) {
        mTables.add(tableName);
        mRelations.add(new Relational(relation, condition));
        return this;
    }

    public ApiReadQuery addGroupBy(String columnName) {
        mGroups.add(columnName);
        return this;
    }

    public ApiReadQuery addWhere(String condition) {
        mWhere.add(condition);
        return this;
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

    @Override
    public String prepareQuery() {
        String query = "SELECT ";

        if (mShowAll) {
            query = query.concat("* ");
        } else {
            for (int i = 0; i < mColumns.size(); i++) {
                query = query.concat(mColumns.get(i));
                if (i != mColumns.size()-1) {
                    query = query.concat(",");
                }
                query = query.concat(" ");
            }
        }

        query = query.concat("FROM ");

        query = query.concat(mTables.get(0));
        if (mRelations.size() > 0) {
            int tableCount = 1;
            for (int i = 0; i < mRelations.size(); i++) {
                if (mRelations.get(i).relation == Relation.NATURAL) {
                    query = query.concat("NATURAL ");
                    query = query.concat(mTables.get(tableCount));
                    query = query.concat(" ");
                    tableCount++;
                }
            }
        } else {
            query = query.concat(" ");
        }

        System.out.println("READ QUERY : " + query);
        return query;
    }

    @Override
    public ResultSet execute() {
        try {
            Statement state = ApiConnection.getConnection().createStatement();
            String query = prepareQuery();
            return state.executeQuery(query);
            
        } catch(Exception e){
            System.err.println("READ QUERY ERROR :"+e);
        }
        
        return null;
    }
}
