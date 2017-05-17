package com.android.udacity.muvie.detailactivity.di;

import android.content.Context;

import com.android.udacity.muvie.detailactivity.DetailActivity;
import com.android.udacity.muvie.utils.base.BaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mayokun on 5/15/2017.
 */
@Singleton
@Component(modules = {BaseModule.class, DetailModule.class})
public interface DetailComponent {

    void inject(DetailActivity detailActivity);
}
