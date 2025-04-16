package com.ysf.mslh.guideme.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ysf.mslh.guideme.models.Experience;

import java.util.List;

@Dao
public interface ExperienceDao {

    @Insert
    void insert(Experience experience);

    @Query("SELECT * FROM experiences WHERE owner_id = :ownerId")
    List<Experience> getExperiencesByOwner(int ownerId);

    @Query("SELECT * FROM experiences WHERE category_id = :categoryId")
    List<Experience> getExperiencesByCategory(int categoryId);
}
