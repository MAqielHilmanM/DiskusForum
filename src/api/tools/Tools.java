/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author maqielhm
 */
public class Tools {

    public static Boolean isDigit(String b) {
        String regex = "[0-9]+";
        return (b.matches(regex));
    }

    public static Boolean isDigit(Object b) {
        if (b instanceof Integer) {
            return true;
        } else {
            return false;
        }
    }

    public static String convertToQueryValue(String text) {
        if (isDigit(text)) {
            return text;
        }
        if (text.contains("SHA1")) {
            return text;
        } else {
            return "'" + text + "'";
        }
    }

    public static String convertToQueryValue(Object text) {
        if (isDigit(text)) {
            return (String) text;
        }
        if (((String) text).contains("SHA1")) {
            return (String) text;
        } else {
            return "'" + text + "'";
        }
    }

    public static String encryptWithSHA1(String data) {
        return "SHA1('" + data + "')";
    }

    public static String generateId(String codeType, int length) {
        if (length > codeType.length()+1) {
            int availableSpace = length - (codeType.length()+1);
            int max = (int) Math.pow(10, availableSpace);
            int min = (int) Math.pow(10, availableSpace - 1);

            Random rand = new Random();
            int id = rand.nextInt(max - min) + min;

            return codeType + "-" + id;
        } else {
            return codeType;
        }
    }

    public static String generateId(int length) {
        int availableSpace = length;
        int max = (int) Math.pow(10, availableSpace);
        int min = (int) Math.pow(10, availableSpace - 1);

        Random rand = new Random();
        int id = rand.nextInt(max - min) + min;

        return String.valueOf(id);

    }

    public static String generateId(String codeType) {
        return codeType;
    }

    public static String generateId(String codeType, int length, String define) {
        return codeType + "-" + define;
    }

}
