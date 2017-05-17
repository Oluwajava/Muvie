package com.android.udacity.muvie.mainactivity.di;

import android.content.Context;

import com.android.udacity.muvie.mainactivity.MainActivity;
import com.android.udacity.muvie.utils.base.BaseModule;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mayokun on 5/15/2017.
 */
@Singleton
@Component(modules = {BaseModule.class, MainActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    Context provideContext();

}
