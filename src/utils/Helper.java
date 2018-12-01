/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
    
    public static String convertNameToShortName(String name,int limit){
        String[] names = name.split(" ");
        String shortName ="";
        int sum = 0;
        for (int i = 0; i < names.length; i++) {
            if(sum <= limit){
                shortName += names[i]+" ";
                sum += names[i].length();
            }else{
                shortName += names[i].charAt(0)+" ";
            }
        }
        return shortName;
    }
    
    public static String convertFullDateToString(Date date){
        DateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        return df.format(date);
    }
    
    public static String convertDateToString(Date date){
        DateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        return df.format(date);
    }

    public static String convertTimeToString(Date date){
        DateFormat df = new SimpleDateFormat("HH:mm:ss a");
        return df.format(date);
    }
    
        public static FocusListener pfFocusListener(JPasswordField field, String defaultText) {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(defaultText)) {
                    field.setText("");
                    field.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().equals("")) {
                    field.setText(defaultText);
                    field.setEchoChar((char) 0);
                }
            }
        };
    }
    
    public static FocusListener tfFocusListener(JTextField field, String defaultText) {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(defaultText)) {
                    field.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().equals("")) {
                    field.setText(defaultText);
                }
            }
        };
    }
    public static void setScrollPanelList(JScrollPane scrollPanel, JList list) {
        scrollPanel.setViewportView(list);
        scrollPanel.setBorder(null);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setOpaque(false);
        scrollPanel.getViewport().setOpaque(false);
    }

    public static String getPercentageFromAverage(int curr, int max){
        if(curr != 0 && max != 0)
        return (curr/max)*100+" %";
        else return "0 %";
    }
}
