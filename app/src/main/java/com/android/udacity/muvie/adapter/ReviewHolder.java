package com.android.udacity.muvie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.udacity.muvie.R;
import com.android.udacity.muvie.utils.models.ReviewsResults;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayokun on 5/16/2017.
 */

public class ReviewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.context_text_view)
    TextView contextTextView;

    @BindView(R.id.author_text_view)
    TextView authorTextView;

    ReviewsResults reviewsResults;

    public ReviewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);


    }

    public void bindView(ReviewsResults reviewsResults) {
        this.reviewsResults = reviewsResults;
        contextTextView.setText(reviewsResults.getContent());
        authorTextView.setText(reviewsResults.getAuthor());
    }
}
