package yuriitsap.example.com.movieviewer.model;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yuriitsap on 13.04.15.
 */
public class Movie implements Parcelable {

    @SerializedName("id")
    private int mId;

    @SerializedName("adult")
    private boolean mAdult;

    @SerializedName("budget")
    private int mBudget;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("imdb_id")
    private String mImdbId;

    @SerializedName("original_title")
    private String mTitle;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("revenue")
    private int mRevenue;

    @SerializedName("vote_average")
    private float mRating;

    public Movie(Parcel parcel) {
        mId = parcel.readInt();
        mAdult = parcel.readByte() != 0;
        mBudget = parcel.readInt();
        mPosterPath = parcel.readString();
        mImdbId = parcel.readString();
        mTitle = parcel.readString();
        mOverview = parcel.readString();
        mRevenue = parcel.readInt();
        mRating = parcel.readFloat();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public void setAdult(boolean adult) {
        mAdult = adult;
    }

    public int getBudget() {
        return mBudget;
    }

    public void setBudget(int budget) {
        mBudget = budget;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public int getRevenue() {
        return mRevenue;
    }

    public void setRevenue(int revenue) {
        mRevenue = revenue;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        mRating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeByte((byte) (mAdult ? 1 : 0));
        dest.writeInt(mBudget);
        dest.writeString(mPosterPath);
        dest.writeString(mImdbId);
        dest.writeString(mTitle);
        dest.writeString(mOverview);
        dest.writeInt(mRevenue);
        dest.writeFloat(mRating);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
