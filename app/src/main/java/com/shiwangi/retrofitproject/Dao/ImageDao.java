package com.shiwangi.retrofitproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.shiwangi.retrofitproject.Model.Image;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Image> imageList);

    @Query("SELECT * FROM image")
    LiveData<List<Image>> getAllImages();

    @Query("DELETE FROM image")
    void delete();

}
