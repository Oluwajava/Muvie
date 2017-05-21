package com.android.udacity.muvie.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.udacity.muvie.data.source.local.MovieContract;
import com.android.udacity.muvie.data.source.remote.MoviesService;
import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.models.Movies;
import com.android.udacity.muvie.utils.models.MoviesResults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mayokun on 5/15/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private Retrofit retrofit;
    private Context context;
    private List<MoviesResults> movieList;

    @Inject
    public MainActivityPresenter(Context context, MainActivityContract.View view, Retrofit retrofit) {
        this.context = context;
        this.view = view;
        this.retrofit = retrofit;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void start() {
        Log.d(MainActivityPresenter.class.getSimpleName(), "Testing");
    }

    @Override
    public void makeServerCall(String queryType) {
        if (isNetworkAvailable()) {
            MoviesService moviesService = retrofit.create(MoviesService.class);
            Call<Movies> moviesCall = null;

            switch (queryType) {
                case Constants.Keys.POPULAR:
                    moviesCall = moviesService.getPopularMovies(Constants.Keys.MY_API_KEY);
                    retrieveDataFromServer(moviesCall);
                    break;
                case Constants.Keys.TOP_RATED:
                    moviesCall = moviesService.getTopRated(Constants.Keys.MY_API_KEY);
                    retrieveDataFromServer(moviesCall);
                    break;
            }
        }
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void makeLocalDbCall() {

        Cursor data = context.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                MovieContract.MovieEntry._ID);

        int idIndex = data.getColumnIndex(MovieContract.MovieEntry._ID);
        int movieIdIndex = data.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_ID);
        int movieTitleIndex = data.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE);
        int imageUrlIndex = data.getColumnIndex(MovieContract.MovieEntry.COLUMN_IMAGE_URL);
        int releaseDateIndex = data.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE);
        int synopsisIndex = data.getColumnIndex(MovieContract.MovieEntry.COLUMN_SYNOPSIS);
        int ratingIndex = data.getColumnIndex(MovieContract.MovieEntry.COLUMN_RATING);

        List<MoviesResults> moviesList = new ArrayList<>();
        while (data.moveToNext()) {
            MoviesResults movie = new MoviesResults();
            movie.setId(data.getInt(movieIdIndex));
            movie.setOriginal_title(data.getString(movieTitleIndex));
            movie.setPoster_path(data.getString(imageUrlIndex));
            movie.setRelease_date(data.getString(releaseDateIndex));
            movie.setOverview(data.getString(synopsisIndex));
            movie.setVote_average(Double.parseDouble(data.getString(ratingIndex)));
            moviesList.add(movie);

        }


        onFinished(moviesList);


    }

    public void onFinished(List<MoviesResults> movieList) {
        view.hideProgressBar();
        view.populateMovieRecyclerViewWithData(movieList);
    }

    private void retrieveDataFromServer(Call<Movies> moviesCall) {
        moviesCall.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                movieList = Arrays.asList(response.body().getResults());
                onFinished(movieList);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

    }

    public List<MoviesResults> getMovieList() {
        return movieList;
    }
}
