/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;

/**
 *
 * @author maqielhm
 */
public class Tools {

    public static Boolean isDigit(String b){
        String regex = "[0-9]+";
        return(b.matches(regex));
    }
    
    public static Boolean isDigit(Object b){
        if(b instanceof Integer) return true;
        else return false;
    }
    
    public static String convertToQueryValue(String text){
        if(isDigit(text)){
            return text;
        }else {
            return "'"+text+"'";
        }
    }
    
    public static String convertToQueryValue(Object text){
        if(isDigit(text)){
            return (String) text;
        }else {
            return "'"+text+"'";
        }
    }
    
    
}
