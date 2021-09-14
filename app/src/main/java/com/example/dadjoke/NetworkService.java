package com.example.dadjoke;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NetworkService {
    @Headers("Accept: application/json")
    @GET("/")
    Call<FetchJokeResponse> fetchJoke();
}
