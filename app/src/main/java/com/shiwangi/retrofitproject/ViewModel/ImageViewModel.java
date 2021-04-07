package com.shiwangi.retrofitproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.shiwangi.retrofitproject.Model.Image;
import com.shiwangi.retrofitproject.Repository.ImageRepository;

import java.util.List;

public class ImageViewModel extends AndroidViewModel {

    private ImageRepository imageRepository;
    private LiveData<List<Image>> getAllImages;

    public ImageViewModel(@NonNull Application application) {
        super(application);
        imageRepository = new ImageRepository(application);
        getAllImages = imageRepository.getAllImages();
    }

    public void insert(List<Image> list) {
        imageRepository.insert(list);
    }

    public LiveData<List<Image>> getAllImages() {
        return getAllImages;
    }
}
