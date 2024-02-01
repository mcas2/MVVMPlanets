package com.mcas2.roomcodelab.api;

import com.mcas2.roomcodelab.entities.Picture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NINJAapi {
    @GET("search")
    Call<List<Picture>> getPictures(@Query("limit") int limit);
}
