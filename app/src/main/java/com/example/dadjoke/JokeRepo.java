package com.example.dadjoke;

import androidx.lifecycle.MutableLiveData;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokeRepo {

    public MutableLiveData<List<JokeModel>> requestJokes() throws JSONException {
        MutableLiveData<List<JokeModel>> mutableLiveData = new MutableLiveData<>();
        List<JokeModel> jokeModelList = new ArrayList<>();
        JokeModel jokeModel = new JokeModel();

        NetworkService networkService = NetworkServiceCreator.createService(NetworkService.class);
        networkService.fetchJoke().enqueue(new Callback<FetchJokeResponse>() {
            @Override
            public void onResponse(Call<FetchJokeResponse> call, Response<FetchJokeResponse> response) {
                jokeModel.setId(response.body().getId());
                jokeModel.setJoke(response.body().getJoke());
                jokeModel.setStatus(response.body().getStatus());
                List<JokeModel> jokeModelList = new ArrayList<>();
                jokeModelList.add(jokeModel);
                mutableLiveData.postValue(jokeModelList);
            }

            @Override
            public void onFailure(Call<FetchJokeResponse> call, Throwable t) {
            }
        });

        mutableLiveData.setValue(jokeModelList);
        return mutableLiveData;
    }
}
