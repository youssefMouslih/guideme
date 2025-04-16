package com.ysf.mslh.guideme.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "experiences", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id", childColumns = "owner_id", onDelete = ForeignKey.CASCADE))
public class Experience {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "owner_id")
    public int ownerId;

    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "available_from")
    public String availableFrom;

    @ColumnInfo(name = "available_to")
    public String availableTo;
}
