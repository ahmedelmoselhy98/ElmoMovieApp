package com.e.k.m.a.elmomovieapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.e.k.m.a.elmomovieapp.json.JsonUtils;
import com.e.k.m.a.elmomovieapp.movie.MovieAdapter;
import com.e.k.m.a.elmomovieapp.models.MovieModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView movieGridView;
    MovieAdapter movieAdapter;
    TextView emptyStateTextView;
    ProgressBar movieProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieGridView = findViewById(R.id.Movie_gridview);
        emptyStateTextView = findViewById(R.id.empty_state_textview);
        movieGridView.setEmptyView(emptyStateTextView);
        movieAdapter = new MovieAdapter(this.getBaseContext(),new ArrayList<MovieModel>());
        movieGridView.setAdapter(movieAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (CheckInternetConnection.isConnected(this))
        new MovieAsyncTask().execute(BuildUrl.POPULAR_MOVIE_URL);
        else
            emptyStateTextView.setText(R.string.no_internet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (CheckInternetConnection.isConnected(this))
            if (item.getItemId() == R.id.popular_menu)
                new MovieAsyncTask().execute(BuildUrl.POPULAR_MOVIE_URL);
            if (item.getItemId() == R.id.top_rated_menu)
                 new MovieAsyncTask().execute(BuildUrl.TOP_RATED_MOVIE_URL);
        else
            emptyStateTextView.setText(R.string.no_internet);


        return true;
    }

    private class MovieAsyncTask extends AsyncTask<String,Void,ArrayList<MovieModel>> {
        @Override
        protected ArrayList<MovieModel> doInBackground(String... strings) {
            if (strings.length < 1 || strings[0] == null)
            {return null;}

            JsonUtils movieUtils = new JsonUtils();
            ArrayList<MovieModel> movieModels = movieUtils.helper(strings[0]);
            return movieModels;
        }
        @Override
        protected void onPostExecute(ArrayList<MovieModel> movies) {
            super.onPostExecute(movies);
            movieProgressBar = findViewById(R.id.moive_progressbar);
            movieProgressBar.setVisibility(View.GONE);
            movieAdapter.clear();
            if (movies != null && !movies.isEmpty()) {
                movieAdapter.addAll(movies);
            }else
                emptyStateTextView.setText(R.string.no_movies);

        }
    }
}

