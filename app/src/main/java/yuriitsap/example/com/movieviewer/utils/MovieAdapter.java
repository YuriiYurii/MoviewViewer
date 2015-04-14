package yuriitsap.example.com.movieviewer.utils;

import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import yuriitsap.example.com.movieviewer.R;
import yuriitsap.example.com.movieviewer.fragments.MovieListFragment;
import yuriitsap.example.com.movieviewer.model.Movie;

/**
 * Created by yuriitsap on 13.04.15.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieItemHolder> {

    private static final String BASE_URL = "http://image.tmdb.org/t/p/w92";
    private ArrayList<Movie> mMovies;
    private MovieListFragment.OnMovieSelectedListener mOnMovieSelectedListener;

    public MovieAdapter(ArrayList<Movie> movies,
            MovieListFragment.OnMovieSelectedListener onMovieSelectedListener) {
        mOnMovieSelectedListener = onMovieSelectedListener;
        mMovies = movies;
    }

    @Override
    public MovieItemHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new MovieItemHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.movie_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MovieItemHolder movieItemHolder, int position) {
        Picasso.with(movieItemHolder.mMoviePriviewImage.getContext())
                .load(BASE_URL + mMovies.get(position).getPosterPath())
                .into(movieItemHolder.mMoviePriviewImage);
        movieItemHolder.mMovieTitleTextView.setText(mMovies.get(position).getTitle());
        movieItemHolder.mMovieRating.setText("Movie rate : " + mMovies.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieItemHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private ImageView mMoviePriviewImage;
        private TextView mMovieTitleTextView;
        private TextView mMovieRating;
        private View mOverlayView;

        public MovieItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mMoviePriviewImage = (ImageView) itemView.findViewById(R.id.movie_picture_preview);
            mMovieTitleTextView = (TextView) itemView.findViewById(R.id.movie_title);
            mMovieRating = (TextView) itemView.findViewById(R.id.movie_rating);
            mOverlayView = itemView.findViewById(R.id.overlay_view);

        }

        @Override
        public void onClick(View v) {
            mOnMovieSelectedListener.onMovieSelected(mMovies.get(getPosition()).getId());
        }
    }

    public ArrayList<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        mMovies = movies;
    }
}
