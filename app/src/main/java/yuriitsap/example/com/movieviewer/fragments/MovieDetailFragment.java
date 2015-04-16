package yuriitsap.example.com.movieviewer.fragments;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    public final static String CURRENT_ID = "CURRENT_ID";
    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185";
    private int mId = -1;
    public static int count = 0;

    public static MovieDetailFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(CURRENT_ID, id);
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    public MovieDetailFragment() {
        Log.e("TAG", "count = " + ++count);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            update(savedInstanceState.getInt(CURRENT_ID));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mId != -1) {
            outState.putInt(CURRENT_ID, mId);
        }
    }

    public void update(int id) {
        mId = id;
        MovieClient.getMovieService().getMovieById(id, new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
                Picasso.with(getActivity()).load(BASE_POSTER_URL + movie.getPosterPath())
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
