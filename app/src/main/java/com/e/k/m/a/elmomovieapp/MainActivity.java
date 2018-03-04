package com.e.k.m.a.elmomovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.e.k.m.a.elmomovieapp.movie.MovieAdapter;
import com.e.k.m.a.elmomovieapp.movie.MovieModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView movieGridView;
    MovieAdapter movieAdapter;
    ArrayList<MovieModel> movieModelArrayList = new ArrayList<MovieModel>();
    String [] moviesTitles = {"ahmed","mohamed","kamal","hussien","elmoselhy"
            ,"ahmed","mohamed","kamal","hussien","elmoselhy"
            ,"ahmed","mohamed","kamal","hussien","elmoselhy"
            ,"ahmed","mohamed","kamal","hussien","elmoselhy"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilizeUi();
        makeMovieData();
    }
    public void initilizeUi(){
        movieGridView = findViewById(R.id.Movie_gridview);
        movieAdapter = new MovieAdapter(this.getBaseContext(),movieModelArrayList);
    }

    // this method create a list of Movie Data
    public void makeMovieData(){
        MovieModel movieModel;
        for (int i =0;i < 10;i++){
            movieModel = new MovieModel();
            movieModel.setMovieTitle("The Prophet Mohamed");
            movieModel.setMovieVoteAverage("7.0");
            movieModelArrayList.add(i,movieModel);
        }
        movieGridView.setAdapter(movieAdapter);
    }
}
