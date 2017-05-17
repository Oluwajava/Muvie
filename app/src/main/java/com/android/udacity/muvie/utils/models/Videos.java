package com.android.udacity.muvie.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Videos implements Parcelable {
    public static final Creator<Videos> CREATOR = new Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel source) {
            Videos var = new Videos();
            var.id = source.readInt();
            var.results = source.createTypedArray(VideosResults.CREATOR);
            return var;
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };
    private int id;
    private VideosResults[] results;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VideosResults[] getResults() {
        return this.results;
    }

    public void setResults(VideosResults[] results) {
        this.results = results;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeTypedArray(this.results, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
