package yuriitsap.example.com.movieviewer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import yuriitsap.example.com.movieviewer.R;
import yuriitsap.example.com.movieviewer.model.Page;
import yuriitsap.example.com.movieviewer.utils.MovieAdapter;
import yuriitsap.example.com.movieviewer.utils.MovieClient;

/**
 * Created by yuriitsap on 13.04.15.
 */
public class MovieListFragment extends Fragment {

    public interface OnMovieSelectedListener {

        public void onMovieSelected(int id);
    }

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private OnMovieSelectedListener mOnMovieSelectedListener;
    private static int lala = 0;


    public MovieListFragment() {
        Log.e("TAG", "MovieListFragment count = " + ++lala);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieClient.getMovieService().getPopularMovies(new Callback<Page>() {
            @Override
            public void success(Page page, Response response) {
                Log.e("TAG", "success");
                mMovieAdapter.getMovies().addAll(page.getMovies());
                mMovieAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TAG", "Error");

            }
        });
        mMovieAdapter = new MovieAdapter(mOnMovieSelectedListener);
//        this.setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mOnMovieSelectedListener = (OnMovieSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement mOnMovieSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mMovieAdapter);
        return view;
    }
}
