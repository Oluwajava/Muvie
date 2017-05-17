package com.android.udacity.muvie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.udacity.muvie.R;
import com.android.udacity.muvie.utils.models.ReviewsResults;

import java.util.List;

/**
 * Created by Mayokun on 5/16/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {

    List<ReviewsResults> reviewsResultsList;
    public ReviewAdapter(List<ReviewsResults> reviewsResultsList) {
        this.reviewsResultsList = reviewsResultsList;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.bindView(reviewsResultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewsResultsList.size();
    }
}
