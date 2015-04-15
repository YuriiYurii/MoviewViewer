package yuriitsap.example.com.movieviewer.utils;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import yuriitsap.example.com.movieviewer.model.Movie;
import yuriitsap.example.com.movieviewer.model.Page;

/**
 * Created by yuriitsap on 13.04.15.
 */
public interface MovieService {

    @GET("/movie/popular?api_key=c776ded64b682b68377f1bee206ea6f7")
    public void getPopularMovies(@Query("page") int pageNumber, Callback<Page> page);

    @GET("/movie/{id}?api_key=c776ded64b682b68377f1bee206ea6f7")
    public void getMovieById(@Path("id") int id, Callback<Movie> movies);


}
