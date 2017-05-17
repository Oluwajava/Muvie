package com.android.udacity.muvie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.udacity.muvie.R;
import com.android.udacity.muvie.detailactivity.DetailActivity;
import com.android.udacity.muvie.mainactivity.MainActivity;
import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.models.MoviesResults;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayokun on 5/15/2017.
 */

public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.movie_thumbnail)
    ImageView movieThumbnail;

    MoviesResults moviesResults;
    Context context;

    public MovieHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = itemView.getContext();

        movieThumbnail.setOnClickListener(this);
    }

    public void bindView(MoviesResults moviesResults) {
        this.moviesResults = moviesResults;
        Glide.with(context).load(Constants.Endpoints.IMAGE_BASE_URL+moviesResults.getPoster_path())
                .placeholder(android.R.drawable.picture_frame)
                .error(android.R.drawable.picture_frame)
                .into(movieThumbnail);
    }

    @Override
    public void onClick(View view) {
        Intent detailActivityIntent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.Keys.MOVIES_STATE, moviesResults);
        detailActivityIntent.putExtras(bundle);
        context.startActivity(detailActivityIntent);
    }
}
