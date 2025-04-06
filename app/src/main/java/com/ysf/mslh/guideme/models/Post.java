package com.ysf.mslh.guideme.models;

public class Post {
    private String username;
    private String location;
    private String imageUrl;
    private String postText;
    private String timePost;

    public Post(String username, String location, String imageUrl, String postText, String timePost) {
        this.username = username;
        this.location = location;
        this.imageUrl = imageUrl;
        this.postText = postText;
        this.timePost = timePost;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPostText() {
        return postText;
    }

    public String getTimePost() {
        return timePost;
    }
}
