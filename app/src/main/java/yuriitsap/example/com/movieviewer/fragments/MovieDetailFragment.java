package yuriitsap.example.com.movieviewer.fragments;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import yuriitsap.example.com.movieviewer.R;
import yuriitsap.example.com.movieviewer.model.Movie;
import yuriitsap.example.com.movieviewer.utils.MovieClient;

/**
 * Created by yuriitsap on 13.04.15.
 */
public class MovieDetailFragment extends Fragment {

    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185";

    public static MovieDetailFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("CURRENT_ID", id);
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            MovieClient.getMovieService()
                    .getMovieById(getArguments().getInt("CURRENT_ID"), new Callback<Movie>() {
                        @Override
                        public void success(Movie movie, Response response) {
                            getView().findViewById(R.id.loading_spinner).setVisibility(View.GONE);
                            Picasso.with(getActivity())
                                    .load(BASE_POSTER_URL + movie.getPosterPath())
                                    .placeholder(R.drawable.placeholder)
                                    .into((android.widget.ImageView) getView()
                                            .findViewById(R.id.movie_details_poster_holder));
                            ((TextView) getView().findViewById(R.id.movie_details_title)).setText(
                                    movie.getTitle());
                            ((TextView) getView().findViewById(R.id.movie_details_rating))
                                    .setText("Rating : " + movie.getRating());
                            ((TextView) getView().findViewById(R.id.movie_details_budget))
                                    .setText("Budget : " + movie.getBudget());
                            ((TextView) getView().findViewById(R.id.movie_details_description))
                                    .setText(movie.getOverview());
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
        }
    }
}
