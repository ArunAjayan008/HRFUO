package com.polymorfuz.hrfuo.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProfileDao {
    @Insert(onConflict=OnConflictStrategy.IGNORE)
    void insert(ProfileDB profile);

    @Query("DELETE FROM profile_db")
    void deleteAll();

    @Query("SELECT * from profile_db")
    LiveData<List<ProfileDB>> getProfile();
}
