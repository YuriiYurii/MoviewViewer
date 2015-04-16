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

import yuriitsap.example.com.movieviewer.R;
import yuriitsap.example.com.movieviewer.model.Movie;
import yuriitsap.example.com.movieviewer.utils.MovieAdapter;

/**
 * Created by yuriitsap on 13.04.15.
 */
public class MovieListFragment extends Fragment {


    public interface OnMovieSelectedListener {

        void onMovieSelected(int id);
    }

    private MovieAdapter mMovieAdapter;


    public static MovieListFragment newInstance() {
        Log.e("TAG", "newInstance");
        return new MovieListFragment();

    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        mMovieAdapter = new MovieAdapter((OnMovieSelectedListener) activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mMovieAdapter);
        if (savedInstanceState != null) {
            mMovieAdapter.setMovies(savedInstanceState.<Movie>getParcelableArrayList("MOVIE_LIST"));
            mMovieAdapter.setSelectedItemPosition(savedInstanceState.getInt("CURRENT_POSITION"));
        } else {
            mMovieAdapter.setSelectedItemPosition(-1);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("MOVIE_LIST", mMovieAdapter.getMovies());
        outState.putInt("CURRENT_POSITION", mMovieAdapter.getSelectedItemPosition());
    }
}