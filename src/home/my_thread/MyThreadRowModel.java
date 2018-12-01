/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.my_thread;

/**
 *
 * @author maqielhm
 */
public class MyThreadRowModel {
    private String id;
    private String date;
    private String communityName;
    private String title;
    private String body;

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getCommunityName() {
        return communityName;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public MyThreadRowModel(String id, String date, String communityName, String title, String body) {
        this.id = id;
        this.date = date;
        this.communityName = communityName;
        this.title = title;
        this.body = body;
    }

}
