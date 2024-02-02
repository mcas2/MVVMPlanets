package com.mcas2.roomcodelab.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mcas2.roomcodelab.Repository;
import com.mcas2.roomcodelab.entities.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private Repository repository;
    private final LiveData<List<Word>> mAllWords;
    public WordViewModel (Application application) {
        super(application);
        repository = new Repository(application);
        mAllWords = repository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        repository.insertWord(word);
    }
}
