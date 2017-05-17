package com.android.udacity.muvie.detailactivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.udacity.muvie.R;
import com.android.udacity.muvie.adapter.ReviewAdapter;
import com.android.udacity.muvie.adapter.TrailerAdapter;
import com.android.udacity.muvie.detailactivity.di.DaggerDetailComponent;
import com.android.udacity.muvie.detailactivity.di.DetailModule;
import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.base.BaseModule;
import com.android.udacity.muvie.utils.models.MoviesResults;
import com.android.udacity.muvie.utils.models.ReviewsResults;
import com.android.udacity.muvie.utils.models.VideosResults;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mayokun on 5/15/2017.
 */

public class DetailActivity extends AppCompatActivity implements DetailContract.View{

    @Inject DetailPresenter presenter;

    @BindView(R.id.thumbnail)
    ImageView movieThumbnail;

    @BindView(R.id.tv_title)
    TextView nameTextView;

    @BindView(R.id.tv_realease_date)
    TextView releaseDateTextView;

    @BindView(R.id.tv_rating)
    TextView ratingTextView;

    @BindView(R.id.tv_synopsis)
    TextView synopsisTextView;

    @BindView(R.id.trailer_recycler_view)
    RecyclerView trailerRecyclerView;

    @BindView(R.id.review_recycler_view)
    RecyclerView reviewRecyclerView;

    @BindView(R.id.favourite_button)
    Button favouriteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        DaggerDetailComponent.builder()
                .detailModule(new DetailModule(this))
                .baseModule(new BaseModule(this))
                .build()
                .inject(this);

        Bundle bundle = getIntent().getExtras();
        MoviesResults movie = bundle.getParcelable(Constants.Keys.MOVIES_STATE);
        presenter.populateViewWithData(movie);
        presenter.loadReview(movie.getId());
        presenter.loadTrailer(movie.getId());
        presenter.start();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {

    }

    @Override
    public void setImageThumbnail(String url) {
        Glide.with(this).load(url)
                .into(movieThumbnail);
    }

    @Override
    public void setName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        releaseDateTextView.setText(releaseDate);
    }

    @Override
    public void setRating(String rating) {
        ratingTextView.setText(rating);
    }

    @Override
    public void setSynopsis(String synopsis) {
        synopsisTextView.setText(synopsis);
    }

    @Override
    public void setTrailer(List<VideosResults> videosResultsList) {
        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TrailerAdapter trailerAdapter = new TrailerAdapter(videosResultsList);
        trailerRecyclerView.setAdapter(trailerAdapter);
    }

    @Override
    public void setReview(List<ReviewsResults> reviewsResultsList) {
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ReviewAdapter reviewAdapter = new ReviewAdapter(reviewsResultsList);
        reviewRecyclerView.setAdapter(reviewAdapter);
    }

    @Override
    public void setFavouriteButtonText(int id) {
        favouriteButton.setText(getString(id));
    }

    @OnClick(R.id.favourite_button)
    public void addToFavourite() {
        if(favouriteButton.getText().equals(getString(R.string.mark_as_favourite))) {
            presenter.insertFavouriteIntoDb();
            favouriteButton.setText(getString(R.string.already_marked_as_favourite));
        } else {
            presenter.removeMovieFromDb();
            favouriteButton.setText(getString(R.string.mark_as_favourite));
        }
    }
}
