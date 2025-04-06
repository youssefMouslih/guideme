package com.ysf.mslh.guideme.models;

public class MediaItem {
    private String id;
    private String imageUrl;
    private boolean isVideo;

    public MediaItem(String id, String imageUrl, boolean isVideo) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.isVideo = isVideo;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isVideo() {
        return isVideo;
    }
}