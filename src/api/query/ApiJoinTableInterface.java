/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

/**
 *
 * @author maqielhm
 */
public interface ApiJoinTableInterface<K> {
    public K addNaturalJoinTable(String table);
    public K addCrossJoinTable(String table);
    public K addInnerJoinTable(String table1, String column1,String table2, String column2);
    public K addInnerJoinTable(String table, String column);
    public K addLeftJoinTable(String table1, String column1,String table2, String column2);
    public K addRightJoinTable(String table1, String column1,String table2, String column2);
    public K addFullJoinTable(String table1, String column1,String table2, String column2);
}
