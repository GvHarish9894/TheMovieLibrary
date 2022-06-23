package com.techgv.themovielibrary.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowDetailTmDbApi {

//    @GET("genre/tv/list")
//    fun getTvShowGenres(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): TvShowGenreListResponse
//
//    @GET("search/tv")
//    suspend fun searchTvShowsByQuery(
//        @Query("api_key") apiKey: String,
//        @Query("query") query: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): PageResponse<TvShowResponse>
//
//    /*TvShowTmdbApi */
//
//    @GET("tv/{tv_id}")
//    fun getTvShow(
//        @Path("tv_id") tv_id: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Call<TvShowResponse>
//
//    @GET("discover/tv")
//    fun getTvShowByGenre(
//        @Query("with_genres") genreId: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("include_adult") includeAdult:
//        Boolean,
//        @Query("page") page: Int
//    ): PageResponse<TvShowResponse>>
//
//    @GET("tv/airing_today")
//    fun getAiringTodayTvShows(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): PageResponse<TvShowResponse>
//
//    @GET("tv/on_the_air")
//    fun getOnTheAirTvShows(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): PageResponse<TvShowResponse>
//
//    @GET("tv/popular")
//    fun getPopularTvShows(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): PageResponse<TvShowResponse>
//
//    @GET("tv/top_rated")
//    fun getTopRatedTvShows(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): PageResponse<TvShowResponse>
//
//
//    /* TvShowDetailTmDbApi*/
//    @GET("tv/{tv_id}/credits")
//    fun getCastByTvShow(
//        @Path("tv_id") tvId: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): CastListResponse
//
//    @GET("tv/{tv_id}/recommendations")
//    fun getRecommendationByTvShow(
//        @Path("tv_id") tvId: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): PageResponse<TvShowResponse>
//
//    @GET("tv/{tv_id}/similar")
//    fun getSimilarByTvShow(
//        @Path("tv_id") tvId: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): PageResponse<TvShowResponse>
//
//    @GET("tv/{tv_id}/reviews")
//    fun getReviewByTvShow(
//        @Path("tv_id") tvId: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): PageResponse<ReviewResponse>
//
//    @GET("tv/{tv_id}/videos")
//    fun getVideosByTvShow(
//        @Path("tv_id") tvId: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): VideoListResponse
}