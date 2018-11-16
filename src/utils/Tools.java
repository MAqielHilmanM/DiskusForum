/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maqielhm
 */
public class Tools {
    public static List<String> arrayToList(String[] s){
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            lists.add(s[i]);
        }
        return lists;
    }
}
