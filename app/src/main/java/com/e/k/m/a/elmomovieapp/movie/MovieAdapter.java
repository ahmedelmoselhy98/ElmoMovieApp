package com.e.k.m.a.elmomovieapp.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.k.m.a.elmomovieapp.R;
import com.e.k.m.a.elmomovieapp.models.MovieModel;

import java.util.List;

/**
 * Created by ahmedelmoselhy on 3/4/2018.
 */

public class MovieAdapter extends ArrayAdapter {
    public MovieAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MovieModel movieModel = (MovieModel) getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_card,parent,false);
        }
        ImageView imageView = convertView.findViewById(R.id.activity_main_movie_image);
        imageView.setImageResource(R.drawable.lighthouse);
        TextView movieTitle = convertView.findViewById(R.id.activity_main_movie_title);
        if (movieModel.getMovieTitle() != null)
        movieTitle.setText(movieModel.getMovieTitle());

        TextView movieVoteAverage = convertView.findViewById(R.id.activity_main_movie_vote_average);
        if (movieModel.getMovieVoteAverage() != null)
        movieVoteAverage.setText(movieModel.getMovieVoteAverage());

        return convertView;
    }
}
