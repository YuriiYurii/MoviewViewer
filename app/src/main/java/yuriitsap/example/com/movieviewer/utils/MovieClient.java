package yuriitsap.example.com.movieviewer.utils;

import retrofit.RestAdapter;

/**
 * Created by yuriitsap on 13.04.15.
 */
public class MovieClient {

    private static final String API_URL = "http://api.themoviedb.org/3";
    private static RestAdapter REST_ADAPTER;
    private static MovieService mMovieService;

    static {
        setupRestClient();
    }

    private static void setupRestClient() {
        REST_ADAPTER = new RestAdapter.Builder().setEndpoint(API_URL).build();
    }

    public static MovieService getMovieService() {
        if (mMovieService == null) {
            mMovieService = REST_ADAPTER.create(MovieService.class);
        }
        return mMovieService;
    }
}
