package com.android.udacity.muvie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.models.VideosResults;

import java.util.List;

/**
 * Created by Mayokun on 5/16/2017.
 */

public class TrailerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    VideosResults videosResults;
    Context context;

    public TrailerHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindView(VideosResults videosResults) {
        this.videosResults = videosResults;
    }

    @Override
    public void onClick(View view) {
        Intent trailerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.Endpoints.YOUTUBE+videosResults.getKey()));
        context.startActivity(trailerIntent);
    }
}
