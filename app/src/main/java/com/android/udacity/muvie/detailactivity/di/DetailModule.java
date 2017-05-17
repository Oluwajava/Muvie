package com.android.udacity.muvie.detailactivity.di;

import com.android.udacity.muvie.detailactivity.DetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mayokun on 5/16/2017.
 */
@Module
public class DetailModule {

    private final DetailContract.View view;

    public  DetailModule(DetailContract.View view) {
        this.view = view;
    }

    @Provides
    DetailContract.View provideView() {
        return view;
    }
}
