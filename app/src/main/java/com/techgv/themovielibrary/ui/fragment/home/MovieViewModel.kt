package com.techgv.themovielibrary.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techgv.themovielibrary.data.remote.response.*
import com.techgv.themovielibrary.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val popularMoviesLiveData = MutableLiveData<MoviesResponse>()
    val topRatedMoviesLiveData = MutableLiveData<MoviesResponse>()
    val upcomingMoviesLiveData = MutableLiveData<MoviesResponse>()
    val nowPlayingMoviesLiveData = MutableLiveData<MoviesResponse>()
    val latestMoviesLiveData = MutableLiveData<MoviesResponse>()
    val moviesDetailsLivedata = MutableLiveData<MovieDetailsResponse>()
    val movieCategoryDetailLivedata = MutableLiveData<MoviesResponse>()
    val getSimilarMoviesLivedata = MutableLiveData<MoviesResponse>()
    val getRecommendationMoviesLivedata = MutableLiveData<MoviesResponse>()
    val videosLivedata = MutableLiveData<VideoResponse>()
    val creditsLivedata = MutableLiveData<CastResponse>()
    val imagesLivedata = MutableLiveData<ImageResponse>()
    val castDetailsLivedata = MutableLiveData<CastDetailsResponse>()
    val externalIdsLivedata = MutableLiveData<ExternalIdsResponse>()
    val moviesCreditsLivedata = MutableLiveData<MovieCreditsResponse>()


    fun popularMovies(page: Int = 1) {
        viewModelScope.launch {
            val result = repository.getPopularMovies(page)
            result.data?.let {
                popularMoviesLiveData.postValue(it)
            }
        }
    }

    fun topRatedMovies(page: Int = 1) {
        viewModelScope.launch {
            val result = repository.getTopRatedMovies(page)
            result.data?.let {
                topRatedMoviesLiveData.postValue(it)
            }
        }
    }

    fun upcomingMovies(page: Int = 1) {
        viewModelScope.launch {
            val result = repository.getUpComingMovies(page)
            result.data?.let {
                upcomingMoviesLiveData.postValue(it)
            }
        }
    }

    fun nowPlayingMovies(region: String = "in", page: Int = 1) {
        viewModelScope.launch {
            val result = repository.getMoviesOnTheater(region, page)
            result.data?.let {
                nowPlayingMoviesLiveData.postValue(it)
            }
        }
    }

    fun movieCategoryDetails(category: String, page: Int = 1) {
        viewModelScope.launch {
            val result = repository.moviesCategoryDetails(category, page)
            result.data?.let {
                movieCategoryDetailLivedata.postValue(it)
            }
        }
    }

    fun latestMovies(page: Int = 1) {
        viewModelScope.launch {
            val result = repository.getLatestMovies(page)
            result.data?.let {
                latestMoviesLiveData.postValue(it)
            }
        }
    }


    fun getMoviesDetails(movieId: Int) {
        viewModelScope.launch {
            val result = repository.getMovieDetails(movieId)
            result.data?.let {
                moviesDetailsLivedata.postValue(it)
            }
        }
    }


    fun getRecommendMovies(movieId: Int, page: Int = 1) {
        viewModelScope.launch {
            val result = repository.getRecommendMovies(movieId, page)
            result.data?.let {
                getRecommendationMoviesLivedata.postValue(it)
            }
        }
    }

    fun getSimilarMovies(movieId: Int, page: Int = 1) {
        viewModelScope.launch {
            val result = repository.getSimilarMovies(movieId, page)
            result.data?.let {
                getSimilarMoviesLivedata.postValue(it)
            }
        }
    }

    fun getVideos(movieId: Int) {
        viewModelScope.launch {
            val result = repository.getVideos(movieId)
            result.data?.let {
                videosLivedata.postValue(it)
            }
        }
    }

    fun getCredits(movieId: Int) {
        viewModelScope.launch {
            val result = repository.getCredits(movieId)
            result.data?.let {
                creditsLivedata.postValue(it)
            }
        }
    }

    fun getImages(movieId: Int) {
        viewModelScope.launch {
            val result = repository.getImages(movieId)
            result.data?.let {
                imagesLivedata.postValue(it)
            }
        }
    }

    fun getCastDetails(personId: Int) {
        viewModelScope.launch {
            val result = repository.getCastDetails(personId)
            result.data?.let {
                castDetailsLivedata.postValue(it)
            }
        }
    }

    fun getExternalId(personId: Int) {
        viewModelScope.launch {
            val result = repository.getExternalIds(personId)
            result.data?.let {
                externalIdsLivedata.postValue(it)
            }
        }
    }

    fun getMovieCredits(personId: Int) {
        viewModelScope.launch {
            val result = repository.getMoviesCredits(personId)
            result.data?.let {
                moviesCreditsLivedata.postValue(it)
            }
        }
    }


}