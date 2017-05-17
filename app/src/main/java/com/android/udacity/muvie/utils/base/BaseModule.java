package com.android.udacity.muvie.utils.base;

import android.content.Context;
import android.provider.SyncStateContract;

import com.android.udacity.muvie.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mayokun on 5/15/2017.
 */
@Module
public class BaseModule {

    private final Context context;

    public BaseModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.Endpoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
