package com.mcas2.roomcodelab;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mcas2.roomcodelab.entities.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private Repository mRepository;
    private final LiveData<List<Word>> mAllWords;
    public WordViewModel (Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insertWord(word);
    }
}
