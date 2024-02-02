package com.mcas2.roomcodelab.api;

import com.mcas2.roomcodelab.entities.Picture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NINJAapi {
    @Headers("X-Api-Key: " + "p4hwtOEyoLfCz3Vfy0eJ3g==V9Ul4glcUK9OZbQb")
    @GET("randomimage")
    Call<List<Picture>> getPictures(@Query("category") String category);
}
