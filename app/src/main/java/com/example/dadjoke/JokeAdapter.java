package com.example.dadjoke;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {

    private List<JokeModel> jokeModels;
    private LayoutInflater mInflater;

    private FavJokes favJokes = new FavJokes();

    public JokeAdapter(Context context, List<JokeModel> jokeModels) {
        this.mInflater = LayoutInflater.from(context);
        this.jokeModels = jokeModels;
    }

    public void addJokes(List<JokeModel> jokeModels) {
        this.jokeModels = jokeModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_joke, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JokeModel jokeModel = jokeModels.get(position);
        holder.textViewJoke.setText(jokeModel.getJoke());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewJoke;
        Button buttonDel;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewJoke = itemView.findViewById(R.id.textViewJoke);
            buttonDel = itemView.findViewById(R.id.buttonDel);
            buttonDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favJokes.deleteJoke(getAdapterPosition());
                    removeAt(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return jokeModels != null ? jokeModels.size() : 0;
    }

    public void removeAt(int position) {
        notifyItemRemoved(position);
    }
}
