package yuriitsap.example.com.movieviewer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import yuriitsap.example.com.movieviewer.fragments.MovieDetailFragment;

/**
 * Created by yuriitsap on 14.04.15.
 */
public class MovieDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_fragment_activity);
        if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }
        if (savedInstanceState == null) {
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_details_container, movieDetailFragment,
                            "details_activity_fragment").commit();
        }

    }

}
