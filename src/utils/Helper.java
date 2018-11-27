/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import static utils.Constant.VALID_EMAIL_ADDRESS_REGEX;

/**
 *
 * @author maqielhm
 */
public class Helper {
    public static List<String> arrayToList(String[] s){
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            lists.add(s[i]);
        }
        return lists;
    }
    
    public static boolean checkEmailRegex(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
