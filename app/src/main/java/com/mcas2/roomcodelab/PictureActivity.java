package com.mcas2.roomcodelab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mcas2.roomcodelab.api.NINJAapi;
import com.mcas2.roomcodelab.entities.Picture;
import com.mcas2.roomcodelab.entities.Word;
import com.mcas2.roomcodelab.picturerecycler.PictureAdapter;
import com.mcas2.roomcodelab.viewmodels.PictureViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PictureActivity extends AppCompatActivity {
    private PictureViewModel pictureViewModel;
    private List<Picture> getPictures;
    private PictureAdapter pictureAdapter;
    private RecyclerView recyclerView;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        repository = new Repository(getApplication());
        getPictures = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        pictureViewModel = new ViewModelProvider(this).get(PictureViewModel.class);

        pictureAdapter = new PictureAdapter(this, getPictures);
        makeRequest();
        pictureViewModel.getAllPictures().observe(this, new Observer<List<Picture>>() {
            @Override
            public void onChanged(List<Picture> pictures) {
                recyclerView.setAdapter(pictureAdapter);
                pictureAdapter.getAllDatas(pictures);
                Log.d("main", "onChanged: " + pictures);
            }
        });
        }

        private void makeRequest () {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.api-ninjas.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            NINJAapi api = retrofit.create(NINJAapi.class);
            Call<List<Picture>> call = api.getPictures(getRandomWord());
            call.enqueue(new retrofit2.Callback<List<Picture>>() {
                @Override
                public void onResponse(Call<List<Picture>> call, Response<List<Picture>> response) {
                    if (response.isSuccessful()) {
                        repository.insertPicture(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Picture>> call, Throwable t) {
                    Log.d("main", "onFailure: " + t.getMessage());
                    Toast.makeText(PictureActivity.this, "Está fallando", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private String getRandomWord() {
            String [] words = {"nature", "city", "technology", "food", "still_life", "abstract", "wildlife"};
            return words[new Random().nextInt(words.length)];
        }
    }
