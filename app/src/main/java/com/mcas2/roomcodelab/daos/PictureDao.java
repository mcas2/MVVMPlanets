package com.mcas2.roomcodelab.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mcas2.roomcodelab.entities.Picture;

import java.util.List;

@Dao
public interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Picture> pictures);

    @Query("SELECT DISTINCT * FROM picture_table")
    LiveData<List<Picture>> getPictures();

    @Query("DELETE FROM picture_table")
    void deleteAll();
}
