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

        void onMovieSelected(int id);
    }

    private OnMovieSelectedListener mOnMovieSelectedListener;
    private MovieAdapter mMovieAdapter = new MovieAdapter();


    public static MovieListFragment newInstance() {
        return new MovieListFragment();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mOnMovieSelectedListener = (OnMovieSelectedListener) activity;
        mMovieAdapter.setOnMovieSelectedListener(mOnMovieSelectedListener);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mMovieAdapter.setMovies(savedInstanceState.<Movie>getParcelableArrayList("MOVIE_LIST"));
            mMovieAdapter.setSelectedItemPosition(savedInstanceState.getInt("CURRENT_POSITION"));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mMovieAdapter);
        recyclerView
                .setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                                .getLayoutManager();
                        if (linearLayoutManager.getChildCount() + linearLayoutManager
                                .findFirstVisibleItemPosition() == linearLayoutManager
                                .getItemCount()
                                && !mMovieAdapter.isLoading()) {
                            mMovieAdapter.loadData();
                        }
                    }
                });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("MOVIE_LIST", mMovieAdapter.getMovies());
        outState.putInt("CURRENT_POSITION", mMovieAdapter.getSelectedItemPosition());
    }
}