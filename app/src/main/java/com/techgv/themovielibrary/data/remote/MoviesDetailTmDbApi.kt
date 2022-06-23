package com.techgv.themovielibrary.data.remote

import com.techgv.themovielibrary.data.remote.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDetailTmDbApi {

    @GET("movie/popular")
    suspend fun popularMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun topRatedMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun upcomingMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/latest")
    suspend fun latestMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/{category}")
    suspend fun moviesCategoryDetail(
        @Path("category") category: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun moviesInTheater(
        @Query("region") region: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movie_id: Int): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendation(
        @Path("movie_id") movie_id: Int,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovie(
        @Path("movie_id") movie_id: Int,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") movie_id: Int): Response<VideoResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") movie_id: Int): Response<CastResponse>

    @GET("movie/{movie_id}/images")
    suspend fun getImages(@Path("movie_id") movie_id: Int): Response<ImageResponse>

    @GET("person/{person_id}")
    suspend fun getCastDetails(
        @Path("person_id") personId: Int
    ): Response<CastDetailsResponse>

    @GET("person/{person_id}/external_ids")
    suspend fun getExternalIds(
        @Path("person_id") personId: Int
    ): Response<ExternalIdsResponse>

    @GET("person/{person_id}/movie_credits")
    suspend fun getMoviesCredits(
        @Path("person_id") personId: Int
    ): Response<MovieCreditsResponse>
}