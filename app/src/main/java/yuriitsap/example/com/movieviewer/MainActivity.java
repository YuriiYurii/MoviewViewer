package yuriitsap.example.com.movieviewer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import yuriitsap.example.com.movieviewer.model.Movie;
import yuriitsap.example.com.movieviewer.utils.MovieClient;
import yuriitsap.example.com.movieviewer.utils.MovieService;


public class MainActivity extends ActionBarActivity {

    private MovieService mMovieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Callback<Page> movies = new Callback<Page>() {
            @Override
            public void success(Page movies, Response response) {
                Log.e("TAG", "Success");
                Log.e("TAG", " movies = " + movies);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TAG", "Error");

            }
        };

        mMovieService = MovieClient.getMovieService();
        mMovieService.getPopularMovies(movies);


    }

    public class Page {

        public List<Movie> mMovies;

        @Override
        public String toString() {
            return "Page{" +
                    "mMovies=" + mMovies +
                    '}';
        }
    }
}