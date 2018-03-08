package com.e.k.m.a.elmomovieapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.e.k.m.a.elmomovieapp.checkinternet.ConnectivityReceiver;
import com.e.k.m.a.elmomovieapp.checkinternet.MyApplication;
import com.e.k.m.a.elmomovieapp.movie.MovieAdapter;
import com.e.k.m.a.elmomovieapp.models.MovieModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

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
        movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getBaseContext(),DetailActivity.class));
            }
        });
    }




    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.activity_main_container), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

}

