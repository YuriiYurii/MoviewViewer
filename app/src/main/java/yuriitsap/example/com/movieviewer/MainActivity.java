package yuriitsap.example.com.movieviewer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

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
            MovieListFragment movieListFragment = new MovieListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_list_container, movieListFragment).commit();
    }

    @Override
    public void onMovieSelected(int id) {

    }
}