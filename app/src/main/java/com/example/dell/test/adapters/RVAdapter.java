package com.example.dell.test.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.test.R;
import com.example.dell.test.models.Movie;


import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MovieViewHolder> {
    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView movieName;
        TextView movieText;
        ImageView moviePhoto;
        TextView details;
        TextView share;

        public MovieViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            movieName = itemView.findViewById(R.id.movie_name);
            movieText = itemView.findViewById(R.id.movie_text);
            moviePhoto = itemView.findViewById(R.id.movie_photo);
            details = itemView.findViewById(R.id.details);
            share = itemView.findViewById(R.id.share);
        }


    }

    private List<Movie> movies;

    public RVAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void removeItem(int position) {

        if (movies.size() > 0) {
            movies.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void addItem(Movie movie) {
        int index = 0;
        if (movies.size() > 0) {
            index = 1;
        }
        movies.add(index, movie);
        notifyItemInserted(index);

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public @NonNull
    MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);

        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.movieName.setText(movies.get(i).getName());
        movieViewHolder.movieText.setText(movies.get(i).getText());
        Glide
                .with(movieViewHolder.moviePhoto.getContext())
                .load(movies.get(i).getPhotoId())
                .into(movieViewHolder.moviePhoto);
        movieViewHolder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Details :" + movies.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        movieViewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.extra_subject);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Hello : " + movies.get(i).getName());
                v.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

}
