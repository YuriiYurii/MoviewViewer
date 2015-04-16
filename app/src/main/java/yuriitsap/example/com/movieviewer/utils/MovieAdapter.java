package yuriitsap.example.com.movieviewer.utils;

import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_ROW = 0;
    public static final int VIEW_TYPE_SPINNER = 1;
    private static final String BASE_URL = "http://image.tmdb.org/t/p/w92";

    private ArrayList<Movie> mMovies = new ArrayList<>();
    private MovieListFragment.OnMovieSelectedListener mOnMovieSelectedListener;
    private int mSelectedItemPosition = -1;
    private int mPagesLoaded = 0;


    public MovieAdapter(MovieListFragment.OnMovieSelectedListener onMovieSelectedListener) {
        mOnMovieSelectedListener = onMovieSelectedListener;
    }

    public void loadData() {
        mPagesLoaded++;
        MovieClient.getMovieService().getPopularMovies(mPagesLoaded, new Callback<Page>() {
            @Override
            public void success(Page page, Response response) {
                mMovies.addAll(page.getMovies());
                notifyItemRangeInserted(getItemCount(),
                        mMovies.size() - 1);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return (position >= mMovies.size()) ? VIEW_TYPE_SPINNER
                : VIEW_TYPE_ROW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ROW:
                return new MovieItemHolder(
                        LayoutInflater.from(viewGroup.getContext())
                                .inflate(R.layout.movie_list_item, viewGroup, false));
            case VIEW_TYPE_SPINNER:
                return new ProgressBarHolder(
                        LayoutInflater.from(viewGroup.getContext())
                                .inflate(R.layout.progress_bar_row, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= mMovies.size()) {
            loadData();
            return;

        }
        MovieItemHolder movieItemHolder = (MovieItemHolder) holder;
        Picasso.with(movieItemHolder.mMoviePreviewImage.getContext())
                .load(BASE_URL + mMovies.get(position).getPosterPath())
                .placeholder(R.drawable.placeholder)
                .into(movieItemHolder.mMoviePreviewImage);
        movieItemHolder.mMovieTitleTextView.setText(mMovies.get(position).getTitle());
        movieItemHolder.mMovieRating.setText("Movie rate : " + mMovies.get(position).getRating());
        movieItemHolder.mRow.setActivated(position == mSelectedItemPosition);
    }

    @Override
    public int getItemCount() {
        return mMovies.size() + 1;
    }

    public class ProgressBarHolder extends RecyclerView.ViewHolder {

        private ProgressBar mProgressBar;

        public ProgressBarHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.loading_panel);

        }
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

    public int getSelectedItemPosition() {
        return mSelectedItemPosition;
    }

    public void setSelectedItemPosition(int selectedItemPosition) {
        mSelectedItemPosition = selectedItemPosition;
    }
}
