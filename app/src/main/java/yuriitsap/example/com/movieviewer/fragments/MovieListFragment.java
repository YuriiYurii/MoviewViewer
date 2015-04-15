package yuriitsap.example.com.movieviewer.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
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

    private static int count = 0;

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
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                        .getLayoutManager();
                if (linearLayoutManager.getChildCount() + linearLayoutManager
                        .findFirstVisibleItemPosition() == linearLayoutManager.getItemCount()) {
                    Log.e("TAG", "entered = " + ++count);
                    Log.e("TAG", "linearLayoutManager.getChildCount() = " + linearLayoutManager
                            .getChildCount());
                    Log.e("TAG", "linearLayoutManager.findFirstVisibleItemPosition() = "
                            + linearLayoutManager
                            .findFirstVisibleItemPosition());
                    Log.e("TAG", "linearLayoutManager.getItemCount() = " + linearLayoutManager
                            .getItemCount());
//                    final ProgressDialog progressDialog = new ProgressDialog(
//                            getView().getContext());
//                    progressDialog.setIndeterminate(true);
//                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                    progressDialog.show();
//                    ((MovieAdapter) recyclerView.getAdapter()).loadData(2);
//                    Runnable runnable = new Runnable() {
//                        @Override
//                        public void run() {
//                            if (!((MovieAdapter) recyclerView.getAdapter()).isLoading()) {
//                                getView().removeCallbacks(this);
//                                progressDialog.dismiss();
//                            } else {
//                                getView().postOnAnimation(this);
//                            }
//
//                            Log.e("TAG", "enter once = " + Thread.currentThread().getName());
//
//
//                        }
//                    };
//                    getView().post(runnable);
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("MOVIE_LIST", mMovieAdapter.getMovies());
    }
}