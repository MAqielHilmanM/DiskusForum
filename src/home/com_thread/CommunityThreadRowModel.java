/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.com_thread;

/**
 *
 * @author maqielhm
 */
public class CommunityThreadRowModel {
    private String id;
    private String date;
    private String title;
    private String body;

    public CommunityThreadRowModel(String id, String date, String title, String body) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
    
    
}
