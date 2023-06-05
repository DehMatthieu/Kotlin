package fr.epf.mm.kotlin.CineLove

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String
    ):Call<ApiResponse>

    @GET("movie/{movie_id}")
    fun getMovieById(
        @Query("language") language: String,
        @Query("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ):Call<ApiResponse>

    @GET("trending/movie/{time_window}")
    fun getTrendingMovies(
        @Query("language") language: String,
        @Query("time_window") apiKey: String,
        @Query("api_key") api_key: String
    ):Call<ApiResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ):Call<ApiResponse>

    @GET("search/movie")
    fun getMovie(
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("api_key") api_key: String
    ):Call<ApiResponse>
}
