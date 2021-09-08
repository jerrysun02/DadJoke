package com.example.dadjoke;

import java.util.ArrayList;
import java.util.List;

public class FavJokes {

    private static List<JokeModel> favJokes = new ArrayList<>();

    public List<JokeModel> getFavJokes() {
        return favJokes;
    }

    public void addJoke(JokeModel joke) {
        favJokes.add(joke);
    }

    public void deleteJoke(int pos) {
        favJokes.remove(pos);
    }
}
