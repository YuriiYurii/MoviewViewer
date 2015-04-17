package yuriitsap.example.com.movieviewer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
                .add(R.id.movie_list_container, MovieListFragment.newInstance())
                .commit();
    }

    @Override
    public void onMovieSelected(int id) {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager()
                    .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_details_container, MovieDetailFragment.newInstance(id))
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_details_container, MovieDetailFragment.newInstance(id))
                    .addToBackStack(null)
                    .commit();
        }
    }
}