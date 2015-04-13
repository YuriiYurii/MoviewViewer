package yuriitsap.example.com.movieviewer.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import yuriitsap.example.com.movieviewer.R;

/**
 * Created by yuriitsap on 13.04.15.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieItemHolder> {


    @Override
    public MovieItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieItemHolder movieItemHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
