package com.android.udacity.muvie.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            Movies var = new Movies();
            var.page = source.readInt();
            var.total_pages = source.readInt();
            var.results = source.createTypedArray(MoviesResults.CREATOR);
            var.total_results = source.readInt();
            return var;
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
    private int page;
    private int total_pages;
    private MoviesResults[] results;
    private int total_results;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return this.total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public MoviesResults[] getResults() {
        return this.results;
    }

    public void setResults(MoviesResults[] results) {
        this.results = results;
    }

    public int getTotal_results() {
        return this.total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.total_pages);
        dest.writeTypedArray(this.results, flags);
        dest.writeInt(this.total_results);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
