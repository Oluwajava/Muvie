package com.android.udacity.muvie.mainactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.udacity.muvie.R;
import com.android.udacity.muvie.adapter.MoviesAdapter;
import com.android.udacity.muvie.mainactivity.di.DaggerMainActivityComponent;
import com.android.udacity.muvie.mainactivity.di.MainActivityModule;
import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.base.BaseModule;
import com.android.udacity.muvie.utils.models.MoviesResults;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.movie_recycler_view)
    RecyclerView movieRecyclerView;

    @BindView(R.id.error_message)
    TextView errorMessageTextView;

    @Inject
    MainActivityPresenter presenter;

    private MoviesAdapter moviesAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        List<MoviesResults> movies = presenter.getMovieList();
        ArrayList<MoviesResults> list = null;
        
        if (movies != null) {
            list = new ArrayList<>(movies);
        }
        outState.putParcelableArrayList(Constants.Keys.MOVIES_STATE, list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        DaggerMainActivityComponent.builder()
                .baseModule(new BaseModule(this))
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);


        gridLayoutManager = new GridLayoutManager(this, numberOfColumns());
        movieRecyclerView.setLayoutManager(gridLayoutManager);

        if (savedInstanceState != null) {
            if (savedInstanceState.getParcelableArrayList(Constants.Keys.MOVIES_STATE) != null) {
                presenter.onFinished(savedInstanceState.<MoviesResults>getParcelableArrayList(Constants.Keys.MOVIES_STATE));
            }
        } else {
            presenter.makeServerCall(Constants.Keys.POPULAR);
        }
    }

    @Override
    public void setPresenter(MainActivityContract.Presenter presenter) {
//        presenter.start();
    }

    @Override
    public void populateMovieRecyclerViewWithData(List<MoviesResults> movieList) {
        moviesAdapter = new MoviesAdapter(movieList);
        movieRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(int stringId) {
        movieRecyclerView.setVisibility(View.INVISIBLE);
        errorMessageTextView.setText(stringId);
        errorMessageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        movieRecyclerView.setVisibility(View.VISIBLE);
        errorMessageTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gotoDetailActivity(MoviesResults movie) {

    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_popularity:
                presenter.makeServerCall(Constants.Keys.POPULAR);
                break;
            case R.id.sort_by_rating:
                presenter.makeServerCall(Constants.Keys.TOP_RATED);
                break;
            case R.id.sort_by_favourite:
                presenter.makeLocalDbCall();
                break;
        }
        return true;
    }
}
