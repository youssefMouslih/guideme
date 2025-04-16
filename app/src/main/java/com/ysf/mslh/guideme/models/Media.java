package com.ysf.mslh.guideme.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "media", foreignKeys = @ForeignKey(entity = Experience.class,
        parentColumns = "id", childColumns = "experience_id", onDelete = ForeignKey.CASCADE))
public class Media {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "experience_id")
    public int experienceId;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "type")
    public String type; // 'image' or 'video'

    @ColumnInfo(name = "uploaded_at")
    public String uploadedAt;
}
