package com.android.udacity.muvie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.udacity.muvie.R;
import com.android.udacity.muvie.utils.Constants;
import com.android.udacity.muvie.utils.models.MoviesResults;
import com.bumptech.glide.Glide;


import java.util.List;

/**
 * Created by Mayokun on 4/10/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieHolder>{

    private List<MoviesResults> movieList;

    public MoviesAdapter(List<MoviesResults> movieList) {
        this.movieList = movieList;
    }


    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.bindView(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


}
