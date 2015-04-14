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
        if (savedInstanceState == null) {
            MovieListFragment movieListFragment = new MovieListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_list_container, movieListFragment).commit();
        }

        if (getResources().getBoolean(R.bool.dual_pane) && findViewById(
                R.id.movie_details_fragment) != null) {
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.movie_details_fragment,
                    movieDetailFragment,
                    "movie_details_fragment").commit();
        }
    }

    @Override
    public void onMovieSelected(int id) {
        if (getResources().getBoolean(R.bool.dual_pane)) {
            //start activity
            MovieDetailFragment fragment = (MovieDetailFragment) getSupportFragmentManager()
                    .findFragmentByTag("movie_details_fragment");
            if (fragment == null) {
                MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(MovieDetailFragment.CURRENT_ID, id);
                movieDetailFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.movie_details_fragment, movieDetailFragment,
                                "movie_details_fragment").commit();
            } else {
                fragment.update(id);
            }


        }

    }
}