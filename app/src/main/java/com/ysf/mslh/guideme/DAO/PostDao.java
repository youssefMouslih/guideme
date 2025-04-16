package com.ysf.mslh.guideme.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ysf.mslh.guideme.models.Post;

import java.util.List;

@Dao
public interface PostDao {

    @Insert
    void insert(Post post);

    @Query("SELECT * FROM posts WHERE user_id = :userId ORDER BY posted_at DESC")
    List<Post> getPostsByUser(int userId);

    @Query("SELECT * FROM posts WHERE expires_at > CURRENT_TIMESTAMP")
    List<Post> getActivePosts();
}
