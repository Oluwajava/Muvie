package com.android.udacity.muvie.mainactivity;

import com.android.udacity.muvie.utils.base.BasePresenter;
import com.android.udacity.muvie.utils.base.BaseView;
import com.android.udacity.muvie.utils.models.MoviesResults;

import java.util.List;

/**
 * Created by Mayokun on 5/15/2017.
 */

public interface MainActivityContract {

    interface Presenter extends BasePresenter {
        void makeServerCall(String queryType);
        boolean isNetworkAvailable();
        void makeLocalDbCall();
    }

    interface View extends BaseView<Presenter> {
        void populateMovieRecyclerViewWithData(List<MoviesResults> movieList);

        void showProgressBar();

        void hideProgressBar();

        void showErrorMessage(int stringId);

        void hideErrorMessage();

        void gotoDetailActivity(MoviesResults movie);
    }
}
