package com.android.udacity.muvie.utils;

/**
 * Created by Mayokun on 5/15/2017.
 */

public interface Constants {

    interface Endpoints {
        String BASE_URL = "http://api.themoviedb.org/3/movie/";
        String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";
        String YOUTUBE = "https://www.youtube.com/video/";
    }

    interface Keys {
        String API_KEY = "api_key";
        String MY_API_KEY = "MY_API_KEY";

        String RESULTS = "results";
        String POSTER_PATH = "poster_path";
        String ADULT = "adult";
        String OVERVIEW = "overview";
        String RELEASE_DATE = "release_date";
        String ID = "id";
        String ORIGINAL_TITLE = "original_title";
        String ORIGINAL_LANGUAGE = "original_language";
        String BACKDROP_PATH = "backdrop_path";
        String POPULARITY = "popularity";
        String VOTE_COUNT = "vote_count";
        String VIDEO = "video";
        String VOTE_AVERAGE = "vote_average";
        String MOVIES_STATE = "movie_state";
        String POPULAR = "popular";
        String TOP_RATED= "top_rated";
        String FAVOURITE = "favourite";
    }
}
