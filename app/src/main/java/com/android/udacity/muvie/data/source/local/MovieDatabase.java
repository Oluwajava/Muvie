package com.android.udacity.muvie.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.udacity.muvie.data.source.local.MovieContract.*;

import static com.android.udacity.muvie.data.source.local.MovieContract.MovieEntry.TABLE_NAME;

/**
 * Created by Mayokun on 5/16/2017.
 */

public class MovieDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movie.db";

    public static final int DATABASE_VERSION = 1;


    public MovieDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE =
                "CREATE TABLE "+ MovieEntry.TABLE_NAME +
                        " ("+
                            MovieEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                            MovieEntry.COLUMN_MOVIE_ID+ " INTEGER, "+
                            MovieEntry.COLUMN_TITLE+ " TEXT);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
