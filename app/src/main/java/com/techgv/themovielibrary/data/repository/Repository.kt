package com.techgv.themovielibrary.data.repository

import com.techgv.themovielibrary.data.remote.MoviesDetailTmDbApi
import com.techgv.themovielibrary.data.remote.response.*
import com.techgv.themovielibrary.data.remote.utils.Resource
import javax.inject.Inject

class Repository @Inject constructor(private val service: MoviesDetailTmDbApi) {

    suspend fun getPopularMovies(page:Int): Resource<MoviesResponse> {
        val response = service.popularMovies(page)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getTopRatedMovies(page:Int): Resource<MoviesResponse> {
        val response = service.topRatedMovies(page)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getUpComingMovies(page:Int): Resource<MoviesResponse> {
        val response = service.upcomingMovies(page)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getMoviesOnTheater(region:String,page:Int): Resource<MoviesResponse> {
        val response = service.moviesInTheater(region,page)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun moviesCategoryDetails(category:String, page: Int = 1): Resource<MoviesResponse> {
        val response = service.moviesCategoryDetail(category,page)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getLatestMovies(page:Int): Resource<MoviesResponse> {
        val response = service.latestMovies(page)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getMovieDetails(movieId:Int): Resource<MovieDetailsResponse> {
        val response = service.getMovieDetails(movieId)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getRecommendMovies(movieId:Int,page:Int): Resource<MoviesResponse> {
        val response = service.getRecommendation(movieId,page)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }


    suspend fun getSimilarMovies(movieId:Int,page:Int): Resource<MoviesResponse> {
        val response = service.getSimilarMovie(movieId,page)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getVideos(movieId:Int): Resource<VideoResponse> {
        val response = service.getVideos(movieId)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getCredits(movieId:Int): Resource<CastResponse> {
        val response = service.getCredits(movieId)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getImages(movieId:Int): Resource<ImageResponse> {
        val response = service.getImages(movieId)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getCastDetails(personId:Int): Resource<CastDetailsResponse> {
        val response = service.getCastDetails(personId)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getExternalIds(personId:Int): Resource<ExternalIdsResponse> {
        val response = service.getExternalIds(personId)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }

    suspend fun getMoviesCredits(personId:Int): Resource<MovieCreditsResponse> {
        val response = service.getMoviesCredits(personId)
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), response.body())
        }
    }


}