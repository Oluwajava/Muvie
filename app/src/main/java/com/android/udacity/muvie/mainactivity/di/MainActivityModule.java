package com.android.udacity.muvie.mainactivity.di;

import android.content.Context;

import com.android.udacity.muvie.mainactivity.MainActivityContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mayokun on 5/15/2017.
 */
@Module
public class MainActivityModule {

    private final MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    MainActivityContract.View providesView() {
        return view;
    }
}
