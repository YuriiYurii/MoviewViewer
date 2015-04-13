package yuriitsap.example.com.movieviewer.utils;

import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import yuriitsap.example.com.movieviewer.R;
import yuriitsap.example.com.movieviewer.model.Movie;

/**
 * Created by yuriitsap on 13.04.15.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieItemHolder> {

    private LinkedList<Movie> mMovies;


    @Override
    public MovieItemHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new MovieItemHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.movie_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MovieItemHolder movieItemHolder, int position) {
        Picasso.with(movieItemHolder.mMoviePriviewImage.getContext())
                .load(mMovies.get(position).getPosterPath())
                .into(movieItemHolder.mMoviePriviewImage);
        movieItemHolder.

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieItemHolder extends RecyclerView.ViewHolder {

        private ImageView mMoviePriviewImage;
        private TextView mMovieTitleTextView;
        private TextView mMovieDescriptionTextView;

        public MovieItemHolder(View itemView) {
            super(itemView);

            mMoviePriviewImage = (ImageView) itemView.findViewById(R.id.movie_picture_preview);
            mMovieTitleTextView = (TextView) itemView.findViewById(R.id.movie_title);
            mMovieDescriptionTextView = (TextView) itemView.findViewById(R.id.movie_description);
        }
    }
}
