package yuriitsap.example.com.movieviewer.utils;

import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import yuriitsap.example.com.movieviewer.R;
import yuriitsap.example.com.movieviewer.fragments.MovieListFragment;
import yuriitsap.example.com.movieviewer.model.Movie;
import yuriitsap.example.com.movieviewer.model.Page;

/**
 * Created by yuriitsap on 13.04.15.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieItemHolder> {

    private static final String BASE_URL = "http://image.tmdb.org/t/p/w92";
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private MovieListFragment.OnMovieSelectedListener mOnMovieSelectedListener;
    private int mSelectedItemPosition = -1;
    public static int count = 0;
    private boolean mIsLoading;

    public MovieAdapter() {
        Log.e("TAG", "count = " + count++);
        loadData(1);

    }

    public void loadData() {
        mIsLoading = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mIsLoading = false;

            }
        }).start();
    }

    public void loadData(int page) {
        mIsLoading = true;
        MovieClient.getMovieService().getPopularMovies(new Callback<Page>() {
            @Override
            public void success(Page page, Response response) {
                mMovies.addAll(page.getMovies());
                Log.e("TAG", "size = " + mMovies.size());
                notifyDataSetChanged();
                mIsLoading = false;

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Override
    public MovieItemHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new MovieItemHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.movie_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MovieItemHolder movieItemHolder, int position) {
        Picasso.with(movieItemHolder.mMoviePreviewImage.getContext())
                .load(BASE_URL + mMovies.get(position).getPosterPath())
                .into(movieItemHolder.mMoviePreviewImage);
        movieItemHolder.mMovieTitleTextView.setText(mMovies.get(position).getTitle());
        movieItemHolder.mMovieRating.setText("Movie rate : " + mMovies.get(position).getRating());
        movieItemHolder.mRow.setActivated(position == mSelectedItemPosition);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieItemHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private ImageView mMoviePreviewImage;
        private LinearLayout mRow;
        private TextView mMovieTitleTextView;
        private TextView mMovieRating;

        public MovieItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mMoviePreviewImage = (ImageView) itemView.findViewById(R.id.movie_picture_preview);
            mRow = (LinearLayout) itemView.findViewById(R.id.movie_info_row);
            mMovieTitleTextView = (TextView) itemView.findViewById(R.id.movie_title);
            mMovieRating = (TextView) itemView.findViewById(R.id.movie_rating);

        }

        @Override
        public void onClick(View v) {
            mOnMovieSelectedListener.onMovieSelected(mMovies.get(getPosition()).getId());
            int previousPosition = mSelectedItemPosition;
            mSelectedItemPosition = getPosition();
            notifyItemChanged(getPosition());
            notifyItemChanged(previousPosition);
        }
    }

    public ArrayList<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    public MovieListFragment.OnMovieSelectedListener getOnMovieSelectedListener() {
        return mOnMovieSelectedListener;
    }

    public void setOnMovieSelectedListener(
            MovieListFragment.OnMovieSelectedListener onMovieSelectedListener) {
        mOnMovieSelectedListener = onMovieSelectedListener;
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public void setLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }
}
