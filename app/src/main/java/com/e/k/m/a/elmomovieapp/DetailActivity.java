package com.e.k.m.a.elmomovieapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.e.k.m.a.elmomovieapp.adapters.ReviewAdapter;
import com.e.k.m.a.elmomovieapp.adapters.TrailerAdapter;
import com.e.k.m.a.elmomovieapp.database.MovieDatabaseHelper;
import com.e.k.m.a.elmomovieapp.json.JsonUtils;
import com.e.k.m.a.elmomovieapp.models.MovieModel;
import com.e.k.m.a.elmomovieapp.models.ReviewModel;
import com.e.k.m.a.elmomovieapp.models.TrailerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class DetailActivity extends AppCompatActivity {
    ImageView moviePosterImageView;
    TextView movieVoteAverageTextView,movieReleaseDateTextView,movieOverViewTextView;
    MovieModel movieDetail;
    RecyclerView trailersRecyclerView,reviewsRecyclerView;
    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;
    MovieDatabaseHelper databaseHelper;
    ToggleButton favoriteToggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        movieDetail = (MovieModel) intent.getExtras().getSerializable("movieKey");
        databaseHelper = new MovieDatabaseHelper(this);
        initUI();
        polishUi();
        addMovieToFavorites();
        checkIfMovieFavorite();
    }
    private void initUI(){
    moviePosterImageView = findViewById(R.id.activity_detail_movie_image);
    movieVoteAverageTextView = findViewById(R.id.activity_detail_movie_vote_average);
    movieReleaseDateTextView = findViewById(R.id.activity_detail_movie_release_date);
    movieOverViewTextView = findViewById(R.id.activity_detail_movie_overview);
    favoriteToggleButton = findViewById(R.id.favorite_toggle_button);
    trailersRecyclerView = findViewById(R.id.trailer_recycler_view);
    LinearLayoutManager trailersLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
    trailersRecyclerView.setLayoutManager(trailersLinearLayoutManager);
    reviewsRecyclerView = findViewById(R.id.reviews_recycler_view);
    LinearLayoutManager reviewsLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    reviewsRecyclerView.setLayoutManager(reviewsLinearLayoutManager);
}
    private void polishUi(){
        if (movieDetail.getMoviePostarPath()!=null)
            Picasso.with(this).load(BuildUrl.IMAGE_BASE_URL+movieDetail.getMoviePostarPath()).into(moviePosterImageView);
        if (movieDetail.getMovieTitle()!=null)
            setTitle(movieDetail.getMovieTitle());
        if (movieDetail.getMovieVoteAverage()!=null)
            movieVoteAverageTextView.setText(movieDetail.getMovieVoteAverage());
        if (movieDetail.getMovieReleaseDate()!=null)
            movieReleaseDateTextView.setText(movieDetail.getMovieReleaseDate());
        if (movieDetail.getMovieOverview()!=null)
            movieOverViewTextView.setText(movieDetail.getMovieOverview());
    }
    public void addMovieToFavorites(){
        favoriteToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!favoriteToggleButton.isChecked()){
                    // here add movie to database
                    if (databaseHelper.insertFavorite(movieDetail)){
                        Toast.makeText(DetailActivity.this, "Movie Added Successfully", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(DetailActivity.this, "Movie Added Failed", Toast.LENGTH_SHORT).show();
                }else {
                    // here delete movie from database
                    if (databaseHelper.deleteFavorite(movieDetail.getMovieId())){
                        Toast.makeText(DetailActivity.this, "Movie Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DetailActivity.this, "Movie Deleted Failed", Toast.LENGTH_SHORT).show();
                    }}}
        });
    }
    public void checkIfMovieFavorite(){
        if (databaseHelper.checkFavoritFilm(movieDetail.getMovieId()))
            favoriteToggleButton.setChecked(true);
    }
    @Override
    protected void onStart() {
        super.onStart();
        BuildUrl buildUrl = new BuildUrl(movieDetail.getMovieId());
        if (CheckInternetConnection.isConnected(this)) {
            new FetchTrailerData().execute(buildUrl.getMovieTrailersUlr());
            new FetchReviewData().execute(buildUrl.getMovieReviewsUlr());
        }else
            Toast.makeText(this, R.string.no_internet+" for Trailers and Reviews !!", Toast.LENGTH_SHORT).show();
    }
    private class FetchTrailerData extends AsyncTask<String,Void,ArrayList<TrailerModel>> {
        private final String LOG_TAG = FetchTrailerData.class.getSimpleName();

        public FetchTrailerData() {
        }
        @Override
        protected ArrayList<TrailerModel> doInBackground(String... strings) {
//        Log.e(TAG,"Here Is doInBackground Of MovieAsyncTask");
            if (strings.length < 1 || strings[0] == null)
            {return null;}

            JsonUtils movieUtils = new JsonUtils();
            ArrayList<TrailerModel> trailerModels = movieUtils.getTrailerList(strings[0]);
            return trailerModels;
        }
        @Override
        protected void onPostExecute(ArrayList<TrailerModel> trailers) {
//        Log.e(TAG,"Here Is onPostExecute Of MovieAsyncTask");
            if (trailers != null && !trailers.isEmpty()) {
                trailerAdapter = new TrailerAdapter(DetailActivity.this,trailers);
                trailersRecyclerView.setAdapter(trailerAdapter);
                trailerAdapter.notifyDataSetChanged();
            }


        }
    }
    private class FetchReviewData extends AsyncTask<String,Void,ArrayList<ReviewModel>> {
        private final String LOG_TAG = FetchReviewData.class.getSimpleName();
        public FetchReviewData() {
        }
        @Override
        protected ArrayList<ReviewModel> doInBackground(String... strings) {
//        Log.e(TAG,"Here Is doInBackground Of MovieAsyncTask");
            if (strings.length < 1 || strings[0] == null)
            {return null;}

            JsonUtils movieUtils = new JsonUtils();
            ArrayList<ReviewModel> reviewModels = movieUtils.getReviewList(strings[0]);
            return reviewModels;
        }
        @Override
        protected void onPostExecute(ArrayList<ReviewModel> reviews) {
//        Log.e(TAG,"Here Is onPostExecute Of MovieAsyncTask");
            if (reviews != null && !reviews.isEmpty()) {
                reviewAdapter = new ReviewAdapter(DetailActivity.this,reviews);
                reviewsRecyclerView.setAdapter(reviewAdapter);
                reviewAdapter.notifyDataSetChanged();
            }


        }
    }




}
