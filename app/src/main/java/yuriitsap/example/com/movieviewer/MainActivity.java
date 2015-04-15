package yuriitsap.example.com.movieviewer;

import android.content.Intent;
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
        if (getResources().getBoolean(R.bool.dual_pane) && getSupportFragmentManager()
                .findFragmentByTag("movie_details_fragment") == null) {
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.movie_details_fragment,
                    movieDetailFragment,
                    "movie_details_fragment").commit();
        }
    }

    @Override
    public void onMovieSelected(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailFragment.CURRENT_ID, id);

        if (getResources().getBoolean(R.bool.dual_pane)) {
            MovieDetailFragment movieDetailFragment
                    = (MovieDetailFragment) getSupportFragmentManager()
                    .findFragmentByTag("movie_details_fragment");
            movieDetailFragment.update(id);
        } else {
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}