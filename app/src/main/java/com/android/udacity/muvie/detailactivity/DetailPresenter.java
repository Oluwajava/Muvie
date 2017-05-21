package com.android.udacity.muvie.detailactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.android.udacity.muvie.R;
import com.android.udacity.muvie.data.source.local.MovieContract;
import com.android.udacity.muvie.data.source.remote.MoviesService;
import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.models.MoviesResults;
import com.android.udacity.muvie.utils.models.Reviews;
import com.android.udacity.muvie.utils.models.ReviewsResults;
import com.android.udacity.muvie.utils.models.Videos;
import com.android.udacity.muvie.utils.models.VideosResults;

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

public class DetailPresenter implements DetailContract.Presenter {

    private final Context context;
    private final DetailContract.View view;
    private final Retrofit retrofit;
    private List<VideosResults> videosUrl;
    private List<ReviewsResults> reviews;
    private MoviesResults moviesResults;

    @Inject
    public DetailPresenter(Context context, DetailContract.View view, Retrofit retrofit) {
        this.view = view;
        this.retrofit = retrofit;
        this.context = context;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void start() {
        if (isFavourite()) {
            view.setFavouriteButtonText(R.string.already_marked_as_favourite);
        } else {
            view.setFavouriteButtonText(R.string.mark_as_favourite);
        }
    }

    @Override
    public void populateViewWithData(MoviesResults movie) {
        this.moviesResults = movie;
        view.setImageThumbnail(Constants.Endpoints.IMAGE_BASE_URL + movie.getPoster_path());
        view.setName(movie.getOriginal_title());
        view.setRating("" + movie.getVote_average());
        view.setReleaseDate("" + movie.getRelease_date());
        view.setSynopsis(movie.getOverview());
    }

    @Override
    public void loadReview(int id) {
        MoviesService moviesService = retrofit.create(MoviesService.class);
        Call<Reviews> makeReviewCall = moviesService.getMovieReview("" + id, Constants.Keys.MY_API_KEY);
        makeReviewCall.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                reviews = Arrays.asList(response.body().getResults());
                view.setReview(reviews);
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }
        });
    }

    @Override

    public void loadTrailer(int id) {
        MoviesService moviesService = retrofit.create(MoviesService.class);
        Call<Videos> makeVideoCall = moviesService.getMovieLink("" + id, Constants.Keys.MY_API_KEY);
        makeVideoCall.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                videosUrl = Arrays.asList(response.body().getResults());
                view.setTrailer(videosUrl);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });
    }

    @Override
    public void insertFavouriteIntoDb() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, moviesResults.getId());
        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, moviesResults.getTitle());
        contentValues.put(MovieContract.MovieEntry.COLUMN_IMAGE_URL, moviesResults.getPoster_path());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, moviesResults.getRelease_date());
        contentValues.put(MovieContract.MovieEntry.COLUMN_SYNOPSIS, moviesResults.getOverview());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RATING, moviesResults.getVote_average());
        Uri uri = context.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);

    }

    @Override
    public void removeMovieFromDb() {
        Uri uriToDelete = MovieContract.MovieEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(moviesResults.getId())).build();
        int tasksDeleted = context.getContentResolver().delete(uriToDelete, null, null);

    }

    @Override
    public boolean isFavourite() {

        Uri singleMovieUri = MovieContract.MovieEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(moviesResults.getId())).build();
        Cursor cursor = context.getContentResolver().query(singleMovieUri,
                null,
                null,
                null,
                null);

        return cursor.getCount() > 0;
    }
}
