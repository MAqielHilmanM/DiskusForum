/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.hot_thread;

/**
 *
 * @author maqielhm
 */
public class HotThreadRowModel {
    private String id;
    private String url_pic;
    private String name;
    private String community_name;
    private String title;
    private String preview;
    private String date;

    public HotThreadRowModel(String id, String url_pic, String name, String community_name, String title, String preview, String date) {
        this.id = id;
        this.url_pic = url_pic;
        this.name = name;
        this.community_name = community_name;
        this.title = title;
        this.preview = preview;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getUrl_pic() {
        return url_pic;
    }

    public String getName() {
        return name;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public String getTitle() {
        return title;
    }

    public String getPreview() {
        return preview;
    }

    public String getDate() {
        return date;
    }
    
}
