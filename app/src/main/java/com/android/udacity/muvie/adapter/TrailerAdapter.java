package com.android.udacity.muvie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.udacity.muvie.R;
import com.android.udacity.muvie.utils.models.VideosResults;

import java.util.List;

/**
 * Created by Mayokun on 5/16/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerHolder> {

    List<VideosResults> videosResultsList;

    public TrailerAdapter(List<VideosResults> videosResultsList) {
        this.videosResultsList = videosResultsList;
    }

    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailer_item, parent, false);

        return new TrailerHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerHolder holder, int position) {
        holder.bindView(videosResultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return videosResultsList.size();
    }
}
