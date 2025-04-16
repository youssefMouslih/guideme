package com.ysf.mslh.guideme.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "middle_name")
    public String middleName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "bio")
    public String bio;

    @ColumnInfo(name = "profile_picture")
    public String profilePicture;
}
