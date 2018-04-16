package com.e.k.m.a.elmomovieapp.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.e.k.m.a.elmomovieapp.database.MovieDatabaseHelper;
import com.e.k.m.a.elmomovieapp.models.MovieModel;

/**
 * Created by ahmedelmoselhy on 4/16/2018.
 */

public class MovieProvider extends ContentProvider{
    static final String PROVIDER_NAME = "com.e.k.m.a.elmomovieapp";
    static final String URL = "content://"+PROVIDER_NAME+"/movies";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String ID = "id";
    static final String TITLE = "title";
    static final int MOVIES = 1;
    static final int MOVIE_ID = 2;
    static final UriMatcher uriMatcher;
    MovieDatabaseHelper databaseHelper;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "students", MOVIES);
        uriMatcher.addURI(PROVIDER_NAME, "students/#", MOVIE_ID);
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseHelper = new MovieDatabaseHelper(context);
        return (databaseHelper == null)? false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        return databaseHelper.queryMoiveData();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case MOVIES:
                return "vnd.android.cursor.dir/vnd.example.movies";
            case MOVIE_ID:
                return "vnd.android.cursor.item/vnd.example.movies";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
