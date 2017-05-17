package com.android.udacity.muvie.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Reviews implements Parcelable {
    public static final Creator<Reviews> CREATOR = new Creator<Reviews>() {
        @Override
        public Reviews createFromParcel(Parcel source) {
            Reviews var = new Reviews();
            var.id = source.readInt();
            var.page = source.readInt();
            var.total_pages = source.readInt();
            var.results = source.createTypedArray(ReviewsResults.CREATOR);
            var.total_results = source.readInt();
            return var;
        }

        @Override
        public Reviews[] newArray(int size) {
            return new Reviews[size];
        }
    };
    private int id;
    private int page;
    private int total_pages;
    private ReviewsResults[] results;
    private int total_results;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public ReviewsResults[] getResults() {
        return this.results;
    }

    public void setResults(ReviewsResults[] results) {
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
        dest.writeInt(this.id);
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
