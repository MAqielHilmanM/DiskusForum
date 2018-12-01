/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.comment;

/**
 *
 * @author maqielhm
 */
public class CommentRowModel {
    private String id;
    private String url;
    private String date;
    private String body;
    private String name;

    public CommentRowModel(String id, String url, String date, String body,String name) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.body = body;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    public String getName() {
        return name;
    }
}
