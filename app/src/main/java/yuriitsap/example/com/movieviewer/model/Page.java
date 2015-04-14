package yuriitsap.example.com.movieviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriitsap on 13.04.15.
 */
public class Page {

    public static int count = 0;
    @SerializedName("page")
    private int mNumber;
    @SerializedName("results")
    private ArrayList<Movie> mMovies;
    @SerializedName("total_pages")
    private int mTotalPages;
    @SerializedName("total_results")
    private int mTotalResults;

    public Page() {
        count++;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Page.count = count;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public ArrayList<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }
}
