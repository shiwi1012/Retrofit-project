package com.shiwangi.retrofitproject.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.shiwangi.retrofitproject.Dao.ImageDao;
import com.shiwangi.retrofitproject.Database.ImageDatabase;
import com.shiwangi.retrofitproject.Model.Image;

import java.util.List;

public class ImageRepository {

    private ImageDatabase database;
    private LiveData<List<Image>> getAllImages;

    public ImageRepository(Application application) {
        database = ImageDatabase.getInstance(application);
        getAllImages = database.imageDao().getAllImages();
    }

    public void insert(List<Image> imageList) {
        new InsertAsyncTask(database).execute(imageList);
    }

    public LiveData<List<Image>> getAllImages() {
        return getAllImages;
    }

    static class InsertAsyncTask extends AsyncTask<List<Image>,Void,Void> {
        private ImageDao imageDao;
        InsertAsyncTask(ImageDatabase imageDatabase) {
            imageDao = imageDatabase.imageDao();
        }

        @Override
        protected Void doInBackground(List<Image>... lists) {
            imageDao.insert(lists[0]);
            return null;
        }
    }
}
