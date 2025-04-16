package com.ysf.mslh.guideme.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ysf.mslh.guideme.models.Story;

import java.util.List;

@Dao
public interface StoryDao {

    @Insert
    void insert(Story story);

    @Query("SELECT * FROM stories WHERE user_id = :userId AND expires_at > CURRENT_TIMESTAMP")
    List<Story> getActiveStoriesByUser(int userId);
}
