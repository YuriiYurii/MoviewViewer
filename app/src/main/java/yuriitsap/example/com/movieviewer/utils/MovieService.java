package yuriitsap.example.com.movieviewer.utils;

import retrofit.Callback;
import retrofit.http.GET;
import yuriitsap.example.com.movieviewer.model.Movie;
import yuriitsap.example.com.movieviewer.model.Page;

/**
 * Created by yuriitsap on 13.04.15.
 */
public interface MovieService {

    @GET("/movie/popular?api_key=c776ded64b682b68377f1bee206ea6f7")
    public void getPopularMovies(Callback<Page> page);

    @GET("/movie/550?api_key=c776ded64b682b68377f1bee206ea6f7")
    public void getMovieById(Callback<Movie> movies);

}
