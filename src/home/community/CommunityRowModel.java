/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.community;

/**
 *
 * @author maqielhm
 */
public class CommunityRowModel {
    private String id;
    private String title;
    private String name;
    private String body;
    private String date;

    public CommunityRowModel(String id, String title, String name, String body, String date) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.body = body;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }
    
    
    
}
