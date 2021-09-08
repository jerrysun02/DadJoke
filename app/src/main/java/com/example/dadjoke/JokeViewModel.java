package com.example.dadjoke;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import org.json.JSONException;
import java.util.List;

public class JokeViewModel {

    private JokeRepo jokeRepo;
    private MutableLiveData<List<JokeModel>> mutableLiveData;

    public JokeViewModel() {
        jokeRepo = new JokeRepo();
    }

    public LiveData<List<JokeModel>> getJoke() throws JSONException {
        //if (mutableLiveData == null) {
            mutableLiveData = jokeRepo.requestJokes();
        //}
        return mutableLiveData;
    }
}
