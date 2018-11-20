/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

import java.sql.ResultSet;

/**
 *
 * @author maqielhm
 */
public interface ApiBaseQuery<T> {
    public ResultSet execute();
    public String prepareQuery();
}