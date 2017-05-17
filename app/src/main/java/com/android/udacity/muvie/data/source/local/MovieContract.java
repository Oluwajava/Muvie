package com.android.udacity.muvie.data.source.local;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Mayokun on 5/16/2017.
 */

public class MovieContract{

    public static final String AUTHORITY = "com.android.udacity.muvie";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);

    public static final String PATH_MOVIES = "movies";

    public static final class MovieEntry  implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_MOVIE_ID = "movie_id";
    }
}
