/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import api.table.TableUser;
import com.sun.istack.internal.Nullable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Constant;

/**
 *
 * @author maqielhm
 */
public class ApiQuery<T> {
    
    private T className;
    
    private String defineClass() {
        if (className instanceof TableUser) {
            return TableUser.TABLE_NAME;
        }
        
        return "";
    }
    
    private String defineColumn(List<String> column) {
        if (column.size() >= 0) {
            String s = "";
            for (int i = 0; i < column.size(); i++) {
                s.concat(column.get(i));
                if (column.size() >= 1 && i != (column.size() - 1)) {
                    s.concat(",");
                }
            }
            return s;
        }
        return "*";
    }
    
    private List<String> defineAllColumn(){
        List<String> column = new ArrayList<>();
        if(className instanceof TableUser){
           
        }
    }
    
    public ResultSet getResultSingleTable(List<String> column) {
        try {
            Statement state = ApiConnection.getConnection().createStatement();
            String query = "Select " + defineColumn(column) + " from " + defineClass();
            ResultSet rs = state.executeQuery(query);
            return rs;
        } catch (Exception e) {
            return null;
        }
    }
    
    public void readSingleTable(List<String> column) {
        try {
            ResultSet rs = getResultSingleTable(column);
            while (rs.next()) {
                for (int i = 0; i < column.size(); i++) {
                    System.out.println(column.get(i) + " : " + rs.getString(column.get(i)));
                }
            }
        } catch (Exception e) {
            
        }
    }
    
}
