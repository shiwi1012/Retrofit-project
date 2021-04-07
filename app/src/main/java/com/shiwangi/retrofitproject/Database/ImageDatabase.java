package com.shiwangi.retrofitproject.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.shiwangi.retrofitproject.Dao.ImageDao;
import com.shiwangi.retrofitproject.Model.Image;

@Database(entities = {Image.class}, version = 1)
public abstract class ImageDatabase extends RoomDatabase {
    private static final String DATABASE_NAME="ImageDatabase";

    public abstract ImageDao imageDao();

    private static volatile ImageDatabase INSTANCE;

    public static ImageDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (ImageDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, ImageDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };
    static class PopulateAsynTask extends AsyncTask<Void, Void, Void> {

        private ImageDao imageDao;
        PopulateAsynTask(ImageDatabase imageDatabase) {
            imageDao = imageDatabase.imageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            imageDao.delete();
            return null;
        }
    }
}
