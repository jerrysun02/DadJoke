package com.example.dadjoke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import com.example.dadjoke.databinding.ActivityMainBinding;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    final JokeViewModel jokeViewModel = new JokeViewModel();
    FavJokes favJokes = new FavJokes();
    JokeModel currentJokeModel;
    JokeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initUI();

        try {
            jokeViewModel.getJoke().observe(this, new Observer<List<JokeModel>>() {
                @Override
                public void onChanged(List<JokeModel> jokeModels) {
                    if (jokeModels.size() > 0) {
                        currentJokeModel = jokeModels.get(0);
                        binding.textViewJokeContent.setText(currentJokeModel.getJoke());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initUI() {
        List<JokeModel> jokeModelList = new ArrayList<>();
        adapter = new JokeAdapter(this, jokeModelList);
        binding.recyclerViewJoke.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewJoke.setAdapter(adapter);
    }

    public void onGet(View view) {
        binding.recyclerViewJoke.setVisibility(View.GONE);
        binding.textViewJokeContent.setVisibility(View.VISIBLE);
        try {
            jokeViewModel.getJoke().observe(this, new Observer<List<JokeModel>>() {
                @Override
                public void onChanged(List<JokeModel> jokeModels) {
                    if (jokeModels.size() > 0) {
                        currentJokeModel = jokeModels.get(0);
                        binding.textViewJokeContent.setText(currentJokeModel.getJoke());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onAdd(View view) {
        if (!favJokes.getFavJokes().contains(currentJokeModel)) {
           favJokes.addJoke(currentJokeModel);
        }
    }

    public void onFav(View view) {
        binding.textViewJokeContent.setVisibility(View.GONE);
        binding.recyclerViewJoke.setVisibility(View.VISIBLE);
        adapter = new JokeAdapter(this, favJokes.getFavJokes());
        binding.recyclerViewJoke.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewJoke.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}