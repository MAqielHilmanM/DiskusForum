/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.member;

/**
 *
 * @author maqielhm
 */
public class MemberRowModel {
    private String id;
    private String name;
    private String communityPosition;
    private String title;
    private String communityName;
    private String numberPost;
    private String status;

    public MemberRowModel(String id, String name, String communityPosition, String title, String communityName, String numberPost, String status) {
        this.id = id;
        this.name = name;
        this.communityPosition = communityPosition;
        this.title = title;
        this.communityName = communityName;
        this.numberPost = numberPost;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCommunityPosition() {
        return communityPosition;
    }

    public String getTitle() {
        return title;
    }

    public String getCommunityName() {
        return communityName;
    }

    public String getNumberPost() {
        return numberPost;
    }

    public String getStatus() {
        return status;
    }
    
    
}
