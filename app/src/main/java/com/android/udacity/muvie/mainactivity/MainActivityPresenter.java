package com.android.udacity.muvie.mainactivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.models.Movies;
import com.android.udacity.muvie.utils.models.MoviesResults;
import com.android.udacity.muvie.data.source.remote.MoviesService;

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
