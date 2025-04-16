package com.ysf.mslh.guideme.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class Posta {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_id")
    public int userId;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "media_url")
    public String mediaUrl;

    @ColumnInfo(name = "type")
    public String type; // 'image' or 'video'

    @ColumnInfo(name = "posted_at")
    public String postedAt;

    @ColumnInfo(name = "expires_at")
    public String expiresAt;

    @ColumnInfo(name = "likes_count")
    public int likesCount;

    @ColumnInfo(name = "favorites_count")
    public int favoritesCount;
}
