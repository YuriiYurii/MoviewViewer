package yuriitsap.example.com.movieviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import yuriitsap.example.com.movieviewer.fragments.MovieDetailFragment;
import yuriitsap.example.com.movieviewer.fragments.MovieListFragment;
import yuriitsap.example.com.movieviewer.model.Page;
import yuriitsap.example.com.movieviewer.utils.MovieClient;
import yuriitsap.example.com.movieviewer.utils.MovieService;


public class MainActivity extends ActionBarActivity implements
        MovieListFragment.OnMovieSelectedListener{

    private MovieService mMovieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Callback<Page> movies = new Callback<Page>() {
            @Override
            public void success(Page page, Response response) {
                Log.e("TAG", "Success");
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("MOVIE_LIST_DATA", page.getMovies());
                Fragment fragment = new MovieListFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.movie_list_container, fragment).commit();


            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TAG", "Error");

            }
        };

        mMovieService = MovieClient.getMovieService();
        mMovieService.getPopularMovies(movies);


    }

    @Override
    public void onMovieSelected(int id) {
        Fragment movieDetailFragment=getSupportFragmentManager().findFragmentById(
                R.id.movie_details);

        if(getResources().getBoolean(R.bool.dual_pane)){

        }

    }
}