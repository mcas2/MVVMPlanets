package com.mcas2.roomcodelab;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mcas2.roomcodelab.Repository;
import com.mcas2.roomcodelab.entities.Picture;

import java.util.List;

public class PictureViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<Picture>> getAllCats;

    public PictureViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        getAllCats=repository.getAllPictures();
    }

    public void insert(List<Picture> pictures){
        repository.insertPicture(pictures);
    }

    public LiveData<List<Picture>> getAllPictures()
    {
        return getAllCats;
    }
}