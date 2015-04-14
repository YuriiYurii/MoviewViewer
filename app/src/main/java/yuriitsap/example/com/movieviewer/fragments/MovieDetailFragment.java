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

    private int mId = -1;
    public final static String CURRENT_ID = "CURRENT_ID";
    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w342";
    private static int count;

    public MovieDetailFragment() {
        count++;
        Log.e("TAG", "MovieDetailFragment created = " + count);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        MovieClient.getMovieService().getMovieById(C);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            update(savedInstanceState.getInt(CURRENT_ID));
        }
        return inflater.inflate(R.layout.movie_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = getArguments();
        if (bundle != null) {
            update(bundle.getInt(CURRENT_ID));
        } else if (mId != -1) {
            update(mId);
        }
    }

    public void update(int id) {
        mId = id;
        MovieClient.getMovieService().getMovieById(id, new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
                Picasso.with(getActivity()).load(BASE_POSTER_URL + movie.getPosterPath())
                        .into((android.widget.ImageView) getActivity()
                                .findViewById(R.id.movie_details_poster_holder));
                ((TextView) getActivity().findViewById(R.id.movie_details_title)).setText(
                        movie.getTitle());
                ((TextView) getActivity().findViewById(R.id.movie_details_rating))
                        .setText("Rating : " + movie.getRating());
                ((TextView) getActivity().findViewById(R.id.movie_details_budget))
                        .setText("Bdget : " + movie.getBudget());
                ((TextView) getActivity().findViewById(R.id.movie_details_description))
                        .setText(movie.getOverview());

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(CURRENT_ID, mId);
    }
}
