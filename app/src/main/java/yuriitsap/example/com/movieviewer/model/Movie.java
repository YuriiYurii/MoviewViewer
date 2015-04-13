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
    private int mRating;

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

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        mRating = rating;
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
        if (mRating != movie.mRating) {
            return false;
        }
        if (mRevenue != movie.mRevenue) {
            return false;
        }
        if (mImdbId != null ? !mImdbId.equals(movie.mImdbId) : movie.mImdbId != null) {
            return false;
        }
        if (mOverview != null ? !mOverview.equals(movie.mOverview) : movie.mOverview != null) {
            return false;
        }
        if (mPosterPath != null ? !mPosterPath.equals(movie.mPosterPath)
                : movie.mPosterPath != null) {
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
        result = 31 * result + (mPosterPath != null ? mPosterPath.hashCode() : 0);
        result = 31 * result + (mImdbId != null ? mImdbId.hashCode() : 0);
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mOverview != null ? mOverview.hashCode() : 0);
        result = 31 * result + mRevenue;
        result = 31 * result + mRating;
        return result;
    }
}
