package yuriitsap.example.com.movieviewer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import yuriitsap.example.com.movieviewer.fragments.MovieDetailFragment;
import yuriitsap.example.com.movieviewer.fragments.MovieListFragment;


public class MainActivity extends ActionBarActivity implements
        MovieListFragment.OnMovieSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        if (savedInstanceState != null) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.movie_list_container, MovieListFragment.newInstance(), "movie_list")
                .commit();
    }

    @Override
    public void onMovieSelected(int id) {
        MovieDetailFragment movieDetailFragment
                = (MovieDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.movie_details_container);
        if (movieDetailFragment == null) {
            movieDetailFragment = MovieDetailFragment.newInstance(id);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_details_container, movieDetailFragment,
                            "movie_details_fragment")
                    .addToBackStack(null)
                    .commit();
        }
        movieDetailFragment.update(id);
    }
}