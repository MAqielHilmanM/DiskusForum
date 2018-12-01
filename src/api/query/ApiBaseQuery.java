/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

import api.daos.BaseDao;
import java.sql.ResultSet;

/**
 *
 * @author maqielhm
 * @param <T> 
 * @param <K> Inner Class for Condition
 */



public abstract class ApiBaseQuery<T>{
    public abstract void prepareQuery();
    public abstract T execute();
    public abstract void inputCustomQuery(String query);
    public ResultSet executeAsResultSet(){return null;}
}