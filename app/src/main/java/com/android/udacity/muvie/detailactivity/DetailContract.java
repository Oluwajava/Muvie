package com.android.udacity.muvie.detailactivity;

import com.android.udacity.muvie.mainactivity.MainActivityContract;
import com.android.udacity.muvie.utils.base.BasePresenter;
import com.android.udacity.muvie.utils.base.BaseView;
import com.android.udacity.muvie.utils.models.MoviesResults;
import com.android.udacity.muvie.utils.models.ReviewsResults;
import com.android.udacity.muvie.utils.models.VideosResults;

import java.util.List;

/**
 * Created by Mayokun on 5/15/2017.
 */

public interface DetailContract {

    interface View extends BaseView<Presenter>{
        void setImageThumbnail(String url);
        void setName(String name);
        void setReleaseDate(String releaseDate);
        void setRating(String rating);
        void setSynopsis(String synopsis);
        void setTrailer(List<VideosResults> videosResultsList);
        void setReview(List<ReviewsResults> reviewsResultsList);
        void setFavouriteButtonText(int id);
    }

    interface Presenter extends BasePresenter{
        void populateViewWithData(MoviesResults movie);
        void loadReview(int id);
        void loadTrailer(int id);
        void insertFavouriteIntoDb();
        void removeMovieFromDb();
        boolean isFavourite();
    }
}
