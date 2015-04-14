package yuriitsap.example.com.movieviewer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        public void onMovieSelected(int id);
    }

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private OnMovieSelectedListener mOnMovieSelectedListener;


    public MovieListFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mOnMovieSelectedListener = (OnMovieSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mMovieAdapter = new MovieAdapter(
                getArguments().<Movie>getParcelableArrayList("MOVIE_LIST_DATA"),
                mOnMovieSelectedListener);
        mRecyclerView.setAdapter(mMovieAdapter);
        return view;
    }
}
