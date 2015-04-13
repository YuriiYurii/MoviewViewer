package yuriitsap.example.com.movieviewer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yuriitsap on 13.04.15.
 */
public class Movie {

    @SerializedName("id")
    private int mId;
    @SerializedName("adult")
    private boolean mAdult;
    @SerializedName("budget")
    private int mBudget;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("imdb_id")
    private String mImdbId;
    @SerializedName("original_title")
    private String mTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("revenue")
    private int mRevenue;

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

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie)) {
            return false;
        }

        Movie movie = (Movie) o;

        if (mAdult != movie.mAdult) {
            return false;
        }
        if (mBudget != movie.mBudget) {
            return false;
        }
        if (mId != movie.mId) {
            return false;
        }
        if (mRevenue != movie.mRevenue) {
            return false;
        }
        if (mBackdropPath != null ? !mBackdropPath.equals(movie.mBackdropPath)
                : movie.mBackdropPath != null) {
            return false;
        }
        if (mImdbId != null ? !mImdbId.equals(movie.mImdbId) : movie.mImdbId != null) {
            return false;
        }
        if (mOverview != null ? !mOverview.equals(movie.mOverview) : movie.mOverview != null) {
            return false;
        }
        if (mTitle != null ? !mTitle.equals(movie.mTitle) : movie.mTitle != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mAdult ? 1 : 0);
        result = 31 * result + mBudget;
        result = 31 * result + (mBackdropPath != null ? mBackdropPath.hashCode() : 0);
        result = 31 * result + (mImdbId != null ? mImdbId.hashCode() : 0);
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mOverview != null ? mOverview.hashCode() : 0);
        result = 31 * result + mRevenue;
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mId=" + mId +
                ", mAdult=" + mAdult +
                ", mBudget=" + mBudget +
                ", mBackdropPath='" + mBackdropPath + '\'' +
                ", mImdbId='" + mImdbId + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mOverview='" + mOverview + '\'' +
                ", mRevenue=" + mRevenue +
                '}';
    }
}
