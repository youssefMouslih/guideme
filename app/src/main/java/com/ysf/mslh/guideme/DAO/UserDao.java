package com.ysf.mslh.guideme.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ysf.mslh.guideme.models.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM users WHERE id = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);
}
