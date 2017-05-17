package com.android.udacity.muvie.data.source.remote;

import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.models.Movies;
import com.android.udacity.muvie.utils.models.MoviesResults;
import com.android.udacity.muvie.utils.models.Reviews;
import com.android.udacity.muvie.utils.models.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mayokun on 5/15/2017.
 */

public interface MoviesService {

    @GET("popular")
    Call<Movies> getPopularMovies(@Query(Constants.Keys.API_KEY)String apiKey);

    @GET("top_rated")
    Call<Movies> getTopRated(@Query(Constants.Keys.API_KEY)String apiKey);

    @GET("{id}/videos")
    Call<Videos> getMovieLink(@Path(Constants.Keys.ID)String id, @Query(Constants.Keys.API_KEY)String apiKey);

    @GET("{id}/reviews")
    Call<Reviews> getMovieReview(@Path(Constants.Keys.ID)String id, @Query(Constants.Keys.API_KEY)String apiKey);
}
