package com.shiwangi.retrofitproject.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "image")
public class Image {
    @PrimaryKey(autoGenerate = true)
    private int imageId;

    @SerializedName("id")
//    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("download_url")
    @ColumnInfo(name = "picture")
    private String picture;

    public Image(int id, String picture) {
        this.id = id;
        this.picture = picture;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", id=" + id +
                ", picture='" + picture + '\'' +
                '}';
    }
}
